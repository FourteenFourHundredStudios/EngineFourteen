package com.fourteenfourhundred.engine.drawable.entities.tiles;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.entities.Entity;
import com.fourteenfourhundred.engine.util.Color;

public class Tile extends Entity {


    public static int size = 30;

    public Tile(int x, int y) {
        super(x, y, size, size);
    }

    public void paint(int xOff,int yOff){
        drawRect(x+xOff,y+yOff,width,height,0,Color.GREEN);
    }

}
