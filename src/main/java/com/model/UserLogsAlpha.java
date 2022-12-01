package com.model;

public interface UserLogsAlpha {

    abstract void newLogin();
    abstract void Login();
    abstract void passwordEncoder(String passR);
    abstract void passwordDecoder(String passEnc);

}
