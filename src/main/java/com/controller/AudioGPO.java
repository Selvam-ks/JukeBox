package com.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class AudioGPO {
    AudioInputStream audioInputStream;
    Clip clip;
    String fileName;
    File file;
    Long currentFrame;
    String status;

    public void AudioPlayerMethods(String fileName) {
        this.fileName=fileName;
        try {
            file=new File(fileName);
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip=AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception e){
            System.out.println("The file is Missing or File not fund");
        }
    }
    public void playAudio() {
        try {
            clip.start();
            status="play";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void stopAudio() {
        try{
            currentFrame=0L;
            clip.stop();
            //clip.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void resumeAudio() {
        if(status.equals("play")){
            System.out.println("Audio already playing");
            return;
        }
        try {
            clip.close();
            resetAudioStream();
            clip.setMicrosecondPosition(currentFrame);
            this.playAudio();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void restartAudio() {
        try{
            clip.stop();
            clip.close();
            resetAudioStream();
            clip.setMicrosecondPosition(0);
            this.playAudio();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void loopAudio() {
        try{
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void pauseAudio() {
        if(status.equals("paused")){
            System.out.println("Audio already paused");
            return ;
        }
        try{
            this.currentFrame= this.clip.getMicrosecondPosition();
            clip.stop();
            status="paused";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void resetAudioStream()
    {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(fileName).getAbsoluteFile());
            clip.open(audioInputStream);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
