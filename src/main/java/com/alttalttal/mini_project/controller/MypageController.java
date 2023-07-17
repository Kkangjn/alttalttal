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

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;



// 마이 페이지(mypage) 불러오기.
// 닉네임 수정 기능, 내가 단 라운지 리스트 조회, 내가 찜한 리스트 조회
//    @GetMapping("")
//    public MypageResponseDto getLounge(HttpServletRequest httpServletRequest) {
//        return mypageService.getLounge(httpServletRequest);
//    }

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
