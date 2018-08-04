package com.fourteenfourhundred.engine.display;

import com.fourteenfourhundred.engine.drawable.entities.Entity;

public class Camera {

    public int x=0;
    public int y=0;
    public int width = 0;
    public int height = 0;


    public Camera(int width,int height){
        this.width = width;
        this.height = height;
        //System.out.println();
        x = width/2;
        y = height/2;
    }


    public void focus(Entity entity){

    }


}
