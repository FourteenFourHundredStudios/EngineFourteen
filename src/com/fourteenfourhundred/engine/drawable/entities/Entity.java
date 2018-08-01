package com.fourteenfourhundred.engine.drawable.entities;

import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Rectangle;


import java.util.ArrayList;

public class Entity extends Drawable {

    protected boolean collidable = true;
    protected boolean hasGravity = false;
    public double gravitySpeed = 0.3;
    public float yVelocity = 0;
    public int xVelocity = 0;
    public ArrayList<Map> maps = new ArrayList<>();
    public Rectangle bounds;


    public Entity(int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height);
        this.collidable = collidable;
        this.bounds = new Rectangle(x,y,width,height);
    }

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.bounds = new Rectangle(x,y,width,height);
    }



    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public boolean hasGravity() {
        return hasGravity;
    }

    public void setHasGravity(boolean hasGravity) {
        this.hasGravity = hasGravity;
    }


    public void attachToMap(Map map){
        maps.add(map);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public boolean isTouching(float dx, float dy){
        Rectangle collideBounds = new Rectangle(x+dx,y+dy,width,height);
        for(Map map: maps){
            for(Tile tile : map.getTiles()){
                if(collideBounds.intersects(tile.getBounds())){
                    return true;
                }
            }
        }
        return false;
    }

    public void tick(){
        y += yVelocity;
        x += xVelocity;


        if(hasGravity){

            if(isTouching(0,yVelocity)){
                yVelocity = 0 ;
            }

            yVelocity += gravitySpeed ;

        }
    }

}
