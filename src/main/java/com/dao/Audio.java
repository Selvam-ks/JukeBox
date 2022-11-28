package com.dao;

import com.controller.AudioGPO;
import com.moduel.SongModel;
import com.view.Menus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Audio {
    AudioGPO audiogpo = new AudioGPO();
    Scanner src = new Scanner(System.in);
    public void playAllSongs(List<SongModel> songmodel)
    {
        ArrayList<String> arrayUrl = new ArrayList<>();
        Menus mnu = new Menus();
        for (SongModel songModel : songmodel) {
            arrayUrl.add(songModel.getUrl());
        }
        Iterator<String> i = arrayUrl.iterator();
        while (i.hasNext())
        {
            String s = i.next();
            //System.out.println(s);
            audiogpo.AudioPlayerMethods(s);
            mnu.audioMenu();
            int a;
            do {
                System.out.println("Enter a Choice");
                a = src.nextInt();
                switch (a)
                {
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
                    default:
                        System.out.println("Exiting to menu");
                        break;
                }
                /*System.out.println("Zero(0) for Next Song");
                a= src.nextInt();*/
            }while (a != 0);
            audiogpo.stopAudio();
        }
    }
}