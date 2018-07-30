package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Window;

public class Game {

    public Game(){
        GameScreen game = new GameScreen();
        Window win = new Window(game);
    }


    public static void main(String args[]){
        new Game();
    }

}
