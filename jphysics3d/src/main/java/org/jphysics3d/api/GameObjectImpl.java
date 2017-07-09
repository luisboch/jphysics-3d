/**
 * VectorObject.class
 */
package org.jphysics3d.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.jphysics3d.math.Vector3f;
import org.jphysics3d.steering.Steering;

/**
 *
 * @author Luis Boch
 * @email luis.c.boch@gmail.com
 * @since Jul 31, 2016
 */
public abstract class GameObjectImpl extends BasicObjectImpl implements PhysicObject {

    private final float radius;
    private final float mass;
    private final float maxVel;
    private final float accel = 300;
    private final float maxForce;
    private final Vector3f position = new Vector3f();
    private final Vector3f velocity = new Vector3f();
    private final Vector3f direction = new Vector3f();
    private final Vector3f scale = new Vector3f(1f, 1f, 1f);
    protected Vector3f lastWorldPos = new Vector3f();
    private Vector3f pivot = new Vector3f(0f, 0f, 0f);

    private final String type;

    private Steering steering;

    protected int health = -999999;
    protected int initHealth = health;

    private final List<PhysicObject> listActorObject;
    private PhysicObject parent;

    public GameObjectImpl(float radius, float mass) {
        this.radius = radius;
        this.mass = mass;
        type = _getType();
        maxVel = 500f;
        maxForce = 350f;
        listActorObject = new ControlList(this);
    }

    public GameObjectImpl(float radius, float mass, float maxVel) {
        this.radius = radius;
        this.mass = mass;
        this.type = _getType();
        this.maxVel = maxVel;
        maxForce = 350f;
        listActorObject = new ControlList(this);
    }

    public GameObjectImpl(float radius, float mass, float maxVel, float maxForce) {
        this.radius = radius;
        this.mass = mass;
        this.type = _getType();
        this.maxVel = maxVel;
        this.maxForce = maxForce;
        listActorObject = new ControlList(this);
    }

    public GameObjectImpl(float radius, float mass, Vector3f scale) {
        this.radius = radius;
        this.mass = mass;
        type = _getType();
        maxVel = 500f;
        maxForce = 350f;
        listActorObject = new ControlList(this);
        this.scale.set(scale);
    }

    public GameObjectImpl(float radius, float mass, float maxVel, Vector3f scale) {
        this.radius = radius;
        this.mass = mass;
        this.type = _getType();
        this.maxVel = maxVel;
        maxForce = 350f;
        listActorObject = new ControlList(this);
        this.scale.set(scale);
    }

    public GameObjectImpl(float radius, float mass, float maxVel, float maxForce, Vector3f scale) {
        this.radius = radius;
        this.mass = mass;
        this.type = _getType();
        this.maxVel = maxVel;
        this.maxForce = maxForce;
        listActorObject = new ControlList(this);
        this.scale.set(scale);
    }

    protected void tick() {
    }

    public Vector3f getLastWorldPos() {
        return lastWorldPos;
    }

    public void applyForce(Float force) {
        if (this.health > 0) {

            float f = force;

            if (f > 0) {
                float h = this.health;
                h = h - f;
                h = h < 0 ? 0 : h;

                this.health = (int) h;
            }
        }
    }

    public final void setHealth(Integer health) {
        this.health = health;
        this.initHealth = health;
    }

    public Vector3f getPivot() {
        return pivot;
    }

    public void setPivot(Vector3f pivot) {
        this.pivot = pivot;
    }

    private String _getType() {
        return getClass().getSimpleName().toUpperCase();
    }

