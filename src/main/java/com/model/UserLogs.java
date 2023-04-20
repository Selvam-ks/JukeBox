package com.model;

import java.security.NoSuchAlgorithmException;

public interface UserLogs {
    int signUp(UserInfo userInfo) throws NoSuchAlgorithmException;
    boolean login(UserInfo userInfo);

}
