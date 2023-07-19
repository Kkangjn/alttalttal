package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.dto.simpleRecipesResponseDto;
import com.alttalttal.mini_project.entity.MongoRecipe;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.mongo.repository.MongoRecipeRepository;
import com.mongodb.BasicDBObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {
    private final MongoRecipeRepository mongoRecipeRepository;
    private final MongoTemplate mongoTemplate;
    private final ServiceManagerImpl serviceManager;
    private final JwtUtil jwtUtil;

    public List<simpleRecipesResponseDto> getAllRecipes() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "zzimUserIdList"));
        return mongoTemplate.find(query, MongoRecipe.class).stream().map(simpleRecipesResponseDto::new).toList();
    }

    public RecipeResponseDto getRecipe(Long recipeId, HttpServletRequest request, HttpServletResponse response){
        // recipe가 있는지 확인
        MongoRecipe recipe = mongoRecipeRepository.findByRecipeId(recipeId).orElseThrow(()-> new IllegalArgumentException("잘못된 접근 입니다."));
        // 찜한 유저가 몇명인지 확인
        Integer countZzim = recipe.getZzimUserIdList().size();
        // 로그인 안한 유저는 false
        Boolean isUserZzim = false;
        // 토큰 검증
        String accessToken = serviceManager.getAccessToken(request);
        String refreshToken = serviceManager.getRefreshToken(request);
        log.info("accessToken = {}", accessToken);
        log.info("refreshToken = {}", refreshToken);
        if(!accessToken.isBlank()){
            // token이 유효한지 확인
            if (jwtUtil.validateAllToken(accessToken, refreshToken, response)) {
                // token에 email로 user가져와서 userId로 찜 확인
                Long userId = serviceManager.getUserIdFromToken(refreshToken);
                isUserZzim = recipe.getZzimUserIdList().stream().anyMatch(zzim -> zzim.getUserId()==userId);
            }else isUserZzim = false;
        }

        // 이미지가 없으면 default 이미지로 보내기
//        String imagePath = "file:src/main/resources/images/" + recipe.getRecipeId() + ".png";
//        Resource imageResource;
//        try{
//            imageResource = new UrlResource(imagePath);
//        }catch (MalformedURLException e){
//            String defaultImageUrl = "http://localhost:8080/images/noimage.png";
//            return new RecipeResponseDto(recipe, isUserZzim, countZzim, defaultImageUrl);
//        }

//        String imageUrl = "http://localhost:8080/images/" + recipe.getRecipeId() + ".png";

        return new RecipeResponseDto(recipe, isUserZzim, countZzim);
    }

    public ResponseEntity<String> createZzim(Long recipeId, String accessToken, String refreshToken, HttpServletResponse response) {
        if(!jwtUtil.validateAllToken(accessToken, refreshToken, response)){
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
        Long userId = serviceManager.getUserIdFromToken(refreshToken);
        // 검증을 추가(이미 찜한 상태인지 확인)
        if(mongoRecipeRepository.existsByRecipeIdAndZzimUserIdListUserId(recipeId,userId)){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        Query query = new Query(Criteria.where("recipeId").is(recipeId));
        Update update = new Update().push("zzimUserIdList", new BasicDBObject("userId", userId));
        mongoTemplate.updateFirst(query, update, MongoRecipe.class);

        return ResponseEntity.ok("찜 성공");
    }

    public ResponseEntity<String> deleteZzim(Long recipeId, String accessToken, String refreshToken, HttpServletResponse response) {
        if(!jwtUtil.validateAllToken(accessToken, refreshToken, response)){
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
        Long userId = serviceManager.getUserIdFromToken(refreshToken);
        if(!mongoRecipeRepository.existsByRecipeIdAndZzimUserIdListUserId(recipeId,userId)){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        Query query = new Query(Criteria.where("recipeId").is(recipeId));
        Update update = new Update().pull("zzimUserIdList", new BasicDBObject("userId", userId));
        mongoTemplate.updateFirst(query, update, MongoRecipe.class);

        return ResponseEntity.ok("찜 삭제");
    }
}
