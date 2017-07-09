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
public class Pursuit extends Steering<Pursuit> {

    private PhysicObject evader;

    public Pursuit() {
        this(null);
    }

    public Pursuit(PhysicObject evader) {
        this.evader = evader;
    }

    public Pursuit evader(PhysicObject evader) {
        this.evader = evader;
        return this;
    }

    @Override
    public Vector3f _calculate() {

        if (evader == null) {
            throw new IllegalStateException("Object evader can't be null");
        }

        Vector3f toEvader = new Vector3f(evader.getPosition()).sub(from.getPosition());

        float relativeDir = new Vector3f(from.getVelocity()).dot(evader.getVelocity());

        if (toEvader.dot(from.getVelocity()) > 0
                && relativeDir < Math.toRadians(18)) {
            return new Seek(evader).from(from).calculate();
        }

        float lookAheadTime = toEvader.length() / (from.getMaxVelocity() + evader.getVelocity().length());
        return new Seek(evader.getPosition().add(evader.getVelocity().mul(lookAheadTime))).from(from).calculate();
    }
}
