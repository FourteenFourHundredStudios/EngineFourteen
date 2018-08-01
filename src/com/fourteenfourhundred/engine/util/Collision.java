package com.fourteenfourhundred.engine.util;


import com.fourteenfourhundred.engine.drawable.entities.Entity;

public class Collision{

    public Entity entity;
    public boolean happened=true;
    //0 means left, 1 means right
    public int hitbox;

    public static final int horizontal = 0;
    public static final int vertical = 1;

    public Collision(Entity entity, int hitbox){
        this.entity = entity;
        this.hitbox = hitbox;

    }

    public Collision(){
        happened = false;
    }

}