package com;

import com.dao.Dao;
import com.view.AllSongs;

import java.util.List;

public class SongImpls {
    static Dao dps = new Dao();
    public static void main(String[] args) {
        System.out.println("welcome");
        List songmodel = dps.getAllSongs();
        AllSongs myview = new AllSongs();
        myview.showSongs(songmodel);
    }
}
