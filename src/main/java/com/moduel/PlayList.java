package com.moduel;

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
    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public int getSong_id_playlist() {
        return song_id_playlist;
    }

    public void setSong_id_playlist(int song_id_playlist) {
        this.song_id_playlist = song_id_playlist;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "playListName='" + playListName + '\'' +
                ", song_id_playlist=" + song_id_playlist +
                '}';
    }
}
