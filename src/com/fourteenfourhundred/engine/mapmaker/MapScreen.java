package com.fourteenfourhundred.engine.mapmaker;

import com.fourteenfourhundred.engine.display.Camera;
import com.fourteenfourhundred.engine.display.Screen;
import com.fourteenfourhundred.engine.display.Window;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.Entity;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;

import java.io.*;
import java.nio.charset.Charset;

public class MapScreen extends Screen {
    private boolean save = false;
    private boolean load = false;

    public boolean align = true;
    public Map map = new Map(0,0,720,1080,new Camera(this));
    public Crosshair crosshair = new Crosshair();


    public MapScreen(){
        addToScreen(map);
        addToScreen(crosshair);
    }

    public void onMousePressed(double x, double y, int button){
        if(button == Window.LEFT_CLICK) {
            addEntity(new Tile((int)x,(int)y),align);
        }else if(button == Window.RIGHT_CLICK) {
            removeEntity(x,y);
        }
    }

    public void save(){
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("map.eft")));
            bos.write(map.getTileSavable().toString().getBytes(Charset.forName("UTF-8")));
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void keys(){
        if(isKeyDown(77)){
            if(!save) {
                save = true;
                save();
            }
        }else{
            save = false;
        }
        if(isKeyDown(76)){
            if(!load) {
                load = true;
                map.load("map");
            }
        }else{
            load = false;
        }
    }



    public void onMouseMoved(double x, double y){
        crosshair.setPos(align,x,y);
    }

    public void addEntity(Entity entity, boolean align){
        if(align){
            entity.setX(Tile.size*(Math.round(entity.getX()/Tile.size)));
            entity.setY(Tile.size*(Math.round(entity.getY()/Tile.size)));
        }

        map.addEntity(entity);

    }

    public void removeEntity(double x, double y){
        for(Entity entity: map.getEntities()){
            System.out.println("x "+ entity.getX());
            if(entity.getBounds().intersectsPoint(x,y)){
                System.out.println("test");
            }
        }
    }

}
