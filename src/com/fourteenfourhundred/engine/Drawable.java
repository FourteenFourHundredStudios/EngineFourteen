package com.fourteenfourhundred.engine;

import com.fourteenfourhundred.engine.util.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Drawable {

    public void paint(){
        drawRect(0,0,100,100,0,Color.RED);
    }


    public void drawRect(float x, float y, float width, float height, float rot, Color color){


        glEnable( GL_BLEND );

        glPushMatrix();

        glTranslatef(x, y, 0); // Shifts the position
        glRotatef(rot, 0, 0, 1);

        byte red = (byte)(color.getRed()-128);
        byte green = (byte)(color.getGreen()-128);
        byte blue = (byte)(color.getBlue()-128);
        byte alpha = (byte)(color.getAlpha()-128);

        glColor4b(red, green, blue, alpha);

        glBegin(GL_QUADS);

        glVertex2f(0.0f, 0.0f);
        glVertex2f(0.0f, height);
        glVertex2f(width, height);
        glVertex2f(width, 0.0f);

        glEnd();

        glPopMatrix();

    }

}
