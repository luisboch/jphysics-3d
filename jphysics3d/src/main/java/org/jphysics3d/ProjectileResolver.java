/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics3d;

import org.jphysics3d.api.PhysicObject;
import org.jphysics3d.projectile.Projectile;

/**
 *
 * @author luis
 */
public interface ProjectileResolver {
    boolean canCreateProjectile(Class<? extends Projectile> type, PhysicObject creator);
    
    Projectile create(PhysicObject creator, Class<? extends Projectile> type);
}
