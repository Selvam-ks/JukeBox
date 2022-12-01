package com.model;
import java.util.List;
public interface SearchSongsBy {
    List<SongModel> bySongName(String s_name);
    List<SongModel> bySongAlbum(String album);
    List<SongModel> bySongArtist(String artist);
    List<SongModel> bySongGener(String gener);

}
