/**
 * Flee.class
 */
package org.jphysics3d.steering;

import org.jphysics3d.api.PhysicObject;
import org.jphysics3d.math.Vector3f;

/**
 *
 * @author Luis Boch
 * @email luis.c.boch@gmail.com
 * @since Jul 31, 2016
 */
public class Arrive extends Steering<Arrive> {

    private Vector3f target;
    private Float deceleration = 1f;

    public Arrive() {
    }

    public Arrive(Vector3f target) {
        target(target);
    }

    public Arrive(PhysicObject target) {
        target(target);
    }

    public final Arrive target(Vector3f target) {
        this.target = target;
        return this;
    }

    public final Arrive target(PhysicObject target) {
        this.target = target.getPosition();
        return this;
    }

    public Arrive deceleration(float d) {
        this.deceleration = d;
        return this;
    }

    @Override
    public Vector3f _calculate() {
        if (target == null) {
            throw new IllegalArgumentException("Target can't be null");
        }
        
        Vector3f toTarget = new Vector3f(target).sub(from.getPosition());
        float dist = toTarget.length();

        float speed = dist / deceleration;
        speed = Math.min(from.getMaxVelocity(), speed);
        
        Vector3f desired = toTarget.mul(speed).mul(1.0f / dist);
        desired.sub(from.getVelocity());
        
        return desired;
    }
}
