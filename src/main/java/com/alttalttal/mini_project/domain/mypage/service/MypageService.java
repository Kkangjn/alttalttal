package com.alttalttal.mini_project.domain.mypage.service;

import com.alttalttal.mini_project.domain.mypage.dto.MypageRequestDto;
import com.alttalttal.mini_project.domain.mypage.dto.MypageResponseDto;
import com.alttalttal.mini_project.domain.recipe.dto.simpleRecipesResponseDto;
import com.alttalttal.mini_project.domain.user.entity.User;
import com.alttalttal.mini_project.global.jwt.JwtUtil;
import com.alttalttal.mini_project.domain.recipe.repository.MongoRecipeRepository;
import com.alttalttal.mini_project.global.token.ServiceManagerImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MypageService {
    private final JwtUtil jwtUtil;
    private final MongoRecipeRepository mongoRecipeRepository; // mongodb라서 연관관계 설정이 안되어있음
    private final ServiceManagerImpl serviceManager;

    // 마이페이지 조회
    public MypageResponseDto getMypage(String accessToken, String refreshToken, HttpServletResponse response) {
        if (!jwtUtil.validateAllToken(accessToken, refreshToken, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        User user = serviceManager.getUserFromToken(refreshToken);
        // 유저가 찜한 레시피 목록
        List<simpleRecipesResponseDto> simpleRecipesResponseDtoList = mongoRecipeRepository.findByZzimUserIdListUserId(user.getId()).stream().map(simpleRecipesResponseDto::new).toList();
        return new MypageResponseDto(user, simpleRecipesResponseDtoList);
    }

    @Transactional
    public MypageResponseDto updateMypage(MypageRequestDto mypagerequestDto, String accessToken, String refreshToken, HttpServletResponse response) {
        // token이 유효한지 확인
        if (!jwtUtil.validateAllToken(accessToken, refreshToken, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        // token에 email이 해당 이메일이 맞는지
        User user = serviceManager.getUserFromToken(refreshToken);
        // 맞다면 고쳐요
        user.update(mypagerequestDto);
        List<simpleRecipesResponseDto> simpleRecipesResponseDtoList = mongoRecipeRepository.findByZzimUserIdListUserId(user.getId()).stream().map(simpleRecipesResponseDto::new).toList();
        return new MypageResponseDto(user, simpleRecipesResponseDtoList);
        // return ResponseEntity.ok("변경 완료!");
    }

    // 마이페이지에서 라운지 조회
//    public List<LoungeResponseDto> getLounge() {
//        return mypageRepository.findAllByOrderByCreatedAtDesc().stream().map(MypageResponseDto::new).toList();
//    }
//
//
//    public MypageRequestDto findById(Long id) {
//    }
    // --> 생성자에서 해결!
}