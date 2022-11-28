package com.testio;
import com.dao.Dao;
import com.model.PlayList;
import com.model.SongModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObjectTest {
    Dao dao;
    String songToString;
    List list;
    @Before
    public void SetUp() {
        dao = new Dao();
        this.songToString = "[com.module.Music{song_id=1, song_name='2002', album='anne marie', gener='love', artist='anne marie', duration=3.14, url='D:\\Documents\\THE JUKEBOX\\song Files wav\\Anne-Marie-2002.wav'}]";
    }
    @After
    public void tearDown() {
        dao = null;
    }
    @org.junit.jupiter.api.Test
    void getConnection() throws SQLException {
        Connection con = Dao.getConnection();
        Assertions.assertNotNull(con);
        Assertions.assertTrue(con.isValid(0));
        con.close();
    }
    @org.junit.jupiter.api.Test
    void checkSong_idIfCorrect()
    {
        dao  =new Dao();
        boolean a = dao.checkSong_id(5);
        Assertions.assertTrue(a);
    }
    @Test
    public void checkSong_idIfWring()
    {
        dao  =new Dao();
        boolean a = dao.checkSong_id(13);
        Assertions.assertFalse(a);
    }
    @Test
    public  void displayPlayList()
    {
        ArrayList<PlayList> out  = new ArrayList<>();
        out = dao.displayPlayList();
    }
    @Test
    public void bySomgName()
    {
        List<SongModel> out = new ArrayList<>();
        out = dao.bySongName("2002");
        Assertions.assertEquals(songToString,out.toString());
    }
    @Test
    public void bySongAlbum()
    {
        List<SongModel> out = new ArrayList<>();
        out = dao.bySongAlbum("anne marie");
        this.list = out;;
        Assertions.assertEquals(list.toString(),out.toString());
    }
    @Test
    public void bySongArtist()
    {
        List<SongModel> out = new ArrayList<>();
        out = dao.bySongArtist("2002");
        this.songToString = out.toString();
        Assertions.assertEquals(songToString,out.toString());
    }
    @Test
    public void bySongGener()
    {
        List song = dao.bySongGener("love");
        String s = (String) song.get(1);
        s = s.toLowerCase();
        System.out.println(s);
    }
}
