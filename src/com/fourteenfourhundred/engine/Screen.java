package com.fourteenfourhundred.engine;



import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.entities.Entity;

import java.util.ArrayList;



public class Screen {

    public ArrayList<Drawable> drawableElements = new ArrayList<Drawable>();


    public boolean[] keys = new boolean[65536];


    public void paint(){

        for(int i = 0; i<drawableElements.size();i++){
            drawableElements.get(i).paint(0,0);
        }
    }

    public boolean isKeyDown(int keycode) {
        return keys[keycode];
    }

    public void tick(){
        for(int i = 0; i<drawableElements.size();i++){
            drawableElements.get(i).tick();
        }
        keys();
    }

    public void addToScreen(Drawable element){
        drawableElements.add(element);
    }

    public Entity addToScreen(Entity element){
        drawableElements.add(element);
        return element;
    }

    public void keys(){

    }

}




