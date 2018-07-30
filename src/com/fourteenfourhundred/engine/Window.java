package com.fourteenfourhundred.engine;

import com.sun.corba.se.impl.io.IIOPOutputStream;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
//import java.awt.Color;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window {

    private  long window;
    private int WIDTH = 1280;
    private int HEIGHT = 720;
    private Screen screen;

    public Window(Screen screen) {
        int x = 0;

       // Color c = new Color(9,9,9);
        //java.awt.Button f = new java.awt.Button();
        //System.out.print(c.getGreen());

        

        this.screen = screen;

        if (!glfwInit()) {
            System.out.println("GLFW initialization failed.");
            System.exit(1);
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        window = glfwCreateWindow(WIDTH, HEIGHT, "GLFW Window", MemoryUtil.NULL, MemoryUtil.NULL);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 12, 12, 0, 1, -1);
        glClearColor(0, 0.7f, 1, 0);
        glMatrixMode(GL_MODELVIEW);
        glfwShowWindow(window);


      //  Color red = new Color(137, 97,40);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                drawRect(0, 0, 10, 10,0);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }


    public void drawRect(float x, float y,float width, float height, float rot){






        glBegin(GL_QUADS);

        glVertex2f(0.0f, 0.0f);
        glVertex2f(0.0f, height);
        glVertex2f(width, height);
        glVertex2f(width, 0.0f);

        glEnd();



    }


    public void drawRectOld(float x, float y, float width, float height, float rot,Color color){

        /*
        byte red = (byte)(color.getRed()-128);
        byte green = (byte)(color.getGreen()-128);
        byte blue = (byte)(color.getBlue()-128);
        byte alpha = (byte)(color.getAlpha()-128);
        //glColor4b(red, green, blue, alpha);
*/

        glBegin(GL_QUADS);

        glVertex2f(0.0f, 0.0f);
        glVertex2f(0.0f, height);
        glVertex2f(width, height);
        glVertex2f(width, 0.0f);

        glEnd();
    }

}