package com.controller;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String a = "";
        Scanner s = new Scanner(System.in);
        a = s.next();
        if(!(a.equals("Exit")))
            System.out.println("false");
        else
            System.out.println("true");

        do{

        }while (!(a.equals("Exit")));
    }
}
