package com.model;

import java.util.Arrays;

public class UserInfo {
    private static boolean IsLOGIN=false;
    private static int USER;
    private int user_id;
    private String user_name;
    private String user_pass;
    private byte[] salt;


    public UserInfo() {
    }

    public UserInfo(String user_name, String user_pass) {
        this.user_name = user_name;
        this.user_pass = user_pass;
    }

    public UserInfo(int user_id, String user_name, String user_pass, byte[] salt) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.salt = salt;
    }

    public static boolean isIsLOGIN() {
        return IsLOGIN;
    }

    public static void setIsLOGIN(boolean isLOGIN) {
        IsLOGIN = isLOGIN;
    }

    public static int getUSER() {
        return USER;
    }
    public static void setUSER(int USER) {
        UserInfo.USER = USER;
    }

    public int getUser_id() {
        return user_id;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pass='" + user_pass + '\'' +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
