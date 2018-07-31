package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Window;

public class GameWindow extends Window{



    public GameWindow() {
        super(new TitleScreen(), "EngineFourteen Demo", 1280, 720);

    }

    public void startThreads(){

    }


    public static void main(String args[]){
        new GameWindow();
    }

}
