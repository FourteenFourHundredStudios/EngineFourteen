package com.fourteenfourhundred.engine.mapmaker;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.Screen;
import com.fourteenfourhundred.engine.Window;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.Entity;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Misc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.charset.Charset;

public class MapScreen extends Screen {
    private boolean save = false;
    private boolean load = false;

    public boolean align = true;
    public Map map = new Map(0,0,720,1080,new Camera());
    public Crosshair crosshair = new Crosshair();


    public MapScreen(){
        addToScreen(map);
        addToScreen(crosshair);
    }

    public void onMousePressed(double x, double y, int button){
        if(button == Window.LEFT_CLICK) {
            addTile(new Tile((int)x,(int)y),align);
        }else if(button == Window.RIGHT_CLICK) {


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


    public void load(){
        try {



            JsonArray tiles = new Gson().fromJson(Misc.readFile("map.eft"), JsonArray.class);
            for(int i=0; i<tiles.size();i++){
                String className = tiles.get(i).getAsJsonObject().get("class").getAsString();
                int x = tiles.get(i).getAsJsonObject().get("x").getAsInt();
                int y = tiles.get(i).getAsJsonObject().get("y").getAsInt();
                int width = tiles.get(i).getAsJsonObject().get("width").getAsInt();
                int height = tiles.get(i).getAsJsonObject().get("height").getAsInt();

                Tile tile = (Tile) Class.forName(className).getConstructor(int.class,int.class).newInstance(x,y);
                map.addTile(tile);

                //System.out.println(Class.forName(className).getConstructor(int.class,int.class).getName());

            }


            //System.out.println(convertedObject.toString());


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
                load();
            }
        }else{
            load = false;
        }
    }


    public void onMouseMoved(double x, double y){
        crosshair.setPos(align,x,y);
    }

    public void addTile(Tile tile, boolean align){
        if(align){
            tile.setX(Tile.size*(Math.round(tile.getX()/Tile.size)));
            tile.setY(Tile.size*(Math.round(tile.getY()/Tile.size)));
        }

        map.addTile(tile);

    }

}
