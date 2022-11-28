package com.controller;

import com.dao.Audio;
import com.dao.Dao;
import com.dao.DaoPlaylist;
import com.model.PlayList;
import com.model.SongModel;
import com.view.AllSongs;
import com.view.Menus;

import java.util.List;
import java.util.Scanner;

public class PlaylistImpl {
    Menus mnu = new Menus();
    Dao dao = new Dao();
    DaoPlaylist daoPlaylist = new DaoPlaylist();
    String pL_Name;
    static Scanner scanner = new Scanner(System.in);
    Audio audio = new Audio();


    public void audioFileCollector(List<SongModel> songModels)
    {
        audio.playAllSongs(songModels);
    }
    public void PlaylistOptions()
    {
        do {
            int option = mnu.playlistMenu();
            AllSongs playDisplay = new AllSongs();
            List<PlayList> ply = dao.displayPlayList();
            switch (option) {
                case 1:
                    //create play list
                    createPlayList();
                    break;
                case 2:
                    // display playlist
                    playDisplay.playList(ply);
                    pL_Name = scanner.next();
                    /*boolean flag = false;
                    for (PlayList p : ply) {
                        if(pL_Name.equals(p.getPlayListName())) {
                            flag = true;
                            break;
                        }
                        System.out.println("PlayList Not Found");
                    }*/
                        List<Integer> sogId = dao.playListSongsId(pL_Name);
                        System.out.println("\t\t  ----------------------------------" + pL_Name + "-----------------------------------");
                        try {
                            if (sogId.get(1) > 0) {
                                List<SongModel> display = dao.displayPlayList(sogId);
                                playDisplay.showSongs(display);
                                mnu.audioPlayMenu();
                                System.out.println("Chose THe options");
                                int a = scanner.nextInt();
                                if( a == 1)
                                    audioFileCollector(display);
                                //else if (a == 2)

                            }
                        } catch (Exception ignored) {
//                            System.out.println("There is no such playlist or check the Spelling");
                        }
                    break;
                case 3://added editing to play list
                    int a = mnu.editPlayListMenu();
                    if(a ==1){                      //add songs to existing playlist
                        playDisplay.playList(ply);
                        pL_Name = scanner.next();
                        for (PlayList p : ply) {
                            if(pL_Name.equals(p.getPlayListName()))
                                addSongToPlayList();
                        }
                        System.out.println("PlayList Not Found");
                    } else if (a == 2) {            //delete songs to existing playlist
                        deleteSongsFormPlayList();//System.out.println("delete songs");
                    } else if (a == 3) {
                        deletePlayList();
                    }else System.out.println("wrong option");
                    break;
            }

            System.out.println("Would like to Retry (Press 1 for Retry) OR Exit (Press 0 for exit)");
        }while (scanner.nextInt()!=0);
    }
    public void createPlayList()
    {
        System.out.println("Enter PlayList Name Without Space:-");
        pL_Name= scanner.next();
        int a = daoPlaylist.createPlayList(pL_Name);
        //int a = dao.createPlayList(pL_Name);
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
            System.out.println("Enter the correct song_id From the above list or Press 0 to Exit");
            song_idU = scanner.nextInt();
            if (song_idU != 0) {
                if (dao.checkSong_id(song_idU)) {
                   daoPlaylist.addSongsToPlayList(pL_Name, song_idU);
                } else System.out.println("No such song id");
            } else {
                System.out.println("Exiting to the Main menu");
            }
        }while (song_idU>0);
    }

    public void deleteSongsFormPlayList()
    {
        AllSongs playDisplay = new AllSongs();
        List<PlayList> ply = dao.displayPlayList();
        playDisplay.playList(ply);
        pL_Name = scanner.next();
        List<Integer> sogId = dao.playListSongsId(pL_Name);
        System.out.println("\t\t  ----------------------------------"+pL_Name+"-----------------------------------");
        try {
            if (sogId.get(1) >0) {
                List<SongModel> display = dao.displayPlayList(sogId);
                playDisplay.showSongs(display);
                System.out.println("Enter the song_id");
                daoPlaylist.deleteSongsInPlayList(pL_Name,scanner.nextInt());
            }
        } catch (Exception ignored) {
            System.out.println("There is no such playlist or check the Spelling");
        }
    }
    public void deletePlayList()
    {
        AllSongs playDisplay = new AllSongs();
        List<PlayList> ply = dao.displayPlayList();
        playDisplay.playList(ply);
        pL_Name = scanner.next();
        daoPlaylist.deletePlayListString(pL_Name);
    }
}
