package com.fourteenfourhundred.engine.drawable.entities;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Color;
import com.fourteenfourhundred.engine.util.Rectangle;


import java.util.ArrayList;

public class Entity extends Drawable {

    protected boolean collidable = true;
    protected boolean hasGravity = false;
    public double gravitySpeed = 0.3;
    public float terminalVelocity = 20;
    public float yVelocity = 0;
    public float xVelocity = 0;
    public ArrayList<Map> maps = new ArrayList<>();
    public Rectangle bounds;
    public Rectangle collideBounds ;

    public Entity(int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height);
        this.collidable = collidable;
        this.bounds = new Rectangle(x,y,width,height);
        this.collideBounds = new Rectangle(x,y,width,height);
    }

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.bounds = new Rectangle(x,y,width,height);
        this.collideBounds = new Rectangle(x,y,width,height);
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



    public Entity isTouching(float dx, float dy){
        if(!isCollidable())return null;
        Rectangle collideBounds = new Rectangle(x+dx,y,width,height+dy);
        for(Map map: maps){
            for(Tile tile : map.getTiles()){
                if(collideBounds.intersects(tile.getBounds()) && tile.isCollidable()){

                    return tile;
                }
            }
        }
        return null;
    }

    public void moveBy(float dx, float dy){
        Entity touching = isTouching(dx,dy);
        if( touching == null){

            x += dx;
            y += dy;
        }else{
            //x adjustment for speed
            if(dx > 0 ){
                x = touching.getX() - touching.getWidth();
            }else if (dx < 0){
                x = touching.getX() + touching.getWidth();
            }
            //y adjustment for speed

            if(touching.getY()<y){
                //y=touching.getY()-touching.height;
            }


        }
    }

    public void launch(int dx, int dy){
        xVelocity+=dx;
        yVelocity+=dy;
    }

    public void tick(){
        moveBy(xVelocity, yVelocity);


        //TODO Do not assume Tile's gender
        if(hasGravity){


            //is working
            Entity touching = isTouching(0,yVelocity);

            if(touching!=null){
                yVelocity = 0;

                y = touching.getY() - touching.getHeight();
            }

            yVelocity += gravitySpeed ;
            if( yVelocity > terminalVelocity)yVelocity=terminalVelocity;

        }
    }

}
