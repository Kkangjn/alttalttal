package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;


    // key-value 설정
    public void setValues(String token, String email){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(token, email, JwtUtil.REFRESH_TOKEN_TIME);
    }

    // key-value 가져오기
    public String getValue(String token){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(token);
    }

    // key-value 삭제
    public void delValues(String token){
        redisTemplate.delete(token.substring(7));
    }

}
