package org.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.backend.dto.UserReq;
import org.example.backend.dto.UserQueryResp;
import org.example.backend.entity.User;
import org.example.backend.exception.BusinessException;
import org.example.backend.mapper.UserMapper;
import org.example.backend.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long addUser(UserReq userReq) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userReq.getUsername());
        if (userMapper.selectCount(queryWrapper) > 0) {
            throw new BusinessException(400, "用户名已存在");
        }
        User user = new User();
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setCreateTime(LocalDateTime.now()); // 防止数据库时区不同导致时间错误
        int line = userMapper.insert(user); // 返回受影响的行数
        if (line == 1) {
            return user.getId();
        } else {
            throw new BusinessException(404, "无法插入新用户");
        }
    }

    @Override
    public UserQueryResp selectUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            UserQueryResp userRes = new UserQueryResp();
            userRes.setUsername(user.getUsername());
            userRes.setCreateTime(user.getCreateTime());
            return userRes;
        } else {
            throw new BusinessException(404, "未查找到此人");
        }
    }

    @Override
    public void updateUserById(Long id, UserReq userReq) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(404, "未找到该用户");
        }
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        userMapper.updateById(user);
    }
}
