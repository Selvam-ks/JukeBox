package com.moduel;
import java.util.List;
public interface Search {
    abstract List<SongModel> bySongName(String s_name);
    abstract List<SongModel> bySongAlbum(String album);
    List<SongModel> bySongArtist(String artist);
    List<SongModel> bySongGener(String gener);
}
