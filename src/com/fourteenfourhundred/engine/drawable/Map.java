package com.fourteenfourhundred.engine.drawable;


import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.entities.Actor;
import com.fourteenfourhundred.engine.drawable.entities.Entity;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;


import java.util.ArrayList;

public class Map extends Drawable {

    //user can use this
    protected ArrayList<Tile> tiles = new ArrayList<>();

    //this is internal, and is used for collision algorithm
    protected ArrayList<ArrayList<Tile>> tileBuckets = new ArrayList<>();
    protected Camera camera;


    public Map(int x, int y, int width, int height, Camera camera) {
        super(x, y, width, height);
        this.camera = camera;

    }

    @Override
    public void paint(int xOff, int yOff) {
        for(int i=0; i< tiles.size();i++){
            tiles.get(i).paint(camera);
        }
    }


    public boolean addTile(Tile tile){
        tiles.add(tile);

        return true;
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }


    public void invalidateTileBuckets(){

    }


}
