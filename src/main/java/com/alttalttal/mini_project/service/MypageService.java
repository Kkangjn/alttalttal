package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.Dto.MypageRequestDto;
import com.alttalttal.mini_project.Dto.MypageResponseDto;
import com.alttalttal.mini_project.entity.User;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MypageService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // 마이페이지에서 라운지 조회
//    public List<LoungeResponseDto> getLounge() {
//        return mypageRepository.findAllByOrderByCreatedAtDesc().stream().map(MypageResponseDto::new).toList();
//    }
//
//
//    public MypageRequestDto findById(Long id) {
//    }

    @Transactional
    public MypageResponseDto updateMypage(MypageRequestDto mypagerequestDto, HttpServletRequest request, HttpServletResponse response) {
        // token이 유효한지 확인
        if (!jwtUtil.validateAllToken(request, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        // token에 email이 해당 이메일이 맞는지
        String refresh = jwtUtil.substringToken(jwtUtil.getTokenFromRequest("Refresh", request));
        String email = jwtUtil.getUserInfoFromToken(refresh).getSubject();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );
        // 맞다면 고쳐요
        user.update(mypagerequestDto);
        return new MypageResponseDto(user);
    }

    // 유저 정보 가져오기
    public MypageResponseDto getInfo(HttpServletRequest request, HttpServletResponse response) {
        if (!jwtUtil.validateAllToken(request, response)) {
           throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        String refresh = jwtUtil.substringToken(jwtUtil.getTokenFromRequest("Refresh", request));
        String email = jwtUtil.getUserInfoFromToken(refresh).getSubject();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );
        return new MypageResponseDto(user);
    }
}