/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jphysics.sad;

import br.pucpr.mage.FrameBuffer;
import br.pucpr.mage.Mesh;
import br.pucpr.mage.Shader;
import br.pucpr.mage.Texture;
import br.pucpr.mage.camera.Camera;
import br.pucpr.mage.camera.OrthographicCamera;
import br.pucpr.mage.camera.PerspectiveCamera;
import br.pucpr.mage.phong.DirectionalLight;
import br.pucpr.mage.phong.MultiTextureMaterial;
import br.pucpr.mage.phong.SkyMaterial;
import br.pucpr.mage.phong.SpaceMaterial;
import br.pucpr.mage.phong.WaterMaterial;
import br.pucpr.mage.postfx.PostFXMaterial;
import br.pucpr.mage.screen.Window;
import br.pucpr.mage.screen.Screen;
import br.pucpr.mage.util.MeshFactory;
import br.pucpr.mage.util.ResourceUtil;
import java.io.File;
import java.io.IOException;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FRONT_FACE;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPolygonMode;

/**
 *
 * @author luis
 */
public class MainGameScreen extends Screen {

    private static final float WATER_H = 11.0f;

    //Dados da cena
    private final Camera camera = new PerspectiveCamera();
    private DirectionalLight light;

    //Dados da malha
    private Mesh mesh;
    private MultiTextureMaterial material;

    //Dados do skydome
    private Mesh skydome;
    private SpaceMaterial skyMaterial;

    //Dados da água
    private Mesh water;
    private WaterMaterial waterMaterial;

    private Mesh canvas;
    private FrameBuffer fb;
    private PostFXMaterial postFX;

    private FrameBuffer refractionFB;
    private FrameBuffer reflectionFB;

    @Override
    public void init() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glPolygonMode(GL_FRONT_FACE, GL_LINE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        camera.getPosition().set(0.0f, 20.0f, 125.0f);

        light = new DirectionalLight(
                new Vector3f(1.0f, -1.0f, -1.0f), //direction
                new Vector3f(0.1f, 0.1f, 0.1f), //ambient
                new Vector3f(1.0f, 1.0f, 1.0f), //diffuse
                new Vector3f(1.0f, 1.0f, 1.0f));   //specular

        //Carga do terreno
        try {
            mesh = MeshFactory.loadTerrain(ResourceUtil.loadFile("images", "heights/river.png"), 0.4f, 3);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        material = new MultiTextureMaterial(
                new Vector3f(1.0f, 1.0f, 1.0f), //ambient
                new Vector3f(0.9f, 0.9f, 0.9f), //diffuse
                new Vector3f(0.0f, 0.0f, 0.0f), //specular
                0.0f);                          //specular power

        material.setTextures(
                new Texture("images/textures/sand.png"),
                new Texture("images/textures/grass.png"),
                new Texture("images/textures/rock.png"),
                new Texture("images/textures/snow.png")
        );

        //Carga do Skydome
        skydome = MeshFactory.createSphere(20, 20);

        skyMaterial = new SpaceMaterial();
        skyMaterial.setCloud1(new Texture("images/textures/cloud1.png"));
        skyMaterial.setCloud2(new Texture("images/textures/cloud2.png"));

        //Carga da água
        water = MeshFactory.createXZSquare(400, 300, WATER_H);
        waterMaterial = new WaterMaterial();

        //Carga do canvas para o PostFX
        canvas = MeshFactory.createCanvas();
        fb = FrameBuffer.forCurrentViewport();
        postFX = PostFXMaterial.defaultPostFX("fxNone", fb);

        //Cria o framebuffer da refra��o
        refractionFB = FrameBuffer.forCurrentViewport();
        waterMaterial.setRefraction(refractionFB);

        //Cria o framebuffer do reflexo
        reflectionFB = FrameBuffer.forCurrentViewport();
        waterMaterial.setReflection(reflectionFB);

    }

    @Override
    public void update(float secs) {
        skyMaterial.addTime(secs);
        camera.getTarget().set((float) mouse.getPos().x, (float) -mouse.getPos().y, 0.0f);
    }

    public void drawSky(Matrix4f viewMatrix) {
        glDisable(GL_DEPTH_TEST);
        Shader shader = skyMaterial.getShader();
        shader.bind()
                .setUniform("uProjection", camera.getProjectionMatrix())
                .setUniform("uView", viewMatrix)
                .unbind();

        skydome.setUniform("uWorld", new Matrix4f().scale(300));
        skydome.draw(skyMaterial);
        glEnable(GL_DEPTH_TEST);
    }

    public void drawSky() {
        drawSky(camera.getViewMatrix());
    }

    public void drawTerrain(Matrix4f viewMatrix) {
        Shader shader = material.getShader();
        shader.bind()
                .setUniform("uProjection", camera.getProjectionMatrix())
                .setUniform("uView", viewMatrix)
                .setUniform("uCameraPosition", camera.getPosition());
        light.apply(shader);
        shader.unbind();

        mesh.setUniform("uWorld", new Matrix4f().rotateY((float) Math.toRadians(85)));
        mesh.draw(material);
    }

    public void drawTerrain() {
        drawTerrain(camera.getViewMatrix());
    }

    public void drawWater() {
        Shader shader = waterMaterial.getShader();
        shader.bind()
                .setUniform("uProjection", camera.getProjectionMatrix())
                .setUniform("uView", camera.getViewMatrix())
                .setUniform("uReflexView", getReflexView())
                .setUniform("uCameraPosition", camera.getPosition());
        shader.unbind();
        water.setUniform("uWorld", new Matrix4f());
        water.draw(waterMaterial);
    }

    public Matrix4f getReflexView() {
        Vector3f reflPos = new Vector3f(camera.getPosition());
        reflPos.y = -reflPos.y + WATER_H * 2;

        Matrix4f reflexView = new Matrix4f();
        reflexView.lookAt(
                reflPos,
                new Vector3f((float) mouse.getPos().x, (float) mouse.getPos().y + WATER_H * 2, 0),
                new Vector3f(0, 1, 0));
        return reflexView;
    }

    public void drawRefraction() {
        material.setClippingPlane(new Vector4f(0, -1, 0, WATER_H));
        refractionFB.bind();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        drawTerrain();
        refractionFB.unbind();
        material.setClippingPlane(null);
    }

    public void drawReflection() {
        material.setClippingPlane(new Vector4f(0, 1, 0, -WATER_H + 0.5f));
        reflectionFB.bind();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        drawSky(getReflexView());
        drawTerrain(getReflexView());
        reflectionFB.unbind();
        material.setClippingPlane(null);
    }

    public void drawScene() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        drawSky();
        drawTerrain();
        drawWater();
    }

    @Override
    public void draw() {
        drawRefraction();
        drawReflection();

        fb.bind();
        drawScene();
        fb.unbind();

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        canvas.draw(postFX);
    }

    public static void main(String[] args) {
        Window window = new Window(new MainGameScreen(), "S.A.D.");

        // This will lock current Thread.
        window.show();
    }
}
