package com.dao;

import com.controller.SongMainImpl;

public class SongLoader implements Runnable{
    static Dao dps = new Dao();
    @Override
    public void run() {
        SongMainImpl.setSongModel(dps.getAllSongs());
    }
}
