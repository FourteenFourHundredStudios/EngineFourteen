package com.fourteenfourhundred.engine.display;


import com.fourteenfourhundred.engine.display.Screen;
import org.lwjgl.glfw.GLFW;
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
    public double mouseX = 0;
    public double mouseY = 0;


    public static boolean slowMo = false;
    public static int tickSpeed = 10;

    public static final int RIGHT_CLICK = GLFW.GLFW_MOUSE_BUTTON_2;
    public static final int LEFT_CLICK = GLFW.GLFW_MOUSE_BUTTON_1;


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


    public void startThreads(){
        //it was 10

        if(slowMo) tickSpeed = 100;
        Thread ticker = new Thread(){
            public void run(){
                while(true){
                    long startTime = System.nanoTime();

                    screen.tick();


                    long endTime = System.nanoTime();
                    long timeout = tickSpeed - ((endTime-startTime)/1000000);


                    try {
                       //
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

         //   System.out.print(glfwGetJoystickButtons(GLFW_JOYSTICK_1).array());


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