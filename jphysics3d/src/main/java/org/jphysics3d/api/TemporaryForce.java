/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics3d.api;

/**
 *
 * @author luis
 */
public interface TemporaryForce extends Force{
    @Override
    boolean isAlive();
}
