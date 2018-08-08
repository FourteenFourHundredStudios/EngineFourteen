package com.fourteenfourhundred.game;

import com.fourteenfourhundred.engine.display.Screen;
import com.fourteenfourhundred.engine.util.Color;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;


import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class LightingTest {

    private  long window;
    public int WIDTH;
    public int HEIGHT;
    public String title;
    public double mouseX = 0;
    public double mouseY = 0;


    public static boolean slowMo = false;
    public static int tickSpeed = 10;

    public int x=0;

    public static final int RIGHT_CLICK = GLFW.GLFW_MOUSE_BUTTON_2;
    public static final int LEFT_CLICK = GLFW.GLFW_MOUSE_BUTTON_1;

    private long variableYieldTime, lastTime;

    public LightingTest(String title, int width, int height) {


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

            }

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


    public void drawRect(float x, float y, float width, float height, float rot, Color color){



        glEnable( GL_BLEND );

        glPushMatrix();

        glTranslatef(x, y, 0); // Shifts the position
        glRotatef(rot, 0, 0, 1);

        byte red = (byte)(color.getRed()-128);
        byte green = (byte)(color.getGreen()-128);
        byte blue = (byte)(color.getBlue()-128);
        byte alpha = (byte)(color.getAlpha()-128);

        glColor4b(red, green, blue, alpha);

        glBegin(GL_QUADS);

        glVertex2f(0.0f, 0.0f);
        glVertex2f(0.0f, height);
        glVertex2f(width, height);
        glVertex2f(width, 0.0f);

        glEnd();

        glPopMatrix();

    }


    public FloatBuffer floatBuffer(float[] f){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(f.length);
        buffer.put(f);
        buffer.flip();
        return buffer;
    }

    public void paint(){

      //  glEnable(GL_LIGHTING);
       // glEnable(GL_LIGHT0);

        glEnable(GL_COLOR_MATERIAL);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);



        /*
        FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
        ambient.put(new float[] { 0.05f, 0.05f, 0.05f, 1f, });
        ambient.flip();

        FloatBuffer position = BufferUtils.createFloatBuffer(4);
        position.put(new float[] { 0f, 0f, 0f, 1f, });
        position.flip();


        glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambient);
        glLightfv(GL_LIGHT0, GL_POSITION, position);*/


        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glLightModeli(GL11.GL_LIGHT_MODEL_LOCAL_VIEWER, GL11.GL_TRUE);

        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, floatBuffer(new float[] {100,100,100,100}));

        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE);


   //     x++;

        drawRect(30,30,30,30,0,Color.RED);

        /*

        FloatBuffer position = BufferUtils.createFloatBuffer(4);
        float[] posArray = {x, y, x, y};
        position.put(posArray);
        position.flip();


        GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
        */
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



                    sync(60);


                }
            }
        };
        ticker.start();

    }

    public void startGameLoop(){
        while (!glfwWindowShouldClose(window)) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            paint();

            //   System.out.print(glfwGetJoystickButtons(GLFW_JOYSTICK_1).array());

            glEnable(GL_LIGHTING);
            glEnable(GL_LIGHT0);
            // glLightModelfv(GL_LIGHT_MODEL_AMBIENT, position);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
        System.exit(0);
    }

    public static void main(String[] args){
        new LightingTest("few",500,500);
    }



}