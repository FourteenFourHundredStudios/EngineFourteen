package com.fourteenfourhundred.engine;



import java.util.ArrayList;



public class Screen {

    public ArrayList<Drawable> drawableElements = new ArrayList<Drawable>();

    public void paint(){
        for(int i = 0; i<drawableElements.size();i++){
            drawableElements.get(i).paint();
        }
    }



}




