package com.fourteenfourhundred.engine.mapmaker;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.Screen;
import com.fourteenfourhundred.engine.Window;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;

public class MapScreen extends Screen {

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
