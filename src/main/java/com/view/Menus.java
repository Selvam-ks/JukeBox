package com.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Menus {
    Scanner src = null;
    public void welcome()
    {
        System.out.println("******************************************");
        System.out.println("*~~~\uD83D\uDC96~~~Welcome To The JukeBox~~~~\uD83D\uDC96~~~~*");
        System.out.println("       ****Version 3.1.0_(Beta)****");
        System.out.println("******************************************");
    }
    public int menu()
    {
        src  = new Scanner(System.in);
        int a = 0;
        try {
            System.out.println("1. \uD83C\uDFB6Show All Song\uD83C\uDFB6\n2. \uD83C\uDFB6Search Song Menu\uD83C\uDFB6\n3. \uD83C\uDFB6Playlist Menu\uD83C\uDFB6\n4. Exit"); //54. Add Songs To DataBase
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
        System.out.println("1:Play songs\t2:Stop\t3:Pause\t4:Resume\t5:Restart\t6:LoopMusic\t7.Exit the playlist\t0:Next");
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
        System.out.println("+------------------------+");
        System.out.format("| %-20s |\n",name);
        System.out.println("+------------------------+");
        for (Object o: temp) {
            System.out.format("|<\uD83D\uDCBF %-18s >|\n",o);
        }
        System.out.println("+------------------------+");
    }
    public int userLoginMenu()
    {
        System.out.println("1. Sign-Up\n2. Sign-In\n3. Continue Without Sign-up");
        return 2;
    }
}