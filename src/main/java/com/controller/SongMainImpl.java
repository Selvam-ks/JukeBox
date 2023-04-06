package com.controller;

import com.dao.Audio;
import com.dao.Dao;
import com.dao.DaoPlaylist;
import com.dao.UserDAO;
import com.model.SongModel;
import com.model.UserInfo;
import com.view.SongsTableForm;
import com.view.Menus;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SongMainImpl {
    static Dao dps = new Dao();
    static UserLog userLog = new UserLog();
    static SongSearch songSearch = new SongSearch();
    static Scanner src = new Scanner(System.in);
    static PlaylistImpl plyLst = new PlaylistImpl();
    public static void main(String[] args) {
        UserInfo info  = new UserInfo();
        String ss = "N";
        Menus mnu = new Menus();
        Audio audio = new Audio();
        SongMainImpl smipl = new SongMainImpl();
        mnu.welcome();
        boolean repert = false;
        boolean exit = false;
        if(userManage())
            if (info.isIsLOGIN()){
                userLoggedIn();
            }
        else {
            do {
                int option = mnu.menu();
                switch (option)
                {
                    case 1://display all songs
                        List<SongModel> songmodel = dps.getAllSongs();
                        SongsTableForm myview = new SongsTableForm();
                        myview.showSongs(songmodel);
                        int a = mnu.audioPlayMenu();
                        if(a == 1)
                            audio.playAllSongs(songmodel);
                        repert=false;
                        ss="Y";
                        break;
                    case 2://Search Song
                        songSearch.displayAllSongs();
                        songSearch.searchSongProcess(mnu.searchSongList());
                        repert=false;
                        ss="Y";
                        break;
                    case 3://Show Playlist
                        if(info.isIsLOGIN()){
                            plyLst.PlaylistOptions();
                        }else {
                            System.out.println("Pls login");
                            userManage();
                        }
                        repert=false;
                        ss="N";
                        break;
                    case 4:
                        userManage();
                        break;
                    case 5:// update information
                        dps.updateFileReader();
                        repert = true;
                        break;
                    case 6://Exit
                        repert = true;
                        exit =true;
                        break;
                    case 1985://add songs to table
                        smipl.addSongToDatabase();
                        break;
                    default:
                        System.out.println("No such option");
                        break;
                }
                if(repert)
                    if(!exit){
                    System.out.println("Would you like to Repeat The Main menu Or Exit [Y/N]");
                    ss = src.next().toUpperCase();
                }else {
                    System.out.println("Conform Exit in menu [Y/N]");
                    ss = src.next().toUpperCase();
                    if(ss.equals("Y"))
                        ss = "N";
                    else
                        ss = "Y";
                }
            }while (ss.equals("Y"));
        }

        mnu.exitmsg();
    }

    public static boolean userManage(){
        UserInfo info  = new UserInfo();
        String ss = "N";
        Menus mnu = new Menus();
        boolean repert = false;
        boolean exit = false;
        boolean demo = true;
        if (!info.isIsLOGIN())
            do {
                int option = mnu.userLoginMenu();
                switch (option) {
                    case 1 ->{ // signUp
                        int a;
                        boolean flag=true;
                        do{
                            a = userLog.signUp();
                            if(a == 1){
                                flag= false;
                                userLog.login();
                                if(info.isIsLOGIN()){
                                    userLoggedIn();
                                }else {
                                    repert = false;
                                    ss = "Y";
                                }
                            }else {
                                System.out.println("Would like to re-try [Y/N]");
                                if(src.next().equalsIgnoreCase("N")){
                                    flag = false;
                                    repert = true;
                                }
                            }
                        }while (flag);
                        System.out.println(a);
                    }
                    case 2 -> {// LogIn
                        userLog.login();
                        if(info.isIsLOGIN()){
                            userLoggedIn();
                        }else {
                            repert = false;
                            ss = "Y";
                        }
                    }
                    case 3 ->// demo user
                    {
                        repert = false;
                        ss = "N";
                    }
                    case 4 -> {// update information
                        dps.updateFileReader();
                        repert = true;
                    }
                    case 5 -> {//Exit
                        repert = true;
                        exit = true;
                    }
                    default -> System.out.println("No such option");
                }
                if(repert)
            if(!exit){
                    System.out.println("Would you like to Repeat The Main menu [Y/N]");
                    ss = src.next().toUpperCase();
                }else {
                System.out.println("Conform Exit in User [Y/N]");
                ss = src.next().toUpperCase();
                if(ss.equals("Y")){
                    ss = "N";
                    demo = false;
                }
                else
                    ss = "Y";
            }
            }while (ss.equals("Y"));
        return demo;
    }
    public static void userLoggedIn(){
        UserInfo info  = new UserInfo();
        String ss = "N";
        Menus mnu = new Menus();
        Audio audio = new Audio();
        SongMainImpl smipl = new SongMainImpl();
        boolean repert = false;
        String logOut ="";
        if (info.isIsLOGIN())
            do {
                int option = mnu.userMenu();
                switch (option)
                {
                    case 1://display all songs
                        List<SongModel> songmodel = dps.getAllSongs();
                        SongsTableForm myview = new SongsTableForm();
                        myview.showSongs(songmodel);
                        int a = mnu.audioPlayMenu();
                        if(a == 1)
                            audio.playAllSongs(songmodel);
                        repert=false;
                        ss="Y";
                        break;
                    case 2://Search Song
                        songSearch.displayAllSongs();
                        songSearch.searchSongProcess(mnu.searchSongList());
                        repert=false;
                        ss="Y";
                        break;
                    case 3://Show Playlist
                        if(info.isIsLOGIN()){
                            plyLst.PlaylistOptions();
                        }
                        repert=false;
                        ss="Y";
                        break;
                    case 4:// update information
                        dps.updateFileReader();
                        repert = false;
                        ss="Y";
                        break;
                    case 5://LogOut
                        System.out.println("Would you like to logOut [Y/N]");
                        String lg = src.next().toUpperCase();
                        if(lg.equals("Y")){
                            info.setIsLOGIN(false);
                            info.setUSER(0);
                            repert=false;
                            ss="N";
                        }else {
                            repert=false;
                            ss="Y";
                        }
                        break;
                    case 1985://add songs to table
                        smipl.addSongToDatabase();
                        repert=true;
                        break;
                    default:
                        System.out.println("No such option");
                        break;
                }
                if(repert){
                    System.out.println("Would you like to Repeat The Main menu Or Exit [Y/N]");
                    ss = src.next().toUpperCase();
                }
            }while (ss.equals("Y"));
        System.out.println("Logging OUT");
    }

    // it is not working prop 'URL' under Alpha Programing not for public use.
    public void addSongToDatabase() {
        DaoPlaylist daPly = new DaoPlaylist();
        String song_name;
        String album;
        String artist;
        String gener;
        double duration;
        String url;
        System.out.println("The song should be in .wav format");
        System.out.println("Security Key");
        if(src.nextInt() == 5891) {
            System.out.println("Enter the required details correctly instred of space use underscore ''_''");
            System.out.println("Enter song name");
            song_name = src.next();
            System.out.println("Enter album name");
            album = src.next();
            System.out.println("Enter artist name");
            artist = src.next();
            System.out.println("Enter the genera");
            gener = src.next();
            System.out.println("Enter the Duration of song like example 4.13");
            duration =src.nextDouble();
            System.out.println("Enter the URL Correctly**");
            url = src.next();
            //daPly.addSongToDataBase(song_name,album,artist,gener,duration,url);
        }
    }
}