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
public class Seek extends Steering<Seek> {

    private Vector3f target;

    public Seek(Vector3f target) {
        this.target = target;
    }

    public Seek(PhysicObject target) {
        this.target = target.getPosition();
    }

    public Seek target(PhysicObject target) {
        this.target = target.getPosition();
        return this;
    }

    public Seek target(Vector3f target) {
        this.target = target;
        return this;
    }

    @Override
    public Vector3f _calculate() {
        
        if (target == null) {
            throw new IllegalStateException("Target can't be null");
        }

        final Vector3f desired = new Vector3f(target).sub(from.getPosition());

        desired.normalize();
        desired.mul(from.getMaxVelocity());
        Vector3f seek = desired.sub(from.getVelocity());
        
        return seek;
    }

    @Override
    public String toString() {
        return "Seek{from" + (from == null ? "null" : from.getPosition()) + "target=" + target + '}';
    }

}
