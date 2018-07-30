package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Screen;

import java.awt.*;


public class GameScreen extends Screen {


    @Override
    public void paint() {
        drawRect(10,10,40,40,0, Color.CYAN);
    }


}
