package com.dao;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.*;

import static com.dao.Dao.getConnection;
public class DaoPlaylist {
    //ResultSet rs=null;
    Statement st=null;
    ResultSet rs=null;
    Connection con = null;
    PreparedStatement pst;
    /*public int createPlayListNew(String playList_Name)    // with multiple tables
    {
        con = getConnection();
        int tableCreated=1;
        try {
            st = con.createStatement();
            tableCreated = st.executeUpdate("create table "+playList_Name+" (playername VARCHAR(50) NOT NULL, song_id int NOT NULL,foreign key(song_id) references songs(song_id),PRIMARY KEY (`song_id`))");
            int rsr = st.executeUpdate("INSERT INTO `jukebox`.`playlist_name_list1` (`Name`) VALUES ('"+playList_Name+"')");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return tableCreated;
    }*/
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

    /*public void addSongsToPlayList(String pL_Name,int song_idU)  // with multiple tables
    {
        con = getConnection();
        try {
            st = con.createStatement();
            int tableUpdated = st.executeUpdate("INSERT INTO jukebox."+pL_Name+ "(playername,song_id) value ('"+pL_Name+"',"+song_idU+")");
            System.out.println("Added Successfully");
        }
        catch (Exception e)
        {
            System.out.println("The Song is already added to your playlist you cannot create duplicate entry");
        }
    }*/
    public void addSongsToPlayList(String pL_Name,int song_idU)         //without multi-table
    {
        con = getConnection();
        int rowAdded = 0;
        try {
            st = con.createStatement();
            //rowAdded = st.executeUpdate("INSERT INTO jukebox.jukebox.playlist_collect (song_id,playlist_name) value ('"+song_idU+"',"+pL_Name+")");
            pst = con.prepareStatement("INSERT INTO jukebox.playlist_collect (song_id,playlist_name) value (?,?)");
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