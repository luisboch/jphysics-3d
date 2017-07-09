/**
 * TrackMissile.class
 */
package org.jphysics3d.projectile;

import org.jphysics3d.api.GameObjectImpl;
import org.jphysics3d.steering.Steering;

public class MineShot extends Shot {

    protected GameObjectImpl target;

    // Created when receive update from network (ignore steering and from, it will be controled by server)
    public MineShot() {
        this(null, null);
    }

    public MineShot(Steering steering, GameObjectImpl from, GameObjectImpl target, int minProximity) {
        this(steering, from);
        init(target, minProximity);
    }

    
    public MineShot(Steering steering, GameObjectImpl from) {
        super(5, 3f, 2000, 400, 20f, 1000f, 15000);
        init(steering, from);

    }

    private void init(Steering steering, GameObjectImpl from) {
        setSteering(steering);
        setFrom(from);
    }

    @Override
    public float getInitialVelocity() {
        return 0f; 
    }
}