package com.waters.dao;

import com.waters.pojo.Book;

import java.util.List;

//books表映射类
public interface BookMapper {
    public int insert(Book book);

    public int delete(int bookID);

    public int update(Book book);

    public Book query(int bookID);

    public List<Book> queryAll();

    public List<Book> queryByName(String name);
}
