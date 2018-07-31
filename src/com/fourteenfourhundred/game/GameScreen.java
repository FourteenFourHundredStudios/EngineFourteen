package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.drawable.Drawable;
import com.fourteenfourhundred.engine.Screen;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;


public class GameScreen extends Screen {

    Drawable player;
    Map map = new Map(0,0,1280,720,new Camera());

    public GameScreen(){
        player = new Drawable(10,10,50,50);
        drawableElements.add(player);


        for(int i=0; i<10;i++){
            map.addTile(new Tile(Tile.size+(i*10),100));
        }

    }

    public void keys(){
        if(isKeyDown(68)){
            player.setX(player.getX()+1);
        }
    }

    public void paint(){
        super.paint();
        map.paint();
    }

    public void tick(){
        super.tick();

    }

}
