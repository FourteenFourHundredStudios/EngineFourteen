package com.fourteenfourhundred.engine.display;

import com.fourteenfourhundred.engine.drawable.entities.Entity;

public class Camera {

    public int x=0;
    public int y=0;
    public Screen screen;

    public Camera(Screen screen){
        this.screen = screen;
        x = screen.width;
        //y = screen.height;
    }


    public void focus(Entity entity){

    }


}
