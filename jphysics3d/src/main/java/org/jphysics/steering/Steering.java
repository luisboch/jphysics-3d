/**
 * Steering.class
 */
package org.jphysics.steering;

import org.jphysics.api.PhysicObject;
import org.jphysics.math.Vector3f;

/**
 *
 * @author Luis Boch
 * @param <E>
 * @email luis.c.boch@gmail.com
 * @since Jul 31, 2016
 */

public abstract class Steering<E extends Steering> {

    protected PhysicObject from;

    public Steering() {
    }

    public E from(PhysicObject from) {
        this.from = from;
        return (E) this;
    }

    public Vector3f calculate() {

        if (this.from == null) {
            throw new IllegalStateException("Object reference can't be null");
        }

        return _calculate();
    }

    protected abstract Vector3f _calculate();
}
