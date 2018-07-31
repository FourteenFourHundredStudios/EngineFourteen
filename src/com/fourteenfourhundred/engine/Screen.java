package com.fourteenfourhundred.engine;



import java.util.ArrayList;



public class Screen {

    public ArrayList<Drawable> drawableElements = new ArrayList<Drawable>();
    public boolean[] keys = new boolean[65536];


    public void paint(){
        for(int i = 0; i<drawableElements.size();i++){
            drawableElements.get(i).paint();
        }
    }

    public boolean isKeyDown(int keycode) {
        return keys[keycode];
    }

    public void tick(){

    }



}




