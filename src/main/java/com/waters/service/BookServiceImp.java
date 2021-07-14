package com.waters.service;

import com.waters.dao.BookMapper;
import com.waters.pojo.Book;

//对应的实现类
import java.util.List;

public class BookServiceImp implements BookService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int insert(Book book) {
        return bookMapper.insert(book);
    }

    public int delete(int bookID) {
        return bookMapper.delete(bookID);
    }

    public int update(Book book) {
        return bookMapper.update(book);
    }

    public Book query(int bookID) {
        return bookMapper.query(bookID);
    }

    public List<Book> queryAll() {
        return bookMapper.queryAll();
    }

    public List<Book> queryByName(String name) {
        return bookMapper.queryByName(name);
    }
}
