/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics.api;

import org.jphysics.Engine;
import org.jphysics.ObjectController;
import org.jphysics.math.Vector3f;

public class DefaultControllerResolver implements ControllerResolver {
    @Override
    public Vector3f calculate(Engine engine, ControllableObject obj, ObjectController controller) {
        return new Vector3f();
    }
}
