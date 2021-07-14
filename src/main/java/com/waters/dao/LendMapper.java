package com.waters.dao;

import com.waters.pojo.Lend;
import com.waters.pojo.UserLendBook;

import java.util.List;

public interface LendMapper {

    int insert(Lend lend);

    int update(Lend lend);

    int delete(Lend lend);

    List<UserLendBook> queryByUser(Lend lend);

    List<UserLendBook> queryByBook(Lend lend);

    List<UserLendBook> queryByBookAndUser(Lend lend);
}
