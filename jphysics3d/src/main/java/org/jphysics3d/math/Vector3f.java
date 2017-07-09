/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics3d.math;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;
import org.joml.Matrix3dc;
import org.joml.Matrix3fc;
import org.joml.Matrix3x2fc;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Quaternionfc;
import org.joml.Vector2fc;
import org.joml.Vector3dc;
import org.joml.Vector3fc;

/**
 *
 * @author luis
 */
public class Vector3f extends org.joml.Vector3f {

    public Vector3f() {
    }

    public Vector3f(float d) {
        super(d);
    }

    public Vector3f(float x, float y, float z) {
        super(x, y, z);
    }

    public Vector3f(Vector3fc v) {
        super(v);
    }

    public Vector3f(Vector2fc v, float z) {
        super(v, z);
    }

    public Vector3f(ByteBuffer buffer) {
        super(buffer);
    }

    public Vector3f(int index, ByteBuffer buffer) {
        super(index, buffer);
    }

    public Vector3f(FloatBuffer buffer) {
        super(buffer);
    }

    public Vector3f(int index, FloatBuffer buffer) {
        super(index, buffer);
    }

    @Override
    public Vector3f add(Vector3fc v) {
        super.add(v);
        return this;
    }

    @Override
    public Vector3f mul(Vector3fc v) {
        super.mul(v);
        return this;
    }

    @Override
    public Vector3fc toImmutable() {
        super.toImmutable();
        return this;
    }

    @Override
    public Vector3f orthogonalizeUnit(Vector3fc v) {
        super.orthogonalizeUnit(v);
        return this;
    }

    public Vector3f orthogonalizeUnit(Vector3fc v, Vector3f dest) {
        super.orthogonalizeUnit(v, dest);
        return this;

    }

    @Override
    public Vector3f orthogonalize(Vector3fc v) {
        super.orthogonalize(v);
        return this;
    }

    public Vector3f orthogonalize(Vector3fc v, Vector3f dest) {
        super.orthogonalize(v, dest);
        return this;
    }

    public Vector3f lerp(Vector3fc other, float t, Vector3f dest) {
        super.lerp(other, t, dest);
        return this;
    }

    public Vector3f lerp(Vector3fc other, float t) {
        super.lerp(other, t);
        return this;
    }

    public Vector3f hermite(Vector3fc t0, Vector3fc v1, Vector3fc t1, float t, Vector3f dest) {
        super.hermite(t0, v1, t1, t, dest);
        return this;
    }

    public Vector3f smoothStep(Vector3fc v, float t, Vector3f dest) {
        super.smoothStep(v, t, dest);
        return this;
    }

    public Vector3f half(float x, float y, float z, Vector3f dest) {
        super.half(x, y, z, dest);
        return this;
    }

    public Vector3f half(Vector3fc other, Vector3f dest) {
        super.half(other, dest);
        return this;
    }

    @Override
    public Vector3f half(float x, float y, float z) {
        super.half(x, y, z);
        return this;
    }

    @Override
    public Vector3f half(Vector3fc other) {
        super.half(other);
        return this;
    }

    public Vector3f reflect(float x, float y, float z, Vector3f dest) {
        super.reflect(x, y, z, dest);
        return this;
    }

    public Vector3f reflect(Vector3fc normal, Vector3f dest) {
        super.reflect(normal, dest);
        return this;
    }

    @Override
    public Vector3f reflect(float x, float y, float z) {
        super.reflect(x, y, z);
        return this;
    }

    @Override
    public Vector3f reflect(Vector3fc normal) {
        super.reflect(normal);
        return this;
    }

    public Vector3f absolute(Vector3f dest) {
        super.absolute(dest);
        return this;
    }

    @Override
    public Vector3f absolute() {
        super.absolute();
        return this;
    }

    public Vector3f negate(Vector3f dest) {
        super.negate(dest);
        return this;
    }

    @Override
    public Vector3f negate() {
        super.negate();
        return this;
    }

    @Override
    public String toString() {
        return super.toString(DecimalFormat.getInstance());
    }

    @Override
    public Vector3f zero() {
        super.zero();
        return this;
    }

    @Override
    public Vector3f max(Vector3fc v) {
        super.max(v);
        return this;
    }

    @Override
    public Vector3f min(Vector3fc v) {
        super.min(v);
        return this;
    }

    public Vector3f cross(float x, float y, float z, Vector3f dest) {
        super.cross(x, y, z, dest);
        return this;
    }

    public Vector3f cross(Vector3fc v, Vector3f dest) {
        super.cross(v, dest);
        return this;
    }

    @Override
    public Vector3f cross(float x, float y, float z) {
        super.cross(x, y, z);
        return this;
    }

    @Override
    public Vector3f cross(Vector3fc v) {
        super.cross(v);
        return this;
    }

    public Vector3f normalize(Vector3f dest) {
        super.normalize(dest);
        return this;
    }

    @Override
    public Vector3f normalize() {
        super.normalize();
        return this;
    }

    public Vector3f rotateAbout(float angle, float x, float y, float z, Vector3f dest) {
        super.rotateAbout(angle, x, y, z, dest);
        return this;
    }

