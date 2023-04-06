package com.controller;
import com.dao.Audio;
import com.dao.Dao;
import com.model.SongModel;
import com.view.SongsTableForm;
import com.view.Menus;

import java.util.*;

public class SongSearch {
    static Scanner src = new Scanner(System.in);
    static Dao dao = new Dao();
    Audio audio = new Audio();
    Menus mnu = new Menus();
    public void searchSongProcess(int a)
    {
        SongsTableForm myView = new SongsTableForm();
        //displayAllSongs();
        switch (a)
        {
            case 1://search by song name
                System.out.println("Enter The song Name");
                String s_name = src.next();
                s_name+=src.nextLine();
                List<SongModel> byName = dao.bySongName(s_name);
                myView.showSongs(byName);
                audio.playAllSongs(byName);
            break;
            case 2://search by song album
                mnu.menuForsearch(bySongAlbummenu(),"Album");
                System.out.println("Enter The song Album");
                String album = src.next();
                album+=src.nextLine();
                List<SongModel> byalbum = dao.bySongAlbum(album);
                myView.showSongs(byalbum);
                audio.playAllSongs(byalbum);
                break;
            case 3://search by song artist
                mnu.menuForsearch(bySongArtistmenu(),"Artist");
                System.out.println("Enter The song Artist");
                String artist = src.next();
                artist+=src.nextLine();
                List<SongModel> byartist = dao.bySongArtist(artist);
                myView.showSongs(byartist);
                audio.playAllSongs(byartist);
                break;
            case 4://search by song genera
                mnu.menuForsearch(bySongGenermenu(),"Genera");
                System.out.println("Enter The song Gener");
                String gener = src.next();
                gener+=src.nextLine();
                List<SongModel> bygener = dao.bySongGener(gener);
                myView.showSongs(bygener);
                audio.playAllSongs(bygener);
                break;
            case 5:
                System.out.println("enter name");
                String stWith = src.next();
                stWith+=src.nextLine();;
                List<SongModel> byStartWith = dao.bySongNameStartWith(stWith);
                myView.showSongs(byStartWith);
                audio.playAllSongs(byStartWith);
                break;
            default:
                //System.out.println("no suck input");
                break;
        }
    }
    public void displayAllSongs()
    {
        List<SongModel> songmodel = dao.getAllSongs();
        SongsTableForm myview = new SongsTableForm();
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