/**
 * ProjectileSteering.class
 */
package org.jphysics.steering;

import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;

public class ProjectileSteering extends Steering<ProjectileSteering> {

    private float accel = 50f;
    private final Vector3f direction;

    public ProjectileSteering(Vector3f direction) {
        this.direction = new Vector3f(direction);
    }

    public ProjectileSteering(PhysicObject from) {
        this.from = from;
        this.direction = new Vector3f(from.getDirection());
    }

    @Override
    protected Vector3f _calculate() {
        return direction.normalize().mul(accel);
    }

    public ProjectileSteering accel(float accel) {
        this.accel = accel;
        return this;
    }
}
