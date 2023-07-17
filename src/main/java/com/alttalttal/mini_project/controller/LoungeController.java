package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.LoungeResponseDto;
import com.alttalttal.mini_project.dto.LoungeRequestDto;
import com.alttalttal.mini_project.service.LoungeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// import com.alttalttal.jwt.JwtUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lounge")
public class LoungeController {
    private final LoungeService loungeService;

    // 라운지 작성 요청받기.
    @PostMapping()
    public void createLounge(@RequestBody LoungeRequestDto loungeRequestDto, HttpServletRequest request, HttpServletResponse response) {
        loungeService.createLounge(loungeRequestDto, request, response);
    }

    // 라운지 페이지 불러오기. (라운지 리스트 조회)
    @GetMapping()
    public List<LoungeResponseDto> getfindAll() {
        return loungeService.getfindAll();
    }

    // 라운지 글 삭제
    @DeleteMapping("/{id}")
    public void deleteLounge(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        loungeService.deleteLounge(id ,request, response);
    }
}








//    // 라운지 생성
//    @PostMapping("/{postId}")
//    public LoungeResponseDto createLounge(@PathVariable Long postId,
//                                          @RequestBody @Valid LoungeRequestDto requestDto,
//                                          HttpServletRequest req) {
//        String token = authentication(req);
//        return LoungeService.createLounge(postId, requestDto, token);
//    }
//
//    // 라운지 수정
//    @PutMapping("/{LoungeId}")
//    public LoungeResponseDto updateLounge(@PathVariable Long LoungeId, @RequestBody @Valid LoungeRequestDto requestDto, HttpServletRequest req){
//        String token = authentication(req);
//        return LoungeService.updateLounge(LoungeId, requestDto, token);
//    }
//
//    // 라운지 삭제
//    @DeleteMapping("/{LoungeId}")
//    public StatusCodesResponseDto deleteLounge(@PathVariable Long LoungeId, HttpServletRequest req){
//        String token = authentication(req);
//        return LoungeService.deleteLounge(LoungeId, token);
//    }
//
//    private String authentication(HttpServletRequest req) {
//        // 토큰값 가져오기
//        String tokenValue = jwtUtil.getTokenFromRequest(req);
//        // JWT 토큰 substring
//        String token = jwtUtil.substringToken(tokenValue);
//
//        // 토큰 검증
//        if (!jwtUtil.validateToken(token)) {
//            throw new IllegalArgumentException("Token Error");
//        }
//        return token;
//    }

//    // 라운지 상세 조회
//    // 해당 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 lounge.html에 출력.
//    @GetMapping("/{id}")
//    public String findById(@PathVariable Long id, Model model) {
//        model.addAttribute("lounge", longeRequestDto);
//        return "lounge";
//    }
// 경로 상에 있는 값을 가져올 때는 @PathVariable 이라는 어노테이션을 사용한다.
// 데이터를 담아가야 하니까 Model 객체를 사용한다.
// 상세 조회(단건 조회)
//    @DeleteMapping()
//    public DeleteLounge(@)


//    // 라운지 작성 페이지(loungeSave) 불러오기.
//    @GetMapping("/save")
//    public String saveForm() {
//        return "loungeSave";
//    }
//
//
//
//
