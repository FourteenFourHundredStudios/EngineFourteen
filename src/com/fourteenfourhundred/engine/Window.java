package com.fourteenfourhundred.engine;


import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;



import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window {

    private  long window;
    public int WIDTH;
    public int HEIGHT;
    public String title;
    public Screen screen;

    public Window(Screen screen,String title, int width, int height) {


        this.screen = screen;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.title= title;


        if (!glfwInit()) {
            System.out.println("GLFW initialization failed.");
            System.exit(1);
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        window = glfwCreateWindow(WIDTH, HEIGHT, title, MemoryUtil.NULL, MemoryUtil.NULL);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glfwSetKeyCallback(window, new GLFWKeyCallback(){

            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                screen.keys[key] = action != GLFW_RELEASE;
            }

        });

        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);

        glClearColor(1, 1, 1, 0);
        glMatrixMode(GL_MODELVIEW);
        glfwShowWindow(window);
        startThreads();
        startGameLoop();



    }


    public void startThreads(){
        Thread ticker = new Thread(){
            public void run(){
                while(true){
                    long startTime = System.nanoTime();

                    screen.tick();
                    for(int i=0;i<screen.drawableElements.size();i++) {
                        screen.drawableElements.get(i).tick();
                    }

                    long endTime = System.nanoTime();
                    long timeout = 40 - ((endTime-startTime)/1000000);


                    try {

                        Thread.sleep((timeout<0) ? 0 : timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ticker.start();

    }

    public void startGameLoop(){
        while (!glfwWindowShouldClose(window)) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            this.screen.paint();


            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void setScreen(Screen newScreen){
        this.screen = newScreen;
    }




}