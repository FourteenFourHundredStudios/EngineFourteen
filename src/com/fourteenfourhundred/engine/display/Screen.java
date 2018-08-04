package com.fourteenfourhundred.engine.display;

import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.entities.Entity;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;



public class Screen {

    //man this is a test
    //another test

    public ArrayList<Drawable> drawableElements = new ArrayList<Drawable>();
    public int width;
    public int height;

    public boolean[] keys = new boolean[65536];



    public void paint(){

        for(int i = 0; i<drawableElements.size()&&drawableElements.get(i).isVisible();i++){
            drawableElements.get(i).paint(0,0);
        }
    }

    public void onMousePressed(double x, double y,int button){

    }

    public void onMouseReleased(double x, double y, int button){

    }

    public void onMouseMoved(double x, double y){

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




