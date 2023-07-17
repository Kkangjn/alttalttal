package com.alttalttal.mini_project.delete.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MongoUserController {
    private final MongoUserService mongoUserService;

    @PostMapping("/mongo")
    public MongoUser createUser(){
        return mongoUserService.createUser();
    }
    @GetMapping("/mongo/{mongoId}")
    public MongoUser getUser(@PathVariable Long mongoId){
        BigInteger id = BigInteger.valueOf(mongoId);
        return mongoUserService.getUser(id);
    }
}
