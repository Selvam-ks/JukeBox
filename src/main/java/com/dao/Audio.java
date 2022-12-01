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
    Scanner src = new Scanner(System.in);
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
            String s = i.next();
            //System.out.println(s);
            audiogpo.AudioPlayerMethods(s);
            mnu.audioMenu();
            int a = 0;
            System.out.println("Your Playing this Song :- "+sn.next());
                do {
                    try {
                        System.out.println("Enter a Choice");
                        Scanner src1 = new Scanner(System.in);
                        a = src1.nextInt();
                        switch (a) {
                            case 1://play music
                                audiogpo.playAudio();
                                break;
                            case 2://stop music
                                audiogpo.stopAudio();
                                break;
                            case 3://pause music
                                audiogpo.pauseAudio();
                                break;
                            case 4://resume
                                audiogpo.resumeAudio();
                                break;
                            case 5://restart
                                audiogpo.restartAudio();
                                break;
                            case 6:
                                audiogpo.loopAudio();
                                break;
                            case 7:
                                exit = "exit";
                                System.out.println("Exiting to menu");
                                break;
                            default:
                                break;
                        }
                    }catch (Exception e) {
                        System.out.println("Sorry, pls in put only the number that Provided");
                    }
                    if (exit.equals("exit"))
                        break;
                } while (a != 0);
            audiogpo.stopAudio();
            if (exit.equals("exit"))
                break;

            System.out.println("!***Play list Ended***!\uD83D\uDE00");
        }
    }
}