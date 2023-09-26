package com.alttalttal.mini_project.domain.lounge.controller;

import com.alttalttal.mini_project.domain.lounge.dto.LoungeRequestDto;
import com.alttalttal.mini_project.domain.lounge.dto.LoungeResponseDto;
import com.alttalttal.mini_project.global.dto.MessageResponseDto;
import com.alttalttal.mini_project.global.jwt.JwtUtil;
import com.alttalttal.mini_project.domain.lounge.service.LoungeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/lounge")
public class LoungeController {
    private final LoungeService loungeService;

    // 라운지 페이지 불러오기. (라운지 리스트 조회)
    @GetMapping()
    public List<LoungeResponseDto> getfindAll() {
        return loungeService.getfindAll();
    }

    // 라운지 작성 요청받기.
    @PostMapping()
    public ResponseEntity<MessageResponseDto> createLounge(@RequestBody LoungeRequestDto loungeRequestDto, @RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response) {
        return loungeService.createLounge(loungeRequestDto, accessToken, refreshToken, response);
    }

    // 라운지 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> deleteLounge(@PathVariable Long id, @RequestHeader(JwtUtil.ACCESS_HEADER) String accessToken, @RequestHeader(JwtUtil.REFRESH_HEADER) String refreshToken, HttpServletResponse response) {
        return loungeService.deleteLounge(id ,accessToken, refreshToken, response);
    }
}

