/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics3d.api;

import org.jphysics3d.Engine;
import org.jphysics3d.ObjectController;
import org.jphysics3d.math.Vector3f;

public class DefaultControllerResolver implements ControllerResolver {
    @Override
    public Vector3f calculate(Engine engine, ControllableObject obj, ObjectController controller) {
        return new Vector3f();
    }
}
