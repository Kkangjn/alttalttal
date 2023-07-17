package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.Dto.MypageResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import com.alttalttal.mini_project.Dto.MypageRequestDto;
import com.alttalttal.mini_project.service.MypageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

// 마이 페이지 불러오기
    @GetMapping()
    public MypageResponseDto getInfo(HttpServletRequest request, HttpServletResponse response){
        return mypageService.getInfo(request, response);
    }

    // 마이페이지 닉네임 수정
    @PutMapping("")
    public MypageResponseDto updateMypage(@RequestBody MypageRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        return mypageService.updateMypage(requestDto, request, response);
    }
}