    @Override
    public Vector3f rotateAbout(float angle, float x, float y, float z) {
        super.rotateAbout(angle, x, y, z);
        return this;
    }

    public Vector3f rotate(Quaternionfc quat, Vector3f dest) {
        super.rotate(quat, dest);
        return this;
    }

    @Override
    public Vector3f rotate(Quaternionfc quat) {
        super.rotate(quat);
        return this;
    }

    public Vector3f div(float x, float y, float z, Vector3f dest) {
        super.div(x, y, z, dest);
        return this;
    }

    @Override
    public Vector3f div(float x, float y, float z) {
        super.div(x, y, z);
        return this;
    }

    public Vector3f div(float scalar, Vector3f dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Vector3f div(float scalar) {
        super.div(scalar);
        return this;
    }

    public Vector3f mul(float x, float y, float z, Vector3f dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    @Override
    public Vector3f mul(float x, float y, float z) {
        super.mul(x, y, z);
        return this;
    }

    public Vector3f mul(float scalar, Vector3f dest) {
        super.mul(scalar, dest);
        return this;
    }

    @Override
    public Vector3f mul(float scalar) {
        super.mul(scalar);
        return this;
    }

    public Vector3f mulTransposeDirection(Matrix4fc mat, Vector3f dest) {
        super.mulTransposeDirection(mat, dest);
        return this;
    }

    @Override
    public Vector3f mulTransposeDirection(Matrix4fc mat) {
        super.mulTransposeDirection(mat);
        return this;
    }

    public Vector3f mulDirection(Matrix4x3fc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    public Vector3f mulDirection(Matrix4fc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Vector3f mulDirection(Matrix4x3fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Vector3f mulDirection(Matrix4fc mat) {
        super.mulDirection(mat);
        return this;
    }

    public Vector3f mulTransposePosition(Matrix4fc mat, Vector3f dest) {
        super.mulTransposePosition(mat, dest);
        return this;
    }

    @Override
    public Vector3f mulTransposePosition(Matrix4fc mat) {
        super.mulTransposePosition(mat);
        return this;
    }

    public Vector3f mulPosition(Matrix4x3fc mat, Vector3f dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    public Vector3f mulPosition(Matrix4fc mat, Vector3f dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Vector3f mulPosition(Matrix4x3fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Vector3f mulPosition(Matrix4fc mat) {
        super.mulPosition(mat);
        return this;
    }

    public Vector3f mulTranspose(Matrix3fc mat, Vector3f dest) {
        super.mulTranspose(mat, dest);
        return this;
    }

    @Override
    public Vector3f mulTranspose(Matrix3fc mat) {
        super.mulTranspose(mat);
        return this;
    }

    public Vector3f mul(Matrix3x2fc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Vector3f mul(Matrix3x2fc mat) {
        super.mul(mat);
        return this;
    }

    public Vector3f mul(Matrix3dc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Vector3f mul(Matrix3dc mat) {
        super.mul(mat);
        return this;
    }

    public Vector3f mul(Matrix3fc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Vector3f mul(Matrix3fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Vector3f mulProject(Matrix4fc mat) {
        super.mulProject(mat);
        return this;
    }

    public Vector3f mulProject(Matrix4fc mat, Vector3f dest) {
        super.mulProject(mat, dest);
        return this;
    }

    public Vector3f div(Vector3fc v, Vector3f dest) {
        super.div(v, dest);
        return this;
    }

    @Override
    public Vector3f div(Vector3fc v) {
        super.div(v);
        return this;
    }

    public Vector3f mul(Vector3fc v, Vector3f dest) {
        super.mul(v, dest);
        return this;
    }

    public Vector3f fma(float a, Vector3fc b, Vector3f dest) {
        super.fma(a, b, dest);
        return this;
    }

    public Vector3f fma(Vector3fc a, Vector3fc b, Vector3f dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Vector3f fma(float a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Vector3f fma(Vector3fc a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    public Vector3f add(float x, float y, float z, Vector3f dest) {
        super.add(x, y, z, dest);
        return this;
    }

    @Override
    public Vector3f add(float x, float y, float z) {
        super.add(x, y, z);
        return this;
    }

    public Vector3f add(Vector3fc v, Vector3f dest) {
        super.add(v, dest);
        return this;
    }

    public Vector3f sub(float x, float y, float z, Vector3f dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    @Override
    public Vector3f sub(float x, float y, float z) {
        super.sub(x, y, z);
        return this;
    }

    public Vector3f sub(Vector3fc v, Vector3f dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Vector3f sub(Vector3fc v) {
        super.sub(v);
        return this;
    }

    @Override
    public Vector3f setComponent(int component, float value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
    }

    @Override
    public Vector3f set(int index, FloatBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Vector3f set(FloatBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Vector3f set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Vector3f set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Vector3f set(float x, float y, float z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Vector3f set(float d) {
        super.set(d);
        return this;
    }

    @Override
    public Vector3f set(Vector2fc v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Vector3f set(Vector3dc v) {
        super.set(v);
        return this;
    }

    @Override
    public Vector3f set(Vector3fc v) {
        super.set(v);
        return this;
    }

}
