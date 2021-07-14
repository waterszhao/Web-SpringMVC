package com.waters.service;

import com.waters.dao.LendMapper;
import com.waters.dao.UserMapper;
import com.waters.pojo.User;

import java.util.List;

//对应的实现类
public class UserServiceImp implements UserService {

    UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int delete(int userID) {
        return userMapper.delete(userID);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public User query(int userID) {
        return userMapper.query(userID);
    }

    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }

}
