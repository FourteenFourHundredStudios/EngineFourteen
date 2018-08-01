package com.fourteenfourhundred.engine.drawable.entities;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Collision;
import com.fourteenfourhundred.engine.util.Color;
import com.fourteenfourhundred.engine.util.Rectangle;


import java.util.ArrayList;

public class Entity extends Drawable {

    protected boolean collidable = true;
    protected boolean hasGravity = false;
    public double gravitySpeed = .3;
    public float terminalVelocity = 20;
    public float yVelocity = 0;
    public float xVelocity = 0;
    public ArrayList<Map> maps = new ArrayList<>();
    public Rectangle bounds;
    public Rectangle verticalCollideBounds ;
    public Rectangle horizontalCollideBounds ;

    public Entity(int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height);
        this.collidable = collidable;
        this.bounds = new Rectangle(x,y,width,height);
        this.verticalCollideBounds = new Rectangle(x,y,width,height);
        this.horizontalCollideBounds = new Rectangle(x,y,width,height);
    }

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.bounds = new Rectangle(x,y,width,height);
        this.verticalCollideBounds = new Rectangle(x,y,width,height);
        this.horizontalCollideBounds = new Rectangle(x,y,width,height);
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



    public Collision isTouching(float dx, float dy){
        if(!isCollidable())return new Collision();
        verticalCollideBounds = new Rectangle(x+dx+(height/4),y+dy,width/2,height);
        horizontalCollideBounds = new Rectangle(x+dx,y+dy+(width/4),width,height/2);
        for(Map map: maps){
            for(Tile tile : map.getTiles()){
                if(verticalCollideBounds.intersects(tile.getBounds()) && tile.isCollidable()){
                    return new Collision(tile,Collision.vertical);
                }else if(horizontalCollideBounds.intersects(tile.getBounds()) && tile.isCollidable()){
                    return new Collision(tile,Collision.horizontal);
                }
            }
        }
        return new Collision();
    }

    public void moveBy(float dx, float dy){
        Collision touching = isTouching(dx,dy);
        if(!touching.happened){//call the police

            x += dx;
            y += dy;
        }else{

            //x adjustment for collision
            if(touching.hitbox == Collision.horizontal) {
                if (dx > 0) {
                    x = touching.entity.getX() - touching.entity.getWidth();
                } else if (dx < 0) {
                    x = touching.entity.getX() + touching.entity.getWidth();
                }

            }else {

                if (y < touching.entity.getY()) {
                    yVelocity = 0;
                    y = touching.entity.getY() - touching.entity.getHeight();
                } else {
                    yVelocity = 0;
                }

            }

        }
    }

    public void launch(int dx, int dy){
        xVelocity+=dx;
        yVelocity+=dy;
    }


    public boolean isResting(){
        return yVelocity < 1;
    }

    public void tick(){
        moveBy(xVelocity, yVelocity);

        if(hasGravity)yVelocity += gravitySpeed;
        
        if( yVelocity > terminalVelocity)yVelocity=terminalVelocity;



    }

}
