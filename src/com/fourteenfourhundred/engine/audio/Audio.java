package com.fourteenfourhundred.engine.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    public static void playForever(String fileName){
        playForever(new File(fileName));
    }

    public static void playOnce(String fileName){
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
          
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void playForever(File file){
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
