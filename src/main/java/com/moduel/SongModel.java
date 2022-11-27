package com.moduel;

public class SongModel {
    private int song_id;
    private String song_name;
    private String album;
    private String artist;
    private String gener;
    private double duration;
    private String url;


    public SongModel(int song_id,String song_name, String album, String artist, String gener, double duration) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.album = album;
        this.artist = artist;
        this.gener = gener;
        this.duration = duration;
    }

    public SongModel(int song_id, String song_name, String album, String artist, String gener, double duration, String url) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.album = album;
        this.artist = artist;
        this.gener = gener;
        this.duration = duration;
        this.url = url;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*@Override
    public String toString() {
        return "com.module.Music{" +
                "song_id=" + song_id +
                ", song_name='" + song_name + '\'' +
                ", album='" + album + '\'' +
                ", gener='" + gener + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                '}';
    }*/
}
