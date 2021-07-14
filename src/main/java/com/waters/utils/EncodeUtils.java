package com.waters.utils;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtils {
    public static String encoderByMd5(String str) {
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        return base64en.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean checkPassword(String newPasswd, String oldPasswd) {
        if (encoderByMd5(newPasswd).equals(oldPasswd))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        String psw = "admin";
        System.out.println(encoderByMd5(psw));
    }
}
