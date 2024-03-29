package com.dao;

import com.model.PlayList;
import com.model.SongModel;
import com.model.SearchSongsBy;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.model.UserInfo.getUSER;

public class Dao implements SearchSongsBy {
    static Connection con = null;
    ResultSet rs=null;
    Statement st=null;
    SongModel songobj;
    PreparedStatement pst;
    public static Connection getConnection(){
        try{//it will load the driver and create a connectivity
            Class.forName("com.mysql.cj.jdbc.Driver");  //loading the driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","root"); //connection establishing
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    public List<SongModel> getAllSongs() {
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
            con.close();
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
            con.close();
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
            con.close();
        }catch (Exception e) {
            System.out.println("The Exceptions..."+e.getMessage());
        }return bySongAlbumList;
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
            con.close();
        }catch (Exception e) {
            System.out.println("The Exceptions..."+e.getMessage());
        }return bySongArtistList;
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
            con.close();
        }catch (Exception e) {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return bySongArtistList;
    }

    @Override
    public List<SongModel> bySongNameStartWith(String strWith) {
        con=getConnection();
        List<SongModel> bySongNameList = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.songs where song_name LIKE'"+strWith+"%'");
            while (rs.next()) {
                songobj = new SongModel(rs.getInt("song_id"),rs.getString("song_name"),rs.getString("album"),
                        rs.getString(4), rs.getString(5), rs.getDouble("duration"),rs.getString("url"));
                bySongNameList.add(songobj);
            }
            con.close();
        }catch (Exception e)
        {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return bySongNameList;
    }

    public List<Integer> getPlayListSongsId(int play_id)        // without multi table
    {con = getConnection();
        List<Integer> list = new ArrayList<>();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select song_id from jukebox.playlist_collector where playlist_id = '"+play_id+"'");
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            con.close();
        }catch (Exception e) {
            System.out.println("There is No such table");
        }
        return list;
    }
    public List<SongModel> displayPlayList(List<Integer> songId){
        con=getConnection();
        List<SongModel> PlayListSongs = new ArrayList<>();
        try{
            String inSql = String.join(",", Collections.nCopies(songId.size(),"?"));
            System.out.println(inSql);
            for (Integer integer : songId) {
                st = con.createStatement();
                rs = st.executeQuery("select * from jukebox.songs where song_Id =" + integer);
                while (rs.next()) {
                    songobj = new SongModel(rs.getInt("song_id"), rs.getString("song_name"), rs.getString("album"),
                            rs.getString(4), rs.getString(5), rs.getDouble("duration"), rs.getString("url"));
                    PlayListSongs.add(songobj);
                }
                con.close();
            }
        }catch (Exception e) {
            System.out.println("The Exceptions..."+e.getMessage());
        }
        return  PlayListSongs;
    }
    public List<SongModel> displayPlayList1(List<Integer> songId){

        List<SongModel> PlayListSongs = new ArrayList<>();
        try{
            con=getConnection();
//            String inSql = String.join(",", Collections.nCopies(songId.size(),"?"));
            st = con.createStatement();
            pst = con.prepareStatement("select * from jukebox.songs where (song_Id) in (?)");
            pst.setAsciiStream(1, (InputStream) songId,songId.size());
            con.close();
        }catch (Exception e) {
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
            con.close();
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
        List<Integer> play_id = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT distinct playlist_id FROM jukebox.playlist_collector where user_id ="+getUSER());
            while (rs.next()) {
                play_id.add(rs.getInt("playlist_id"));
            }
            String inClause = play_id.stream().map(String::valueOf).collect(Collectors.joining(","));
            rs = st.executeQuery("SELECT * FROM jukebox.playlist_name_list WHERE playlist_id in ("+inClause+")"); //change this to manipulate the table
            while (rs.next()) {
                ply = new PlayList(rs.getInt("playlist_id"),rs.getString(2));
                plylst.add(ply);
            }
            con.close();
        }catch (Exception e) {
            System.out.println("No PLAYLIST");
        }
        return plylst;
    }
    public void updateFileReader()
    {
        String filePath = "src/main/resources/update_info";
        try(
                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr)
                ) {
            String line;
            while ((line = br.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
