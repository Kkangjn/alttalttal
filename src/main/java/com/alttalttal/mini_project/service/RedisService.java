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
    public void setValues(String token, String email, Long expiration){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(token, email, expiration);
    }

    // key-value 가져오기
    public String getValue(String token){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(token);
    }

    // key-value 삭제
    public boolean delValues(String token){
       return redisTemplate.delete(token.substring(7));
    }

}
