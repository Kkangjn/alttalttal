package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.LoginRequestDto;
import com.alttalttal.mini_project.dto.MessageResponseDto;
import com.alttalttal.mini_project.dto.SignUpRequestDto;
import com.alttalttal.mini_project.jwt.JwtUtil;
import com.alttalttal.mini_project.service.RedisService;
import com.alttalttal.mini_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final RedisService redisService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, RedisService redisService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.redisService = redisService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<MessageResponseDto> createUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        return userService.createUser(signUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.loginUser(loginRequestDto, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<MessageResponseDto> logoutUser(HttpServletRequest request){
        redisService.delValues(jwtUtil.getTokenFromRequest("Refresh", request));
        return new ResponseEntity<>(new MessageResponseDto("로그아웃 성공!", HttpStatus.OK.toString()), HttpStatus.OK);
    }
    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response){
        userService.test(request, response);
    }
}
