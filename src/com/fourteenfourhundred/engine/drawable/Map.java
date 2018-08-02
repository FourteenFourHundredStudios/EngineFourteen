package com.fourteenfourhundred.engine.drawable;


import com.fourteenfourhundred.engine.display.Camera;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;
import com.fourteenfourhundred.engine.util.Misc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


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

    public JsonArray getTileSavable(){
        JsonArray tilesJson = new JsonArray();
        for(Tile tile:tiles){
            tilesJson.add(tile.getJSONString());
        }
        return tilesJson;
    }

    public void invalidateTileBuckets(){

    }


    public void load(String name){
        try {
            JsonArray tiles = new Gson().fromJson(Misc.readFile(name+".eft"), JsonArray.class);
            for(int i=0; i<tiles.size();i++){
                String className = tiles.get(i).getAsJsonObject().get("class").getAsString();
                int x = tiles.get(i).getAsJsonObject().get("x").getAsInt();
                int y = tiles.get(i).getAsJsonObject().get("y").getAsInt();
                int width = tiles.get(i).getAsJsonObject().get("width").getAsInt();
                int height = tiles.get(i).getAsJsonObject().get("height").getAsInt();

                Tile tile = (Tile) Class.forName(className).getConstructor(int.class,int.class).newInstance(x,y);
                addTile(tile);

            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
