package com.fourteenfourhundred.engine.mapmaker;

import com.fourteenfourhundred.engine.display.Window;

public class MapWindow extends Window {

    public MapWindow() {
        super(new MapScreen(), "Engine Fourteen Map maker", 1080, 720);

    }




    public static void main(String[] args){
        new MapWindow();
    }

}


