package com.fourteenfourhundred.engine.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class Misc {

    public static String readFile(String name){
        String file = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            file = br.readLine();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return file;
    }


}
