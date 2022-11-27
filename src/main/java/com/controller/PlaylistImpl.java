package com.controller;

import com.dao.Dao;
import com.moduel.PlayList;
import com.moduel.SongModel;
import com.view.AllSongs;
import com.view.Menus;

import java.util.List;
import java.util.Scanner;

public class PlaylistImpl {
    Menus mnu = new Menus();
    Dao dao = new Dao();
    String pL_Name;
    static Scanner scanner = new Scanner(System.in);
    public void PlaylistOptions()
    {
        do {

            int option = mnu.playlistMenu();
            switch (option) {
                case 1:
                    //create play list
                    createPlayList();
                    break;
                case 2:
                    // display playlist
                    List<PlayList> ply = dao.displayPlayList();
                    AllSongs playDisplay = new AllSongs();
                    playDisplay.playList(ply);
                    pL_Name = scanner.next();
                    List<Integer> sogId = dao.playListSongsId(pL_Name);
                    try {
                        if (sogId.get(1) > 0) {
                            List<SongModel> display = dao.displayPlayList(sogId);
                            playDisplay.showSongs(display);
                        }
                    } catch (Exception ignored) {
                        System.out.println("There is no such playlist or check the Spelling");
                    }
                    break;
            }
            System.out.println("Would like to Retry (Press 1 for exit) OR Exit (Press 0 for exit)");
        }while (scanner.nextInt()!=0);
    }
    public void createPlayList()
    {
        System.out.println("Enter PlayList Name Without Space:-");
        pL_Name= scanner.next();
        int a = dao.createPlayList(pL_Name);
        if(a == 0) {
            System.out.println("playlist is created");
            addSongToPlayList();
        } else{
            System.out.println("Playlist not created");
        }
    }
    public void addSongToPlayList()
    {
        int song_idU;
        List<SongModel> songmodel = dao.getAllSongs();
        AllSongs myview = new AllSongs();
        myview.showSongs(songmodel);
        do {
            System.out.println("Enter the correct song_id From the above list");
            song_idU = scanner.nextInt();
            if (song_idU != 0) {
                if (dao.checkSong_id(song_idU)) {
                   dao.addSongsToPlayList(pL_Name, song_idU);
                } else System.out.println("No such song id");
            } else {
                System.out.println("Exiting to the Main menu");
            }
            System.out.println("repeart song adding press 1");
        }while (scanner.nextInt() ==1);
    }
    public void showPlayList()
    {

    }

}