    public String getType() {
        return type;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public float getMass() {
        return mass;
    }

    @Override
    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    @Override
    public GameObjectImpl setPosition(Vector3f pos) {
        position.set(pos);
        return this;
    }

    @Override
    public GameObjectImpl setDirection(Vector3f pos) {
        direction.set(pos);
        return this;
    }

    @Override
    public GameObjectImpl setVelocity(Vector3f pos) {
        velocity.set(pos);
        return this;
    }

    @Override
    public Vector3f getVelocity() {
        return new Vector3f(velocity);
    }

    @Override
    public float getMaxVelocity() {
        return maxVel;
    }

    public float getMaxVel() {
        return maxVel;
    }

    public float getMaxForce() {
        return maxForce;
    }

    @Override
    public Vector3f getDirection() {
        return new Vector3f(direction);
    }

    @Override
    public Vector3f getScale() {
        return new Vector3f(scale);
    }

    public float getAccel() {
        return accel;
    }

    public void setSteering(Steering steering) {
        this.steering = steering;
        if (steering != null) {
            steering.from(this);
        }
    }

    public Steering getSteering() {
        return steering;
    }

    public void contact(GameObjectImpl e) {
    }

    public byte getAnnimationState() {
        return 0;
    }

    public void setAnnimationState(byte val) {
        // Ignored...
    }

    public final List<PhysicObject> getListActorObject() {
        return listActorObject;
    }

    @Override
    public final List<PhysicObject> getChildren() {
        return getListActorObject();
    }

    public void setListActorObject(List<GameObjectImpl> listActorObject) {
        this.listActorObject.clear();
        this.listActorObject.addAll(listActorObject);
    }

    public void setParent(GameObjectImpl parent) {
        this.parent = parent;
    }

    @Override
    public PhysicObject getParent() {
        return parent;
    }

    public float getHealthPercent() {
        return (float) health / (float) initHealth;
    }

    /**
     *
     * @param percent value from 0 to 100
     */
    public void setHealthByPercent(byte percent) {
        setHealthByPercent(((float) percent / 100f));
    }

    public void setHealthByPercent(float percent) {
        health = (int) ((float) initHealth * percent);
    }

    private static class ControlList extends ArrayList<PhysicObject> {

        private final PhysicObject _instance;

        private ControlList(PhysicObject ref) {
            _instance = ref;
        }

        @Override
        public boolean add(PhysicObject e) {
            if (e == null) {
                return false;
            }

            e.setParent(_instance);
            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends PhysicObject> c) {

            if (c == null) {
                return false;
            }

            CollectionUtils.filter(c, new Predicate<PhysicObject>() {
                @Override
                public boolean evaluate(PhysicObject t) {
                    return t != null;
                }
            });

            IterableUtils.forEach(c, new Closure<PhysicObject>() {
                @Override
                public void execute(PhysicObject input) {
                    input.setParent(_instance);
                }
            });

            return super.addAll(c);
        }

        @Override
        public void clear() {
            IterableUtils.forEach(this, new Closure<PhysicObject>() {
                @Override
                public void execute(PhysicObject input) {
                    input.setParent(null);
                }
            });

            super.clear();
        }

        @Override
        public boolean remove(Object o) {

            if (o == null) {
                return false;
            }

            if (o instanceof PhysicObject) {
                ((PhysicObject) o).setParent(null);
            }

            return super.remove(o);
        }

        @Override
        public boolean removeAll(Collection<?> c) {

            if (c == null) {
                return false;
            }

            CollectionUtils.filter(c, new Predicate<Object>() {
                @Override
                public boolean evaluate(Object object) {
                    return object instanceof PhysicObject;
                }
            });

            IterableUtils.forEach(c, new Closure<Object>() {
                @Override
                public void execute(Object input) {
                    ((PhysicObject) input).setParent(null);
                }
            });

            return super.removeAll(c);
        }

    }

    @Override
    public boolean isAlive() {
        return health == -999999 || health > 0;
    }
    
    @Override
    public GameObjectImpl decreaseLife(){
        this.health--;
        return this;
    }

    @Override
    public PhysicObject setParent(PhysicObject obj) {
        this.parent = obj;
        return this;
    }

    @Override
    public void update(float secs) {
    }

}
