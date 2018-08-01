package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.GameScreen;
import com.fourteenfourhundred.engine.Screen;
import com.fourteenfourhundred.engine.drawable.Map;
import com.fourteenfourhundred.engine.drawable.entities.Actor;
import com.fourteenfourhundred.engine.drawable.entities.tiles.Tile;


public class MyGameScreen extends Screen {

    Player player;
    Player mob;
    Player john;
    Map map = new Map(0,0,720,1820,new Camera());
    

    public MyGameScreen(){
        player = new Player(30,10);


        addToScreen(map);

        addToScreen(player).attachToMap(map);




        for(int i=0; i<10;i++){
            map.addTile(new Tile(Tile.size+(i*30),100));
        }

    }

    public void keys(){
        if(isKeyDown(68)){
            player.setX(player.getX()+3);
        }
    }


    public void tick(){
        super.tick();

    }

}
