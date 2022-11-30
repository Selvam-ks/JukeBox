package com.dao;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.*;

import static com.dao.Dao.getConnection;
public class DaoPlaylist {
    //ResultSet rs=null;
    Statement st=null;
   // ResultSet rs=null;
    Connection con = null;
    PreparedStatement pst;
    public int createPlayListNew(String playList_Name)          //without multi-table
    {
        con = getConnection();
        int tableUpdated = 0;
        System.out.println(playList_Name);
        try {
            st = con.createStatement();
            tableUpdated = st.executeUpdate("INSERT INTO `jukebox`.`playlist_name_list` (`playlist_name`) VALUES ('"+playList_Name+"')");
        }catch (Exception e)
        {
            System.out.println("The Name "+'"'+playList_Name+'"'+" is already taken");
        }
        return tableUpdated;
    }
    public void addSongsToPlayList(String pL_Name,int song_idU)         //without multi-table
    {
        con = getConnection();
        int rowAdded = 0;
        try {
            st = con.createStatement();
            pst = con.prepareStatement("INSERT INTO jukebox.playlist_collector (song_id,playlist_name) value (?,?)");
            pst.setInt(1,song_idU);
            pst.setString(2,pL_Name);
            rowAdded = pst.executeUpdate();
            System.out.println("Added Successfully "+rowAdded);
        }
        catch (Exception e)
        {
            System.out.println("The Song is already added to your playlist you cannot create duplicate entry");
        }
    }
    public void deleteSongsInPlayList(String pL_Name,int song_idU)          //without multi-table
    {
        con = getConnection();
        int tableUpdated = 0;
        try {
            st = con.createStatement();
            tableUpdated = st.executeUpdate("DELETE FROM jukebox.playlist_collector WHERE (`song_id` = "+song_idU+" and playlist_name = '"+pL_Name+"')");
        }catch (Exception e) {
            System.out.println(e);
        }
        if(tableUpdated !=0)
            System.out.println("Deleted Successfully");
        else System.out.println("data Not Found");
    }
    public void deletePlayListString(String pL_Name)
    {
        con = getConnection();
        int name_listdelete = 0;
        int collectorDelete = 0;
        try {
            st = con.createStatement();
            collectorDelete = st.executeUpdate("DELETE FROM jukebox.playlist_collector WHERE (`playlist_name` = '"+pL_Name+"')");
            name_listdelete = st.executeUpdate("DELETE FROM jukebox.playlist_name_list WHERE (`playlist_name` = '"+pL_Name+"')");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(name_listdelete+"   "+collectorDelete);
        if(name_listdelete >0 && collectorDelete >0)
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
        System.out.println(tableUpdated);
    }
}