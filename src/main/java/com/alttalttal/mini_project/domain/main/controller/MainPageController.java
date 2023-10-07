package com.alttalttal.mini_project.domain.main.controller;

import com.alttalttal.mini_project.domain.main.dto.MainPageResponseDto;
import com.alttalttal.mini_project.domain.main.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(exposedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageController {
    private final MainPageService mainPageService;

    @GetMapping
    public MainPageResponseDto getMainPage(){
        return mainPageService.getMainPage();
    }

}
