/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics.api;

import org.jphysics.math.Vector3f;

public class SimpleTemporaryForce extends BasicObjectImpl implements TemporaryForce {

    protected boolean isAlive = true;
    protected float mass;
    protected final Vector3f position = new Vector3f();

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public float getMass() {
        return mass;
    }

    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    @Override
    public GameObject setPosition(Vector3f pos) {
        this.position.set(pos);
        return this;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    @Override
    public float getRadius() {
        return 0f;
    }

    @Override
    public void update(float secs) {
    }

}
