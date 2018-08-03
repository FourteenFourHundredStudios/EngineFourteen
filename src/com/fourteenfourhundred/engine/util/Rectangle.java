package com.fourteenfourhundred.engine.util;

public class Rectangle {

    public float x;
    public float y;
    public float width;
    public float height;

    public Rectangle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rectangle r) {
        float tw = this.width;
        float th = this.height;
        float rw = r.width;
        float rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        float tx = this.x;
        float ty = this.y;
        float rx = r.x;
        float ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public boolean intersectsPoint(double x, double y) {
        System.out.println("rx:"+this.x+" x:"+x+" ry:"+this.y+" y:"+y);
        if(this.x<x && x<(this.x+this.width) && this.y<y && y<(this.y+this.height)){
            return true;
        }
        return false;
    }

}
