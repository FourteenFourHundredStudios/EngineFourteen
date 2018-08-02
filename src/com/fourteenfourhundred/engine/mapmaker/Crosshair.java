package com.fourteenfourhundred.engine.mapmaker;

import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Color;

public class Crosshair extends Drawable {

    public Crosshair() {
        super(0, 0, Tile.size, Tile.size);
    }
    float testx=0;
    float testy=0;

    public void paint(int xOff,int yOff){
        drawEmptyRect(x,y,Tile.size,Tile.size,0, Color.RED);
        //drawRect(testx,testy,30,30,0,Color.BLUE);
    }

    public void setPos(boolean align, double x, double y) {
        testx=(float)x;
        testy=(float)y;

        if(align){
            this.x = (Tile.size*(Math.round((int)x/Tile.size)));
            this.y = (Tile.size*(Math.round((int)y/Tile.size)));
        }else{
            this.x=(int)x;
            this.y=(int)y;
        }

        /*
        this.x -= Tile.size/2;
        this.y -= Tile.size/2;*/

    }
}
