package com.fourteenfourhundred.engine.display;


import com.fourteenfourhundred.engine.display.Screen;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;


import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Window {

    private  long window;
    public int WIDTH;
    public int HEIGHT;
    public String title;
    public Screen screen;
    public double mouseX = 0;
    public double mouseY = 0;


    public static boolean slowMo = false;
    public static int tickSpeed = 10;

    public static final int RIGHT_CLICK = GLFW.GLFW_MOUSE_BUTTON_2;
    public static final int LEFT_CLICK = GLFW.GLFW_MOUSE_BUTTON_1;

    private long variableYieldTime, lastTime;

    public Window(Screen screen,String title, int width, int height) {


        this.screen = screen;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.title= title;

        screen.width = width;
        screen.height = height;

       // System.out.println("fwfe");

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

        glfwSetMouseButtonCallback(window, (long window, int button, int action, int mods) -> {
            if(action == GLFW_PRESS) {
                screen.onMousePressed(mouseX, mouseY, button);
            }else if(action == GLFW_RELEASE){
                screen.onMouseReleased(mouseX, mouseY, button);
            }
        });

        glfwSetCursorPosCallback(window, (long window, double x, double y) -> {
            mouseX = x;
            mouseY = y;
            screen.onMouseMoved(x,y);
        });

        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);

        glClearColor(1, 1, 1, 0);
        glMatrixMode(GL_MODELVIEW);
        glfwShowWindow(window);

        //These are for eventual controller support.
        //org.lwjgl.glfw.GLFW.glfwInit();
       // System.out.println(glfwGetJoystickName(GLFW_JOYSTICK_1));



        startThreads();
        startGameLoop();



    }


    private void sync(int fps) {
        if (fps <= 0) return;

        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000 * 1000));
        long overSleep = 0; // time the sync goes over by

        try {
            while (true) {
                long t = System.nanoTime() - lastTime;

                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                } else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                } else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);

            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200 * 1000, sleepTime);
            } else if (overSleep < variableYieldTime - 200 * 1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2 * 1000, 0);
            }
        }
    }


    public void startThreads(){
        //it was 10

        if(slowMo) tickSpeed = 100;
        Thread ticker = new Thread(){
            public void run(){
                while(true){

                    screen.tick();

                    sync(60);


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
        System.exit(0);
    }

    public void setScreen(Screen newScreen){
        this.screen = newScreen;
    }




}