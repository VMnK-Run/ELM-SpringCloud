package com.tju.elmcloud.service.impl;

import com.tju.elmcloud.mapper.UserMapper;
import com.tju.elmcloud.po.User;
import com.tju.elmcloud.service.UserService;
import com.tju.elmcloud.viewpo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByIdByPass(User user) {
        return userMapper.getUserByIdByPass(user);
    }

    @Override
    public int getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public UserInfo getUserInfoById(String userId) {
        User user = userMapper.getUserInfoById(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUserName(user.getUserName());
        userInfo.setUserSex(user.getUserSex());
        return userInfo;
    }
}
