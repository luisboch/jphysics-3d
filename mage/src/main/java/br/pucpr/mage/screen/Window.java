package br.pucpr.mage.screen;

import br.pucpr.mage.mouse.Mouse;
import br.pucpr.mage.keyboard.Keyboard;
import org.joml.Vector2i;
import org.lwjgl.PointerBuffer;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glViewport;
import org.lwjgl.opengl.GLCapabilities;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    // The window handle
    private long windowID;
    private Screen screen;
    private String title;
    private Mouse mouse;
    private Keyboard keyboard;
    private Vector2i size = new Vector2i();

    private final GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.out);
    private final GLFWWindowSizeCallback resizeCallback = GLFWWindowSizeCallback.create(new GLFWWindowSizeCallback.SAM() {
        @Override
        public void invoke(long windowID, int w, int h) {
            glViewport(0, 0, w, h);
            size.x = w;
            size.y = h;
        }
    });

    public Window(Screen screen, String title, int width, int height) {
        this.screen = screen;
        this.title = title;
        size.x = width;
        size.y = height;
    }

    public Window(Screen screen, String title) {
        this(screen, title, 800, 600); // initial screen size.
    }

    public Window(Screen screen) {
        this(screen, "Game");
    }

    private void init() {
        System.setProperty("java.awt.headless", "true");
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback);

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (glfwInit() != GL11.GL_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden
        // after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be
        // resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Create the window
        windowID = glfwCreateWindow(size.x, size.y, title, NULL, NULL);
        if (windowID == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup a key callback. It will be called every time a key is pressed,
        // repeated or released.
        mouse = new Mouse(windowID);
        keyboard = new Keyboard(windowID);

        screen.setMouse(mouse);
        screen.setKeyboard(keyboard);

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(windowID, (vidmode.width() - size.x) / 2, (vidmode.height() - size.y) / 2);
        glfwSetWindowSizeCallback(windowID, resizeCallback);

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowID);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(windowID);
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLCapabilities capabilities = GL.createCapabilities();

        // Set the clear color
        screen.setWindow(this);
        screen.init();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        long before = System.currentTimeMillis() - 1;
        while (glfwWindowShouldClose(windowID) != GL11.GL_TRUE) {
            float secs = (System.currentTimeMillis() - before) / 1000f;
            before = System.currentTimeMillis() - 1;
            screen.update(secs);
            screen.draw();

            keyboard.update();
            mouse.update();

            glfwSwapBuffers(windowID);
            glfwPollEvents();
        }

        screen.close();
    }

    public void show() {
        try {
            init();
            loop();

            // Destroy window and window callbacks
            glfwDestroyWindow(windowID);
        } finally {
            // Terminate GLFW and free the GLFWErrorCallback
            glfwTerminate();
        }
    }
}
