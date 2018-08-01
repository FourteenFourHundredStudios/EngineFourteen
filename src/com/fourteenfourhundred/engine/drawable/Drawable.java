package com.fourteenfourhundred.engine.drawable;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.util.Color;
import com.fourteenfourhundred.engine.util.Rectangle;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Drawable {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible = true;

    public Drawable(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void paint(int xOff, int yOff){
        drawRect(x+xOff,y+yOff,width,height,0,Color.RED);
    }

    public void paint(Camera cam){
        paint(cam.x,cam.y);
    }

    public void tick(){

    }

    public void drawRect(Rectangle rect, Color color){
        drawRect(rect.x, rect.y, rect.width, rect.height,0,color);
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


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
