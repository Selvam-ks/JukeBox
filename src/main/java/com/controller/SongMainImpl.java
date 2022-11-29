package com.controller;

import com.dao.Audio;
import com.dao.Dao;
import com.model.SongModel;
import com.view.AllSongs;
import com.view.Menus;

import java.util.List;
import java.util.Scanner;

public class SongMainImpl {
    static Dao dps = new Dao();
    static SongSearch songSearch = new SongSearch();
    static Scanner src = new Scanner(System.in);
    static PlaylistImpl plyLst = new PlaylistImpl();
    public static void main(String[] args) {
        String ss;
        Menus mnu = new Menus();
        Audio audio = new Audio();
        mnu.welcome();
        do {
            int option = mnu.menu();
            switch (option)
            {
                case 1://display all songs
                    List<SongModel> songmodel = dps.getAllSongs();
                    AllSongs myview = new AllSongs();
                    myview.showSongs(songmodel);
                    int a = mnu.audioPlayMenu();
                    if(a == 1)
                        audio.playAllSongs(songmodel);
                break;
                case 2://Search Song
                    songSearch.displayAllSongs();
                    songSearch.searchSongProcess(mnu.searchSongList());
                break;
                case 3://Show Playlist
                    plyLst.PlaylistOptions();
                break;
                case 4://Exit
                    break;
                default:
                    System.out.println("No such option");
                break;
            }
                System.out.println("Would you like to Repeat The Main menu Or Exit [Y/N]");
                ss = src.next().toUpperCase();
        }while (ss.equals("Y"));
        mnu.exitmsg();
    }
}