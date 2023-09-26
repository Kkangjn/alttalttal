package com.alttalttal.mini_project.domain.user.service;

import com.alttalttal.mini_project.global.dto.MessageResponseDto;
import com.alttalttal.mini_project.domain.user.dto.SignUpRequestDto;
import com.alttalttal.mini_project.domain.user.dto.UserInfoResponseDto;
import com.alttalttal.mini_project.domain.user.entity.User;
import com.alttalttal.mini_project.domain.user.entity.UserRoleEnum;
import com.alttalttal.mini_project.global.jwt.JwtUtil;
import com.alttalttal.mini_project.domain.user.repository.UserRepository;
import com.alttalttal.mini_project.global.token.ServiceManagerImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final JwtUtil jwtUtil;

    @Value("${jwt.admin.key}") // application.properties의 adminKey 가져옴.
    private String adminKey;

    private final ServiceManagerImpl serviceManager;
    public ResponseEntity<MessageResponseDto> createUser(SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        String nickname = signUpRequestDto.getNickname();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByEmail(email);
        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 email 입니다.");
        }
        if(checkNickname.isPresent()){
            throw new IllegalArgumentException("중복된 nickname 입니다.");
        }

        // ADMIN KEY를 통해 권한부여 결정
        UserRoleEnum role = UserRoleEnum.USER;
        if (signUpRequestDto.isAdmin()) {
            if (!adminKey.equals(signUpRequestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능 합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 회원 등록
        User user = new User(email, password, nickname, role);
        userRepository.save(user);

        return new ResponseEntity<>(new MessageResponseDto("회원가입 성공!" , HttpStatus.OK.toString()), HttpStatus.OK);
    }

    public ResponseEntity<MessageResponseDto> logoutUser(String accessToken, String refreshToken) {
        Long expiration = jwtUtil.getExpiration(jwtUtil.substringToken(refreshToken));

        if(!redisService.delValues(refreshToken)) {
            throw new IllegalArgumentException("삭제되지 않았습니다.");
        }

        redisService.setValues(jwtUtil.substringToken(accessToken), "logout", expiration);
        return new ResponseEntity<>(new MessageResponseDto("로그아웃 성공!", HttpStatus.OK.toString()), HttpStatus.OK);
    }

    public UserInfoResponseDto userInfo(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = serviceManager.getAccessToken(request);
        String refreshToken = serviceManager.getRefreshToken(request);
        log.info("accessToken = {}", accessToken);
        log.info("refreshToken = {}", refreshToken);
        if(accessToken.isBlank()){
            return new UserInfoResponseDto(false, "No User");
        }
        // token이 유효한지 확인
        if (!jwtUtil.validateAllToken(accessToken, refreshToken, response)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        // token에 email이 해당 이메일이 맞는지
        String email = jwtUtil.getEmailFromToken(refreshToken);
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );

        String nickname = user.getNickname();
        return new UserInfoResponseDto(true, nickname);
    }
}
