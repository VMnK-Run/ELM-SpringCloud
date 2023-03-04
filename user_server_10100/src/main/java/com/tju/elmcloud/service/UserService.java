package com.tju.elmcloud.service;

import com.tju.elmcloud.po.User;
import com.tju.elmcloud.viewpo.UserInfo;

public interface UserService {

    public User getUserByIdByPass(User user);

    public int getUserById(String userId);

    public int saveUser(User user);

    public int updateUserById(User user);

    public UserInfo getUserInfoById(String userId);
}
