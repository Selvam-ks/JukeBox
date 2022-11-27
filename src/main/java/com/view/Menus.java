package com.view;

import java.util.Scanner;

public class Menus {
    Scanner src = new Scanner(System.in);
    public void welcome()
    {
        System.out.println("******************************************");
        System.out.println("~~~~~~~~~Welcome To The JukeBox~~~~~~~~~~~");
        System.out.println("******************************************");
    }
    public int menu()
    {
        System.out.println("1. Show All Song\n2.Search Song\n3. Show Playlist");
        return src.nextInt();
    }
    public int searchSongList()
    {
        System.out.println("Search By\n1.Song Name\n2.Album\n3.Artist\n4.Gener");
        return src.nextInt();
    }
    public int playlistMenu()
    {
        System.out.println("What Wold You like to do:");
        System.out.println("1.\tCreate New Playlist\n2.View Exisiting Playlist");
        return src.nextInt();
    }
}