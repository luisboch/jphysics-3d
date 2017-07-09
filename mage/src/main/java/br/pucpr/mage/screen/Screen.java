/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpr.mage.screen;

import br.pucpr.mage.mouse.Mouse;
import br.pucpr.mage.keyboard.Keyboard;

/**
 *
 * @author luis
 */
public abstract class Screen {

    protected Keyboard keyboard;
    protected Mouse mouse;
    protected Window window;

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    protected void init() {

    }

    protected void close() {

    }

    protected void update(float secs) {
    }

    protected void draw() {
    }
}
