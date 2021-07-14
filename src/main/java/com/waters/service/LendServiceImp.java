package com.waters.service;

import com.waters.dao.LendMapper;
import com.waters.pojo.Lend;
import com.waters.pojo.UserLendBook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LendServiceImp implements LendService{

    LendMapper lendMapper;

    public void setLendMapper(LendMapper lendMapper) {
        this.lendMapper = lendMapper;
    }

    @Override
    public int lendBook(int bookID, int userID) {
        Lend bean = new Lend();
        bean.setBookID(bookID);
        bean.setUserID(userID);
        bean.setLendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return lendMapper.insert(bean);
    }

    @Override
    public int returnBook(int lendID) {
        Lend bean = new Lend();
        bean.setLendID(lendID);
        bean.setReturnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return lendMapper.update(bean);
    }

    @Override
    public List<UserLendBook> queryByUser(int userID) {
        Lend lend = new Lend();
        lend.setUserID(userID);
        return lendMapper.queryByUser(lend);
    }

    @Override
    public List<UserLendBook> queryByBook(int bookID) {
        Lend lend = new Lend();
        lend.setBookID(bookID);
        return lendMapper.queryByBook(lend);
    }

    @Override
    public List<UserLendBook> queryByBookAndUser(int bookID,int userID) {
        Lend lend = new Lend();
        lend.setBookID(bookID);
        lend.setUserID(userID);
        return lendMapper.queryByBookAndUser(lend);
    }
}
