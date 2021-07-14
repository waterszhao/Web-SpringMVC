package com.waters.service;

import com.waters.pojo.User;

import java.util.List;

//User服务抽象类
public interface UserService {
    public int insert(User user);

    public int delete(int userID);

    public int update(User user);

    public User query(int userID);

    public List<User> queryAll();

    public User queryByName(String name);
}
