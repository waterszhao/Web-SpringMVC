package com.waters.dao;

import com.waters.pojo.User;

import java.util.List;

//users表映射类
public interface UserMapper {
    public int insert(User user);

    public int delete(int userID);

    public int update(User user);

    public User query(int userID);

    public List<User> queryAll();

    public User queryByName(String name);
}
