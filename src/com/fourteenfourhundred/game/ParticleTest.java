package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.display.Screen;
import com.fourteenfourhundred.engine.display.Window;
import com.fourteenfourhundred.engine.drawable.particle.Particle;

public class ParticleTest extends Window {


    public ParticleTest() {
        super(new Screen(){

            @Override
            public void onMousePressed(double x, double y, int button) {
                addToScreen(new Particle((int)x,(int)y,10,10));
            }
        }, "particleTest", 1020, 720);
    }


    public static void main(String[] args){
        new ParticleTest();
    }

}
