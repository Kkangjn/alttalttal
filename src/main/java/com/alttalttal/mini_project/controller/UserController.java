package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.LoginRequestDto;
import com.alttalttal.mini_project.dto.MessageResponseDto;
import com.alttalttal.mini_project.dto.SignUpRequestDto;
import com.alttalttal.mini_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> createUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.createUser(signUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.loginUser(loginRequestDto, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<MessageResponseDto> logoutUser(HttpServletRequest request){
        return userService.logoutUser(request);
    }
    @GetMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response){
        userService.test(request, response);
    }
}
