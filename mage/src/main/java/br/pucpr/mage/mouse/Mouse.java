/*
 * Copyright 2017 luis.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.pucpr.mage.mouse;

import java.util.HashSet;
import java.util.Set;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

/**
 *
 * @author luis
 */
public class Mouse {

    private final long windowId;

    private Vector2d pos = new Vector2d();
    private Listener listener = new MouseAdapter();

    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Set<Integer> downKeys = new HashSet<>();
    private final Set<Integer> releasedKeys = new HashSet<>();

    private final GLFWMouseButtonCallback buttonCallBack = new GLFWMouseButtonCallback() {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            if (window == windowId) {
                Mouse.this.set(button, action);
                Mouse.this.notify(MouseButton.from(button), action);
            }
        }
    };

    private final GLFWCursorPosCallback posCallback = new GLFWCursorPosCallback() {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            pos.x = xpos;
            pos.y = ypos;
            System.out.println("Mouse x:" + xpos + ", y: " + ypos);
        }
    };

    public Mouse(long windowId) {
        this.windowId = windowId;

        // registerCallback
        GLFW.glfwSetCursorPosCallback(windowId, posCallback);
        GLFW.glfwSetMouseButtonCallback(windowId, buttonCallBack);
        glfwSetInputMode(windowId, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

    }

    public Vector2d getPos() {
        return pos;
    }

    public void set(int k, int action) {

        if (action == GLFW.GLFW_PRESS) {
            downKeys.add(k);
            pressedKeys.add(k);
        } else if (action == GLFW.GLFW_RELEASE) {
            downKeys.remove(k);
            releasedKeys.add(k);
        }
    }

    public void update() {
        pressedKeys.clear();
        releasedKeys.clear();
    }

    public boolean isPressed(MouseButton m) {
        return pressedKeys.contains(m.getCode());
    }

    public boolean isDown(MouseButton m) {
        return downKeys.contains(m.getCode());
    }

    public boolean isReleased(MouseButton key) {
        return releasedKeys.contains(key.getCode());
    }

    private void notify(MouseButton k, int action) {
        if (listener != null) {

            if (action == GLFW.GLFW_PRESS) {
                listener.mousePress(k);
            } else if (action == GLFW.GLFW_RELEASE) {
                listener.mouseRelease(k);
            }
        }
    }

    private static interface Listener {

        void mousePress(MouseButton m);

        void mouseRelease(MouseButton m);
    }

    private static class MouseAdapter implements Listener {

        @Override
        public void mousePress(MouseButton m) {
        }

        @Override
        public void mouseRelease(MouseButton m) {
        }

    }

}
