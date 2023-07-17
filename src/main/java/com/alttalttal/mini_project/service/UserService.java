package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.LoginRequestDto;
import com.alttalttal.mini_project.dto.MessageResponseDto;
import com.alttalttal.mini_project.dto.SignUpRequestDto;
import com.alttalttal.mini_project.entity.User;
import com.alttalttal.mini_project.entity.UserRoleEnum;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RedisService redisService;
    private JwtUtil jwtUtil;

    @Value("${jwt.admin.key}") // application.properties의 adminKey 가져옴.
    private String adminKey;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RedisService redisService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.redisService = redisService;
    }
    public ResponseEntity<MessageResponseDto> createUser(SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        String nickname = signUpRequestDto.getNickname();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByEmail(email);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 username 입니다.");
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


    public ResponseEntity<MessageResponseDto> loginUser(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // JWT 생성
        String acessToken = jwtUtil.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getEmail(), user.getRole());

        jwtUtil.addJwtToCookie(acessToken, jwtUtil.ACCESS_HEADER, res);
        jwtUtil.addJwtToCookie(refreshToken,jwtUtil.REFRESH_HEADER, res);

        // Refresh Token 저장
        redisService.setValues(jwtUtil.substringToken(refreshToken), user.getEmail(), 60 * 30 * 100L);
        return new ResponseEntity<>(new MessageResponseDto("로그인 성공!", HttpStatus.OK.toString()), HttpStatus.OK);
    }

    public void test(HttpServletRequest request, HttpServletResponse res) {
        if(jwtUtil.validateAllToken(request, res)) System.out.println("성공");
        else System.out.println("실패");
    }

    public ResponseEntity<MessageResponseDto> logoutUser(HttpServletRequest request) {
        String accessToken = jwtUtil.getTokenFromRequest("Access", request);
        String refresh = jwtUtil.getTokenFromRequest("Refresh", request);
        Long expiration = jwtUtil.getExpiration(jwtUtil.substringToken(accessToken));
//        String email = jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(accessToken)).getSubject();

        System.out.println("refresh = " + refresh);

        if(!redisService.delValues(refresh)) {
            throw new IllegalArgumentException("삭제되지 않았습니다.");
        }

        redisService.setValues(jwtUtil.substringToken(accessToken), "logout", expiration);
        return new ResponseEntity<>(new MessageResponseDto("로그아웃 성공!", HttpStatus.OK.toString()), HttpStatus.OK);
    }
}
