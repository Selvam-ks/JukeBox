package com.dao;
import com.model.UserInfo;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.*;

import static com.dao.Dao.getConnection;
import static com.model.UserInfo.getUSER;
public class DaoPlaylist {
    ResultSet rs=null;
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

    public int getPlayListId(String playList_Name){
        con = getConnection();
        int playList_Id = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from `jukebox`.`playlist_name_list` where playlist_name ='"+playList_Name+"'");
            while (rs.next()){
                playList_Id = rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println("getPlayListId");
        }
        return playList_Id;
    }
    public void addSongsToPlayList(int pL_Id,int song_idU)         //without multi-table // testing user collector
    {
        con = getConnection();
        int rowAdded = 0;
        try {
            st = con.createStatement();
            pst = con.prepareStatement("INSERT INTO jukebox.playlist_collector (song_id,playlist_id,user_id) value (?,?,?)");
            pst.setInt(1,song_idU);
            pst.setInt(2,pL_Id);
            pst.setInt(3,getUSER());
            rowAdded = pst.executeUpdate();
            System.out.println("Added Successfully "+rowAdded);
        }
        catch (Exception e)
        {
            System.out.println("The Song is already added to your playlist you cannot create duplicate entry   "+e.getMessage());
        }
    }
    public void deleteSongsInPlayList(int pL_id,int song_idU)          //without multi-table
    {
        con = getConnection();
        int tableUpdated = 0;
        try {
            st = con.createStatement();
            tableUpdated = st.executeUpdate("DELETE FROM jukebox.playlist_collector WHERE (`song_id` = "+song_idU+" and playlist_id = "+pL_id+")");
        }catch (Exception e) {
            System.out.println(e);
        }
        if(tableUpdated !=0)
            System.out.println("Deleted Successfully");
        else System.out.println("data Not Found");
    }
    public void deletePlayListString(int pL_id)
    {
        con = getConnection();
        int name_listdelete = 0;
        int collectorDelete = 0;
        try {
            st = con.createStatement();
            collectorDelete = st.executeUpdate("DELETE FROM jukebox.playlist_collector WHERE (`playlist_id` = "+pL_id+")");
            name_listdelete = st.executeUpdate("DELETE FROM jukebox.playlist_name_list WHERE (`playlist_id` = "+pL_id+")");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if(name_listdelete >0 && collectorDelete >0)
            System.out.println("PlayList Deleted Successfully");
        else System.out.println("data Not Found");
    }
    //under BETA testing
    /*public void addSongToDataBase(String song_name, String album, String artist, String gener, double duration, String url)
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
    }*/
}