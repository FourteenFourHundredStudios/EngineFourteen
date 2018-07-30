package com.fourteenfourhundred.engine;

import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window
{
    private  long window;
    private int WIDTH = 1280;
    private int HEIGHT = 720;
    private Screen screen;

    public Window(Screen screen) {
        int x = 0;
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

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            /*
            glBegin(GL_QUADS);
            glVertex2f(0.0f, 0.0f);
            glVertex2f(0.0f, 10+x);
            glVertex2f(10, 10);
            glVertex2f(10+x, 0.0f);
            glEnd();*/
            screen.paint();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }
}