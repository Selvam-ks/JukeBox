package com.controller;

import com.dao.Audio;
import com.dao.Dao;
import com.moduel.SongModel;
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
        Menus mnu = new Menus();
        mnu.welcome();
        do {
            int option = mnu.menu();
            switch (option)
            {
                case 1:
                    //display all songs
                    List<SongModel> songmodel = dps.getAllSongs();
                    AllSongs myview = new AllSongs();
                    myview.showSongs(songmodel);
                    Audio audio = new Audio();
                    audio.playAllSongs(songmodel);
                break;
                case 2:
                    songSearch.searchSongProcess(mnu.searchSongList());
                break;
                case 3:
                    plyLst.PlaylistOptions();
                break;
                default:
                    System.out.println("thanyou");
                break;
            }
            System.out.println("Would you like to Repeat Or Exit [Y/N]");
        }while (src.next().equals("Y"));
    }
}
