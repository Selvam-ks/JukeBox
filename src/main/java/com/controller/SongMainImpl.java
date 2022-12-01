package com.controller;

import com.dao.Audio;
import com.dao.Dao;
import com.dao.DaoPlaylist;
import com.model.SongModel;
import com.view.SongsTableForm;
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
        SongMainImpl smipl = new SongMainImpl();
        mnu.welcome();
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
                break;
                case 2://Search Song
                    songSearch.displayAllSongs();
                    songSearch.searchSongProcess(mnu.searchSongList());
                break;
                case 3://Show Playlist
                    plyLst.PlaylistOptions();
                break;
                case 4:// update information
                    dps.updateFileReader();
                    break;
                case 5://Exit
                    break;
                case 1985://add songs to table
                    smipl.addSongToDatabase();
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