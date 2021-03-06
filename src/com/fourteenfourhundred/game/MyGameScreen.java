package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.audio.Audio;
import com.fourteenfourhundred.engine.display.Camera;
import com.fourteenfourhundred.engine.display.Screen;
import com.fourteenfourhundred.engine.display.Window;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class MyGameScreen extends Screen {

    Player player;
    Player mob;
    Player john;
    Camera cam = new Camera(1280, 720);
    Map map = new Map(0,0,720,1820,cam);


    public MyGameScreen(){

        player = new Player(30,10);


        addToScreen(map);
        player.attachToMap(map);
        player.attachCamera(cam);
        map.addEntity(player);


        Audio.playForever("music.wav");

        for(int i=0; i<20;i++){
            map.addEntity(new Tile(Tile.size+(i*30),500));
        }


        map.addEntity(new Tile(30+120, 470));
        map.addEntity(new Tile(30, 440));
        map.addEntity(new Tile(30, 410));
        map.addEntity(new Tile(30, 380));
        map.addEntity(new Tile(30, 350));

        for(int i=1; i<10;i++){
            map.addEntity(new Tile(Tile.size+(i*30),350));
        }



    }



    public void keys(){
        if(isKeyDown(68)){
            player.moveBy(4,0);
        } if(isKeyDown(65)){
            player.moveBy(-4,0);
        } if(isKeyDown(32)){

;
            if(player.isTouching(0,player.yVelocity).happened && player.isResting()) {
                player.launch(0, -10);
                Audio.playOnce("jump.wav");
            }


        }

    }




    public void tick(){
        super.tick();

    }

}
