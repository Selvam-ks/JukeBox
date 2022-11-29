package com.controller;
import com.dao.Audio;
import com.dao.Dao;
import com.model.SongModel;
import com.view.AllSongs;
import com.view.Menus;

import java.util.*;

public class SongSearch {
    static Scanner src = new Scanner(System.in);
    static Dao dao = new Dao();
    Audio audio = new Audio();
    Menus mnu = new Menus();
    public void searchSongProcess(int a)
    {
        AllSongs myView = new AllSongs();
        //displayAllSongs();
        switch (a)
        {
            case 1:
                System.out.println("Enter The song Name");
                String s_name = src.nextLine();
                List<SongModel> byName = dao.bySongName(s_name);
                myView.showSongs(byName);
                audio.playAllSongs(byName);
            break;
            case 2:
                mnu.menuForsearch(bySongAlbummenu());
                System.out.println("Enter The song Album");
                String album = src.nextLine();
                List<SongModel> byalbum = dao.bySongAlbum(album);
                myView.showSongs(byalbum);
                audio.playAllSongs(byalbum);
                break;
            case 3:
                mnu.menuForsearch(bySongArtistmenu());
                System.out.println("Enter The song Artist");
                String artist = src.nextLine();
                List<SongModel> byartist = dao.bySongArtist(artist);
                myView.showSongs(byartist);
                audio.playAllSongs(byartist);
                break;
            case 4:
                mnu.menuForsearch(bySongGenermenu());
                System.out.println("Enter The song Gener");
                String gener = src.nextLine();
                List<SongModel> bygener = dao.bySongGener(gener);
                myView.showSongs(bygener);
                audio.playAllSongs(bygener);
                break;
            default:
                //System.out.println("no suck input");
                break;
        }
    }
    public void displayAllSongs()
    {
        List<SongModel> songmodel = dao.getAllSongs();
        AllSongs myview = new AllSongs();
        myview.showSongs(songmodel);
    }
    public TreeSet<String> bySongAlbummenu()
    {
        List<SongModel> songmodel = dao.getAllSongs();
        TreeSet<String> setAlbum = new TreeSet<>();
        for (SongModel songModel : songmodel) {
            setAlbum.add(songModel.getAlbum());
        }
        return setAlbum;
    }
    public TreeSet<String> bySongArtistmenu() {
        List<SongModel> songmodel = dao.getAllSongs();
        TreeSet<String> setArtist = new TreeSet<>();
        for (SongModel songModel : songmodel) {
            setArtist.add(songModel.getArtist());
        }
        return setArtist;
    }
    public TreeSet<String> bySongGenermenu()
    {
        List<SongModel> songmodel = dao.getAllSongs();
        TreeSet<String> setGener = new TreeSet<>();
        for (SongModel songModel : songmodel) {
            setGener.add(songModel.getGener());
        }
        return  setGener;
    }
}
