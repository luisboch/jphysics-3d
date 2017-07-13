/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics3d.api;

import org.jphysics3d.math.Vector3f;


/**
 *
 * @author luis
 */
public interface GameObject {

    float getMass();
    Vector3f getPosition();
    GameObject setPosition(Vector3f pos);
    
    void update(float secs);

    boolean isAlive();
}
