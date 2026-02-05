package org.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.backend.dto.LoginReq;
import org.example.backend.dto.LoginResp;
import org.example.backend.entity.User;
import org.example.backend.exception.BusinessException;
import org.example.backend.mapper.UserMapper;
import org.example.backend.service.AuthService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private static final String LOGIN_KEY_PREFIX = "login:token:";
    private static final long LOGIN_TTL = 30;

    @Override
    public LoginResp login(LoginReq loginReq) {
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername, loginReq.getUsername())
        );

        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }

        if (!user.getPassword().equals(loginReq.getPassword())) {
            throw new BusinessException(400, "密码错误");
        }

        String token = UUID.randomUUID().toString().replace("-", "");

        String redisKey = LOGIN_KEY_PREFIX + token;
        stringRedisTemplate.opsForValue().set(
                redisKey,
                user.getId().toString(),
                LOGIN_TTL,
                TimeUnit.MINUTES
        );

        LoginResp loginResp = new LoginResp();
        loginResp.setUsername(loginReq.getUsername());
        loginResp.setToken(token);

        return loginResp;
    }

    @Override
    public void logout(String token) {
        stringRedisTemplate.delete(LOGIN_KEY_PREFIX + token);
    }
}
