package com.fourteenfourhundred.engine;

import com.fourteenfourhundred.engine.util.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Drawable {

    private int x;
    private int y;
    private int width;
    private int height;

    public Drawable(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public void paint(){
        drawRect(x,y,width,height,0,Color.RED);
    }

    public void tick(){

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
