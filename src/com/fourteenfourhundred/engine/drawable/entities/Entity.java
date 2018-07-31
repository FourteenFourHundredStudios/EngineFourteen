package com.fourteenfourhundred.engine.drawable.entities;

import com.fourteenfourhundred.engine.drawable.Drawable;

public class Entity extends Drawable {

    protected boolean collidable = true;
    protected boolean hasGravity = false;
    public int gravitySpeed = 5;

    public Entity(int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height);
        this.collidable = collidable;
    }

    public Entity(int x, int y, int width, int height) {
        super(x, y, width, height);
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

    public void tick(){
        if(hasGravity){
            y += gravitySpeed;
        }
    }

}
