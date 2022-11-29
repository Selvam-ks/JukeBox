package com.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Menus {
    Scanner src = null;
    public void welcome()
    {
        System.out.println("******************************************");
        System.out.println("*~~~~~~~~Welcome To The JukeBox~~~~~~~~~~*");
        System.out.println("******************************************");
    }
    /*public int menu() throws ExceptionHandler
    {
        System.out.println("1. Show All Song\n2. Search Song\n3. Show Playlist");
        return src.nextInt();
    }*/
    public int menu()
    {
        src  = new Scanner(System.in);
        int a = 0;
        try {
            System.out.println("1. Show All Song\n2. Search Song\n3. Show Playlist\n4. Exit"); //54. Add Songs To DataBase
            a =  src.nextInt();
        }catch (InputMismatchException e)
        {
            System.out.println("Sorry, pls in put only the number that Provided");
        }
        return a;
    }
    public int searchSongList()
    {
        src  = new Scanner(System.in);
        int a = 0;
        try {
            System.out.println("Search By\n1. Song Name\n2. Album\n3. Artist\n4. Gener\nEnter by Number");
            a =  src.nextInt();
        }catch (InputMismatchException e)
        {
            System.out.println("Sorry, pls in put only the number that Provided");
        }
        return a;
    }
    public int playlistMenu()
    {
        src  = new Scanner(System.in);
        int a = 0;
        try {
            System.out.println("What Wold You like to do:");
            System.out.println("1. Create New Playlist\n2. View Existing Playlist\n3. Edit PlayList");
            a =  src.nextInt();
        }catch (InputMismatchException e)
        {
            System.out.println("Sorry, pls in put only the number that Provided");
        }
        return a;
    }
    public int editPlayListMenu()
    {
        src  = new Scanner(System.in);
        int a = 0;
        try {
            System.out.println("Edit List By");
            System.out.println("1. Add Song to playList\n2. Delete Song from Playlist\n3. Delete PlayList\n4.Exit to Main");
            a =  src.nextInt();
        }catch (InputMismatchException e)
        {
            System.out.println("Sorry, pls in put only the number that Provided");
        }
        return a;
    }
    public void audioMenu()
    {
        System.out.println("---------------------------Dialog Box-------------------------------------------------------");
        System.out.println("1:Play songs\t2:Stop\t3:Pause\t4:Resume\t5:Restart\t6:LoopMusic\t7.Exit the playlist\t0:Nest");
        System.out.println("--------------------------------------------------------------------------------------------");
    }
    public int audioPlayMenu()
    {
        int a = 0;
        try{
        System.out.println("1.play all Songs\n2.exit");
        a =  src.nextInt();
        }catch (InputMismatchException e)
        {
        System.out.println("Sorry, pls in put only the number that Provided");
        }
        return a;
    }
    public void exitmsg()
    {
        System.out.println("ThankYou for using the jukebox hope you had a good experience");
        System.out.println("*******************we will connect again*********************");
        System.out.println("                 ****Creator:- Selvam KS*****");
        System.out.println("**************************************************************");
    }
    public void menuForsearch(TreeSet<String> temp,String name){
        System.out.println("This are the available options");
        System.out.println("+---------------------+");
        System.out.format("| %-20s |\n",name);
        System.out.println("+---------------------+");
        for (Object o: temp) {
            System.out.format("|< %-18s >|\n",o);
        }
        System.out.println("+---------------------+");
    }

}