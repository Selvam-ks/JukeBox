package com.dao;
import com.moduel.SongModel;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.dao.Dao.getConnection;
public class DaoPlaylist {
    //ResultSet rs=null;
    Statement st=null;
    ResultSet rs=null;
    Connection con = null;
    PreparedStatement pst;
    public int createPlayList(String playList_Name)
    {
        con = getConnection();
        int tableCreated=1;
        try {
            st = con.createStatement();
            tableCreated = st.executeUpdate("create table "+playList_Name+" (playername VARCHAR(50) NOT NULL, song_id int NOT NULL,foreign key(song_id) references songs(song_id),PRIMARY KEY (`song_id`))");
            int rsr = st.executeUpdate("INSERT INTO `jukebox`.`playlist_name_list` (`Name`) VALUES ('"+playList_Name+"')");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return tableCreated;
    }
    public void addSongsToPlayList(String pL_Name,int song_idU)
    {
        con = getConnection();
        try {
            st = con.createStatement();
            int tableUpdated = st.executeUpdate("INSERT INTO jukebox."+pL_Name+ "(playername,song_id) value ('"+pL_Name+"',"+song_idU+")");
        }catch (MySQLQueryInterruptedException a)
        {
            System.out.println("Song_id "+song_idU+" Already added to play list");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void deleteSongsInPlayList(String pL_Name,int song_idU)
    {
        con = getConnection();
        int tableUpdated = 0;
        try {
            st = con.createStatement();
            tableUpdated = st.executeUpdate("DELETE FROM jukebox."+pL_Name+ " WHERE (`song_id` = "+song_idU+")");
        }catch (Exception e)
        {
            System.out.println(e);
        }
        if(tableUpdated !=0)
            System.out.println("Deleted Successfully");
        else System.out.println("data Not Found");
    }
    public void deletePlayListString(String pL_Name)
    {
        con = getConnection();
        int tableUpdated = 0;
        int tableDelete = 0;
        try {
            st = con.createStatement();
            tableUpdated = st.executeUpdate("DELETE FROM jukebox.playlist_name_list WHERE (`Name` = '"+pL_Name+"')");
            tableDelete = st.executeUpdate("drop table jukebox."+pL_Name);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if(tableUpdated ==1 && tableDelete ==0)
            System.out.println("PlayList Deleted Successfully");
        else System.out.println("data Not Found");
    }
    public void addSongToDataBase(String song_name, String album, String artist, String gener, double duration, String url)
    {
        con=getConnection();
        int tableUpdated = 0;
        try{
            pst = con.prepareStatement("insert into jukebox.songs (song_name,album,artist,gener,duration,url) VALUES (?,?,?,?,?,?)");
            pst.setString(1,song_name);
            pst.setString(2,album);
            pst.setString(3,album);
            pst.setDouble(4,duration);
            pst.setString(5,url);
            tableUpdated = pst.executeUpdate();
        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
    }
}
