package com.waters.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLendBook {
    private int lendID;
    private int userID;
    private int bookID;
    private String bookName;
    private String lendTime;
    private String returnTime;
    private String detail;
}