package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.dto.simpleRecipesResponseDto;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.repository.UserRepository;
import com.alttalttal.mini_project.service.RecipeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "*")
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
//    final Long userId = 2L; // 유저 테이블이 없어서 임의로 값을 넣어줌
    // security를 쓴다면 @AuthenticationPrincipal UserDetails userDetails
    // userDetails.getName? getId로 처리할듯?

    // 만약 로그인을 하지 않았다면 fi(userDeails == null) {userId = 0;} 으로 service로 보내서 처리할듯!

    @GetMapping()
    private List<simpleRecipesResponseDto> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeResponseDto getRecipe(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        return recipeService.getRecipe(id, request, response);
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> createZzim(@PathVariable Long id, @RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response){
        return recipeService.createZzim(id, accessToken, refreshToken, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZzim(@PathVariable Long id, @RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response){
        return recipeService.deleteZzim(id, accessToken, refreshToken, response);
    }

}
