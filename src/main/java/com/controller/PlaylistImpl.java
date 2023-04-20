package com.controller;

import com.dao.Audio;
import com.dao.Dao;
import com.dao.DaoPlaylist;
import com.model.PlayList;
import com.model.SongModel;
import com.view.SongsTableForm;
import com.view.Menus;
import java.util.List;
import java.util.Scanner;

public class PlaylistImpl {
    Menus mnu = new Menus();
    Dao dao = new Dao();
    DaoPlaylist daoPlaylist = new DaoPlaylist();
    String pL_Name;
    int pL_id;
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
            SongsTableForm playDisplay = new SongsTableForm();
            List<PlayList> ply = dao.displayPlayList();
            switch (option) {
                case 1 -> //create play list
                        createNewPlayList();
                case 2 -> { // View Existing Playlist
                    playDisplay.playList(ply);
                    if (ply.size() == 0)
                        break;
                    pL_Name = scanner.next();
                    this.pL_id = daoPlaylist.getPlayListId(pL_Name);
                    List<Integer> sogId = dao.getPlayListSongsId(pL_id);// getting all song id from the play table
                    System.out.println("\t\t  -------------------------------\uD83D\uDD7A" + pL_Name + "\uD83D\uDD7A--------------------------------");
                    try {
                        if (sogId.size() > 0) {
                            List<SongModel> display = dao.displayPlayList(sogId);
                            playDisplay.showSongs(display);
                            System.out.println("Chose THe options");
                            int a = mnu.audioPlayMenu();
                            if (a == 1)
                                audioFileCollector(display);
                            //else if (a == 2)
                        } else
                            System.out.println("No song found");
                    } catch (Exception ignored) {
//                            System.out.println("There is no such playlist or check the Spelling");
                    }
                }
                case 3 -> {//added editing to play list
                    int a = mnu.editPlayListMenu();
                    if (a == 1) {                      //add songs to existing playlist
                        playDisplay.playList(ply);
                        pL_Name = scanner.next();
                        this.pL_id = daoPlaylist.getPlayListId(pL_Name);
                        for (PlayList p : ply) {
                            if (pL_Name.equals(p.getPlayListName()))
                                addSongToPlayList();
                        }
                        System.out.println("PlayList Not Found");
                    } else if (a == 2) {            //delete songs to existing playlist
                        deleteSongsFormPlayList();//System.out.println("delete songs");
                    } else if (a == 3) {
                        deletePlayList();
                    } else System.out.println("wrong option");
                }
            }
            System.out.println("Would like to Retry (Press 1 for Retry) OR Exit to Main (Press 0 for exit)");
        }while (scanner.nextInt()!=0);
    }
    public void createNewPlayList()
    {
        System.out.println("Enter PlayList Name Without Space:-");
        pL_Name= scanner.next();
        int tableUpdate = daoPlaylist.createPlayListNew(pL_Name);
        //System.out.println(tableUpdate);
        if(tableUpdate == 1) {
            System.out.println("playlist is created Successfully for "+pL_Name);
            this.pL_id = daoPlaylist.getPlayListId(pL_Name);
            addSongToPlayList();
        }
    }
    public void addSongToPlayList()
    {
        int song_idU;
        List<SongModel> songmodel = dao.getAllSongs();
        SongsTableForm myview = new SongsTableForm();
        myview.showSongs(songmodel);
        do {
            System.out.println("Enter the correct song_id From the above list or Press 0 to Exit");
                song_idU = scanner.nextInt();
            if (song_idU != 0) {
                if (dao.checkSong_id(song_idU)) {
                   daoPlaylist.addSongsToPlayList(pL_id, song_idU);
                } else System.out.println("No such song id");
            } else {
                System.out.println("Exiting to the Main menu");
            }
        }while (song_idU>0);
    }
    public void deleteSongsFormPlayList()
    {
        SongsTableForm playDisplay = new SongsTableForm();
        List<PlayList> ply = dao.displayPlayList();
        playDisplay.playList(ply);
        pL_Name = scanner.next();
        this.pL_id = daoPlaylist.getPlayListId(pL_Name);
        List<Integer> sogId = dao.getPlayListSongsId(pL_id);
        System.out.println("\t\t  -------------------------------\uD83D\uDD7A"+pL_Name+"\uD83D\uDD7A--------------------------------");
        try {
            if (sogId.size() >0) {
                List<SongModel> display = dao.displayPlayList(sogId);
                playDisplay.showSongs(display);
                System.out.println("Enter the song_id");
                daoPlaylist.deleteSongsInPlayList(pL_id,scanner.nextInt());
            }else
                System.out.println("No song found");
        } catch (Exception ignored) {
            System.out.println("There is no such playlist or check the Spelling");
        }
    }
    public void deletePlayList()
    {
        SongsTableForm playDisplay = new SongsTableForm();
        List<PlayList> ply = dao.displayPlayList();
        playDisplay.playList(ply);
        pL_Name = scanner.next();
        daoPlaylist.deletePlayListString(daoPlaylist.getPlayListId(pL_Name));
    }
}
