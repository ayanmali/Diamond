package com.diamond.diamond.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Store a value with a key
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // Store a value with a key and expiration time
    public void setWithExpiration(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // Set the TTL for a given key
    public void expireKey(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    // Set a date for the key to expire at
    public void expireAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    // Retrieve a value by key
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Delete a key
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // Check if a key exists
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /* List Operations */

    // Add an element to the end of a list
    public Long rightPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    // Add an element to the beginning of a list
    public Long leftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    // Get all elements in a list
    public List<Object> getAllListElements(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // Get elements in a range (inclusive)
    public List<Object> getListRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // Get the length of a list
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    // Remove and get the first element of a list
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    // Remove and get the last element of a list
    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    // Get element at specific index
    public Object getListElement(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    // Remove elements from list
    public Long removeFromList(String key, Object value) {
        return redisTemplate.opsForList().remove(key, 0, value);
    }
} 