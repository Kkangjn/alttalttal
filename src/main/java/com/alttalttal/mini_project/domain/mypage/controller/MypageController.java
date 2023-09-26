package com.alttalttal.mini_project.domain.mypage.controller;

import com.alttalttal.mini_project.domain.mypage.dto.MypageRequestDto;
import com.alttalttal.mini_project.domain.mypage.dto.MypageResponseDto;
import com.alttalttal.mini_project.global.jwt.JwtUtil;
import com.alttalttal.mini_project.domain.mypage.service.MypageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(exposedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    // 마이 페이지 불러오기
    @GetMapping()
    public MypageResponseDto getMypage(@RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response){
        return mypageService.getMypage(accessToken, refreshToken, response);
    }

    // 마이페이지 닉네임 수정
    @PutMapping("")
    public MypageResponseDto updateMypage(@RequestBody MypageRequestDto requestDto, @RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response) {
        return mypageService.updateMypage(requestDto, accessToken, refreshToken, response);
    }
}
