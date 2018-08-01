package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.entities.Actor;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Color;

public class Player extends Actor {

    public boolean boundBoxes = true;



    public Player(int x, int y) {
        super(x, y, Tile.size, Tile.size,true);

    }



    public void paint(int xOffset, int yOffset){

        super.paint(xOffset,yOffset);


        if(boundBoxes) {
             drawRect(collideBounds,Color.CYAN);

        }



    }

    public void tick(){
        super.tick();



    }


}
