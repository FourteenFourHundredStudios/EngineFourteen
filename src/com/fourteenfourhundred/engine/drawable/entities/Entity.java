package com.fourteenfourhundred.engine.drawable.entities;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Collision;
import com.fourteenfourhundred.engine.util.Rectangle;
import com.google.gson.*;

import java.util.ArrayList;

public class Entity extends Drawable {

    protected boolean collidable = true;
    protected boolean hasGravity = false;
    public double gravitySpeed = .3;
    public float terminalVelocity = 200;
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




    public Collision isTouching(float dx, float dy){
        if(!isCollidable())return new Collision();
        collideBounds = new Rectangle(x+dx,y+dy,width,height);
        for(Map map: maps){
            for(Tile tile : map.getTiles()){
                if(collideBounds.intersects(tile.getBounds()) && tile.isCollidable()) {
                    //once you found the colliding tile pass it into this function to get directions
                    return applyDirection(tile);
                }
            }
        }
        return new Collision();
    }

    public JsonObject getJSONString(){
        JsonObject data = new JsonObject();
        data.addProperty("x",x);
        data.addProperty("y",y);
        data.addProperty("width",width);
        data.addProperty("height",height);
        data.addProperty("class",getClass().getName());
        return data;
    }

    //once the colliding tile is found you need this to find the direction info
    private Collision applyDirection(Tile tile){
        int vDist = y-tile.getY();
        int hDist = x-tile.getX();
        int hitbox = -1;

        if (y >tile.getY()) {
            hitbox = Collision.ABOVE;
        } else if(y<tile.getY()) {
            hitbox = Collision.BELOW;
        }else if (x < tile.getX()) {
            hitbox = Collision.RIGHT;
        } else if (x > tile.getX()) {
            hitbox = Collision.LEFT;
        }
        return new Collision(tile, hitbox,vDist,hDist);

    }

    public void moveBy(float dx, float dy){
        Collision touching = isTouching(dx,dy);

        if(!touching.happened){//call the police

            x += dx;
            y += dy;
        }else{
            if(Math.abs(touching.vDist)> Math.abs(touching.hDist)){
                if(touching.hitbox==touching.BELOW){
                    yVelocity = 0;
                    y = touching.entity.getY()-touching.entity.getHeight();
                }else if(touching.hitbox==touching.ABOVE) {
                    yVelocity = 0;
                    y = touching.entity.getY() + touching.entity.getHeight();
                }
            }else {
                if (touching.hitbox == touching.RIGHT) {
                    y += dy;
                    xVelocity = 0;
                    x = touching.entity.getX() - width;
                } else if (touching.hitbox == touching.LEFT) {
                    y += dy;
                    xVelocity = 0;
                    x = touching.entity.getX() + touching.entity.getWidth();
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
