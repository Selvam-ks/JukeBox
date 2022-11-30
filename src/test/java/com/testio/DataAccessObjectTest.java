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
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

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
    void checkSong_idIfCorrect() {
        dao = new Dao();
        boolean a = dao.checkSong_id(5);
        Assertions.assertTrue(a);
    }

    @Test
    public void checkSong_idIfWring() {
        dao = new Dao();
        boolean a = dao.checkSong_id(13);
        Assertions.assertFalse(a);
    }

    @Test
    public void checkTheSize() {
        List<SongModel> lengthcheck = dao.getAllSongs();
        assertEquals(lengthcheck.size(), 7, 0);

    }

    @Test
    public void bySomgName_Pos() {
        List<SongModel> out = dao.bySongName("2002");
        Assertions.assertEquals(out.get(0).getSong_name(), "2002");
    }

    @Test
    public void bySongAlbum() {
        List<SongModel> out = dao.bySongAlbum("Asuran");
        Assertions.assertEquals(out.get(0).getSong_name(), "Ellu Vaya Pookalaye");
    }

    @Test
    public void bySomgName_Neg() {
        List<SongModel> out = dao.bySongName("Shape of You");
        Assertions.assertNotEquals(out.get(0).getSong_name(), "2002");
    }

    @Test
    public void bySongArtist() {
        List<SongModel> out = dao.bySongArtist("anne marie");
        Assertions.assertEquals(out.get(0).getSong_name(), "2002");
        Assertions.assertEquals(out.get(1).getSong_name(), "friends");
    }
}
