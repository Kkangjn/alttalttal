package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.MainPageResponseDto;
import com.alttalttal.mini_project.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageController {
    private final MainPageService mainPageService;

    @GetMapping
    public MainPageResponseDto getMainPage(){
        return mainPageService.getMainPage();
    }

}
