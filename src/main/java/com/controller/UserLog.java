package com.controller;

import com.dao.UserDAO;
import com.model.UserInfo;
import java.util.Scanner;

public class UserLog{
    static UserDAO dao = new UserDAO();

//    static Scanner src = new Scanner(System.in);
    public int signUp(){
        Scanner src = new Scanner(System.in);
        UserInfo info = new UserInfo();
        int a;
        System.out.println("Welcome To Registration");
        System.out.println("Enter Username");
            String name= src.next();
            name+=src.nextLine();
            info.setUser_name(name);
            String password= passwordConform();
            info.setUser_pass(password);
            a = dao.signUp(info);
        return a;
    }
    public void login(){
        Scanner src = new Scanner(System.in);
        UserInfo info = new UserInfo();
        System.out.println("Welcome To Login");
        System.out.println("Enter Username");
        String name= src.next();
        name+=src.nextLine();
        System.out.println("Enter password");
        String pass= src.next();
        pass+=src.nextLine();
        info.setUser_pass(pass);
        info.setUser_name(name);
        dao.login(info);
//        System.out.println(info.isIsLOGIN()+" after same object  "+info.getUSER());
    }

    private String passwordConform(){
        Scanner src = new Scanner(System.in);
        String password=null;
        boolean flag;
        boolean fail = false;
        do{
            if(fail){
                System.out.println("Password Does Not Match Try again");
            }
            System.out.println("Enter password");
            String pass= src.next();
            pass+=src.nextLine();
            System.out.println("Conform Password");
            String passC= src.next();
            passC+=src.nextLine();
            if(pass.length()>=8){
                if (pass.equals(passC)){
                    flag = false;
                    password = pass;
                }else {
                    flag = true;
                    fail = true;
                }
            }else{
                flag = true;
                fail = true;
            }
        }while (flag);
        return password;
    }
}
