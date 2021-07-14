package com.waters.service;

import com.waters.pojo.Book;

import java.util.List;

//Book服务抽象类
public interface BookService {

    public int insert(Book book);

    public int delete(int bookID);

    public int update(Book book);

    public Book query(int bookID);

    public List<Book> queryAll();

    public List<Book> queryByName(String name);
}
