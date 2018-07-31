package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.drawable.entities.Actor;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;

public class Player extends Actor {


    public Player(int x, int y) {
        super(x, y, Tile.size, Tile.size,true);

    }


}
