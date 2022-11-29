package com.dao;

import com.model.PlayList;
import com.model.SongModel;
import com.model.Search;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Dao implements Search {
    static Connection con = null;
    ResultSet rs=null;
    Statement st=null;
    SongModel songobj;
    public static Connection getConnection()
    {
        try{//it will load the driver and create a connectivity
            Class.forName("com.mysql.cj.jdbc.Driver");  //loading the driver
            //System.out.println("driver loader");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","root"); //connection establishing
            //System.out.println("Connected");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    public List<SongModel> getAllSongs()
    {
        con=getConnection();
        List<SongModel> allSongList = new ArrayList<>();
        int song_id;
        String song_name;
        String album;
        String artist;
        String gener;
        double duration;
        String url;
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs");
            while (rs.next()) {
                song_id = rs.getInt("song_id");
                song_name = rs.getString("song_name");
                album = rs.getString("album");
                artist  =rs.getString(4);
                gener = rs.getString(5);
                duration = rs.getDouble("duration");
                url = rs.getString(7);
                songobj = new SongModel(song_id,song_name,album,artist,gener,duration,url);
                allSongList.add(songobj);
            }
        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return allSongList;
    }
    @Override
    public List<SongModel> bySongName(String s_name)
    {
        con=getConnection();
        List<SongModel> bySongNameList = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs where song_name ='"+s_name+"'");
            while (rs.next()) {
                songobj = new SongModel(rs.getInt("song_id"),rs.getString("song_name"),rs.getString("album"),
                        rs.getString(4), rs.getString(5), rs.getDouble("duration"),rs.getString("url"));
                bySongNameList.add(songobj);
            }

        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
       return bySongNameList;
    }
    @Override
    public List<SongModel> bySongAlbum(String album)
    {
        con=getConnection();
        List<SongModel> bySongAlbumList = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs where album ='"+album+"'");
            while (rs.next()) {
                songobj = new SongModel(rs.getInt("song_id"),rs.getString("song_name"),rs.getString("album"),
                        rs.getString(4), rs.getString(5), rs.getDouble("duration"),rs.getString("url"));
                bySongAlbumList.add(songobj);
            }

        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return bySongAlbumList;
    }
    @Override
    public List<SongModel> bySongArtist(String artist)
    {
        con=getConnection();
        List<SongModel> bySongArtistList = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs where artist ='"+artist+"'");
            while (rs.next()) {
                songobj = new SongModel(rs.getInt("song_id"),rs.getString("song_name"),rs.getString("album"),
                        rs.getString(4), rs.getString(5), rs.getDouble("duration"),rs.getString("url"));
                bySongArtistList.add(songobj);
            }
        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return bySongArtistList;
    }
    @Override
    public List<SongModel> bySongGener(String gener)
    {
        con=getConnection();
        ArrayList<SongModel> bySongArtistList = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs where gener ='"+gener+"'");
            while (rs.next()) {
                songobj = new SongModel(rs.getInt("song_id"),rs.getString("song_name"),rs.getString("album"),
                        rs.getString(4), rs.getString(5), rs.getDouble("duration"),rs.getString("url"));
                bySongArtistList.add(songobj);
            }
        }catch (Exception e) {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return bySongArtistList;
    }
    /*public List<Integer> getPlayListSongsId(String playList)
    {
        con = getConnection();
        List<Integer> list = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select song_id from jukebox."+playList);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        }catch (Exception e)
        {
            System.out.println("There is No such table");
        }
        return list;
    }*/
    public List<Integer> getPlayListSongsId(String playList)
    {
        con = getConnection();
        List<Integer> list = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select song_id from jukebox."+playList);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        }catch (Exception e)
        {
            System.out.println("There is No such table");
        }
        return list;
    }
    public List<SongModel> displayPlayList(List<Integer> songId)
    {
        con=getConnection();
        List<SongModel> PlayListSongs = new ArrayList<>();
        try{
            Iterator<Integer> i = songId.listIterator();
            while (i.hasNext()) {
                st = con.createStatement();
                rs = st.executeQuery("select * from jukebox.songs where song_Id ="+i.next());
                while (rs.next()) {
                    songobj = new SongModel(rs.getInt("song_id"), rs.getString("song_name"), rs.getString("album"),
                            rs.getString(4), rs.getString(5), rs.getDouble("duration"), rs.getString("url"));
                    PlayListSongs.add(songobj);
                }
            }
        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return  PlayListSongs;
    }
    public boolean checkSong_id(int song_idU)
    {
        boolean flag = false;
        con=getConnection();
        int song_id;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs");
            while (rs.next()) {
                song_id = rs.getInt("song_id");
                if(song_idU==song_id) {
                    flag = true;
                    break;}
            }
        }catch (Exception e) {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return flag;
    }
    public ArrayList<PlayList> displayPlayList()
    {
        con=getConnection();
        ArrayList<PlayList> plylst  = new ArrayList<>();
        PlayList ply;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM jukebox.playlist_name_list"); //change this to manipulate the table
            while (rs.next()) {
                ply = new PlayList(rs.getInt("playlist_id"),rs.getString(2));
                plylst.add(ply);
            }
        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return plylst;
    }
}
