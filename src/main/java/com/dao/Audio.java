package com.dao;

import com.controller.AudioGPO;
import com.model.SongModel;
import com.view.Menus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Audio {
    AudioGPO audiogpo = new AudioGPO();
    String exit ="";
    public void playAllSongs(List<SongModel> songmodel)
    {
        ArrayList<String> arrayUrl = new ArrayList<>();
        ArrayList<String> arraysongname = new ArrayList<>();
        Menus mnu = new Menus();
        for (SongModel songModel : songmodel) {
            arrayUrl.add(songModel.getUrl());
            arraysongname.add(songModel.getSong_name());
        }
        Iterator<String> i = arrayUrl.iterator();
        Iterator<String> sn = arraysongname.iterator();
        while (i.hasNext() && sn.hasNext())
        {
            String url = i.next();
            String name = sn.next();
//            System.out.println("Song Name : "+name);
            audiogpo.AudioPlayerMethods(url);
            mnu.audioMenu();
            int a = 0;
            System.out.println("Your Playing this Song :- "+name);
                do {
                    try {
                        System.out.println("Enter a Choice");
                        Scanner src1 = new Scanner(System.in);
                        a = src1.nextInt();
                        switch (a) {
                            case 1 ->//play music
                                    audiogpo.playAudio();
                            case 2 ->//stop music
                                    audiogpo.stopAudio();
                            case 3 ->//pause music
                                    audiogpo.pauseAudio();
                            case 4 ->//resume
                                    audiogpo.resumeAudio();
                            case 5 ->//restart
                                    audiogpo.restartAudio();
                            case 6 -> audiogpo.loopAudio();
                            case 7 -> {
                                exit = "exit";
                                System.out.println("!***Play list Ended***!\uD83D\uDE00");
                            }
                            default -> {
                            }
                        }
                    }catch (Exception e) {
                        System.out.println("Sorry, pls in put only the number that Provided");
                    }
                    if (exit.equals("exit"))
                        break;
                } while (a != 0);
            audiogpo.stopAudio();
            if (a==0) {
                System.out.print("The Next ");
            }
            if (exit.equals("exit")) {
                System.out.println("Exiting to menu");
                break;
            }
        }
    }
}