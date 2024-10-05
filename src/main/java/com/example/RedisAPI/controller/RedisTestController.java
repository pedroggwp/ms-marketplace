package com.example.RedisAPI.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/set")
    public String setRedisValue() {
        redisTemplate.opsForValue().set("myKey", "Hello Redis");
        return "Value set!";
    }

    @GetMapping("/get")
    public String getRedisValue() {
        return redisTemplate.opsForValue().get("myKey");
    }
}

