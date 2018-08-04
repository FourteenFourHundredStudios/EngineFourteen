package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.display.Window;

public class GameWindow extends Window{


    public GameWindow() {
        super(new MyGameScreen(), "EngineFourteen Demo", 1280, 720);

    }

    public void startThreads(){
        super.startThreads();




    }


    public static void main(String args[]){
        new GameWindow();
    }



}
