package com.view;
import java.util.List;

import com.model.PlayList;
import  com.model.SongModel;

public class SongsTableForm {
    public void showSongs(List<SongModel> songList)
    {
        System.out.println("+--------------------------------------------------------------------------------------------+");
        System.out.format("%1s %-7s %1s %-20s %1s %-20s %1s %-15s %1s %-5s %1s %-8s %1s\n",'|',"Song ID",'|',"Song Name",'|',"Album",'|',"Artist",
                '|',"Gener",'|',"Duration",'|');
        System.out.println("+--------------------------------------------------------------------------------------------+");
        for (SongModel o : songList) {
            System.out.format("%1s %-7s %1s %-20s %1s %-20s %1s %-15s %1s %-5s %1s %-8s %1s\n",'|', o.getSong_id(),'|', o.getSong_name(),'|',
                    o.getAlbum(),'|', o.getArtist(),'|', o.getGener(),'|', o.getDuration(),'|');
        }
        System.out.println("+-------------------X-------------------------X---------------------X------------------------+");
    }
    public void playList(List<PlayList> playLists) {
        System.out.println("+------------\uD83D\uDD7A-----------------+");
        System.out.format("| %-11s | %-15s |\n","PlayList_id","PlayList");
        System.out.println("+-------------------------------+");
        if(playLists.size()==0){
            System.out.format("| %-29s |\n","No Play List");
        }
        for (PlayList o : playLists) {
            System.out.format("| %-11s | %-15s |\n",o.getSong_id_playlist(),o.getPlayListName());
        }
        System.out.println("---------X------X------X---------");
        if(playLists.size()!=0)
            System.out.println("Enter The Name of the PlayList");
    }
}
