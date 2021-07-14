package com.waters.service;

import com.waters.pojo.UserLendBook;

import java.util.List;

public interface LendService {

    int lendBook(int bookID,int userID);

    int returnBook(int lendID);

    List<UserLendBook> queryByUser(int userID);

    List<UserLendBook> queryByBook(int bookID);

    List<UserLendBook> queryByBookAndUser(int bookID,int userID);
}
