package com.fourteenfourhundred.engine.drawable;


import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;


import java.util.ArrayList;

public class Map extends Drawable {

    //user can use this
    private ArrayList<Tile> tiles = new ArrayList<>();

    //this is internal, and is used for collision algorithm
    private ArrayList<ArrayList<Tile>> tileBuckets = new ArrayList<>();
    private Camera camera;


    public Map(int x, int y, int width, int height, Camera camera) {
        super(x, y, width, height);
        this.camera = camera;

    }

    public void paint(){
       // super.paint();
        for(int i=0; i< tiles.size();i++){
            tiles.get(i).paint(camera);
        }
    }


    public boolean addTile(Tile tile){
        tiles.add(tile);

        return true;
    }

    public void invalidateTileBuckets(){

    }


}
