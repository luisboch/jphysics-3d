package org.jphysics.api;

import java.util.List;
import org.jphysics.math.Vector3f;

/**
 *
 * @author luis
 */
public interface PhysicObject extends GameObject {

    Vector3f getVelocity();

    Vector3f getDirection();

    Vector3f getScale();

    float getMaxVelocity();

    float getRadius();

    List<PhysicObject> getChildren();

    PhysicObject getParent();

    PhysicObject setParent(PhysicObject obj);

    PhysicObject setVelocity(Vector3f newVelocity);

    PhysicObject setDirection(Vector3f newDirection);

    PhysicObject decreaseLife();
}
