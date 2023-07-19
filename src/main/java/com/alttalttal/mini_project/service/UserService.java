package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.LoginRequestDto;
import com.alttalttal.mini_project.dto.MessageResponseDto;
import com.alttalttal.mini_project.dto.SignUpRequestDto;
import com.alttalttal.mini_project.dto.UserInfoResponseDto;
import com.alttalttal.mini_project.entity.User;
import com.alttalttal.mini_project.entity.UserRoleEnum;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        log.info("user = {}", user.getId());
        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        log.info("email = {}", user.getEmail());

        // JWT 생성
        String acessToken = jwtUtil.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getEmail(), user.getRole());
        log.info("acessToken = {}", acessToken);
        log.info("refreshToken = {}", refreshToken);

//        jwtUtil.addJwtToCookie(acessToken, jwtUtil.ACCESS_HEADER, res);
//        jwtUtil.addJwtToCookie(refreshToken,jwtUtil.REFRESH_HEADER, res);

        res.addHeader(JwtUtil.ACCESS_HEADER, acessToken);
        res.addHeader(JwtUtil.REFRESH_HEADER, refreshToken);


        // Refresh Token 저장
        redisService.setValues(jwtUtil.substringToken(refreshToken), user.getEmail(), 60 * 30 * 100L);
        return new ResponseEntity<>(new MessageResponseDto("로그인 성공!", HttpStatus.OK.toString()), HttpStatus.OK);
    }

    public void test(String accessToken, String refreshToken, HttpServletResponse response) {
        if(jwtUtil.validateAllToken(accessToken, refreshToken, response)) System.out.println("성공");
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
