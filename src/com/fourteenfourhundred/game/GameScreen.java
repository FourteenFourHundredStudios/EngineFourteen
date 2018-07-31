package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Drawable;
import com.fourteenfourhundred.engine.Screen;
import com.fourteenfourhundred.engine.util.Color;
import sun.plugin2.util.ColorUtil;


public class GameScreen extends Screen {

    Drawable player;

    public GameScreen(){
        player = new Drawable(10,10,50,50);
        drawableElements.add(player);
    }

    public void tick(){
        super.tick();

        if(isKeyDown(68)){
           player.setX(player.getX()+1);
       }
       
    }

}
