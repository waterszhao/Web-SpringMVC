package com.waters.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lend {
    private int lendID;
    private int userID;
    private int bookID;
    private String lendTime;
    private String returnTime;
}
