package com.model;

public class PlayList {
    private int song_id_playlist;
    private String playListName;

    public PlayList() {
    }
    public PlayList(int song_id_playlist, String playListName) {
        this.playListName = playListName;
        this.song_id_playlist = song_id_playlist;
    }
    public String getPlayListName() {
        return playListName;
    }

    public int getSong_id_playlist() {
        return song_id_playlist;
    }


    @Override
    public String toString() {
        return "PlayList{" +
                "playListName='" + playListName + '\'' +
                ", song_id_playlist=" + song_id_playlist +
                '}';
    }
}
