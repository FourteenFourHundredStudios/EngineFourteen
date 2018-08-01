package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.Camera;
import com.fourteenfourhundred.engine.Screen;
import com.fourteenfourhundred.engine.drawable.Map;
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



        for(int i=0; i<20;i++){
            map.addTile(new Tile(Tile.size+(i*30),500));
        }


        map.addTile(new Tile(30+120, 470));
        map.addTile(new Tile(30, 440));
        map.addTile(new Tile(30, 410));
        map.addTile(new Tile(30, 380));
        map.addTile(new Tile(30, 350));

        for(int i=1; i<10;i++){
            map.addTile(new Tile(Tile.size+(i*30),350));
        }


    }

    public void keys(){
        if(isKeyDown(68)){
            player.moveBy(4,0);
        } if(isKeyDown(65)){
            player.moveBy(-4,0);
        } if(isKeyDown(32)){

;
            if(player.isTouching(0,player.yVelocity).happened && player.isResting()) {

                player.launch(0, -10);

            }
        }

    }




    public void tick(){
        super.tick();

    }

}
