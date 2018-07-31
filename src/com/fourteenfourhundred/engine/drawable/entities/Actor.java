package com.fourteenfourhundred.engine.drawable.entities;

import com.fourteenfourhundred.engine.drawable.entities.Entity;

public class Actor extends Entity {


    public Actor(int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height, collidable);
        setHasGravity(true);
    }


    @Override
    public void tick() {
        super.tick();

    }
}
