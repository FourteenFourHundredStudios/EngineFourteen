package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.entities.Actor;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Color;

public class Player extends Actor {




    public Player(int x, int y) {
        super(x, y, Tile.size, Tile.size,true);

    }



    public void paint(int xOffset, int yOffset){


        //System.out.println("fwefewf");

        drawRect(collideBounds,Color.CYAN);
        super.paint(xOffset,yOffset);
    }


}
