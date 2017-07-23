/**
 * Flee.class
 */
package org.jphysics.steering;

import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;

/**
 *
 * @author Luis Boch
 * @email luis.c.boch@gmail.com
 * @since Jul 31, 2016
 */
public class Flee extends Steering<Flee> {

    private Vector3f target;
    private Float panicDistance = 300f;

    public Flee() {
    }

    public Flee(PhysicObject target) {
        target(target);
    }

    public Flee(Vector3f target) {
        target(target);
    }

    public Flee target(PhysicObject target) {
        this.target = target.getPosition();
        return this;
    }

    public Flee target(Vector3f target) {
        this.target = target;
        return this;
    }

    public Flee panicDist(float dist) {
        this.panicDistance = dist;
        return this;
    }

    @Override
    public Vector3f _calculate() {

        if (target == null) {
            throw new IllegalStateException("Target can't be null");
        }

        if (new Vector3f(from.getPosition()).distance(target) > panicDistance) {
            return new Vector3f(0, 0, 0);
        }

        final Vector3f desired = new Vector3f(from.getPosition()).sub(target);

        desired.normalize();
        desired.mul(from.getMaxVelocity());

        return desired.sub(from.getVelocity());
    }
}
