package com.fourteenfourhundred.engine.drawable.particle;

import com.fourteenfourhundred.engine.drawable.Drawable;

public class Particle extends Drawable {


    public Particle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }




    public void tick(){
        y-=10;
    }


}
