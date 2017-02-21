package com.win.fractals.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.win.fractals.Fractals;
import com.win.fractals.Utilities.HUD;

import java.util.ArrayList;

/**
 * Created by WIN THU LATT on 12/7/2016.
 */

public class ReversedMengerFractal implements Screen, InputProcessor
{
    public Environment environment;
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public ModelBatch modelBatch;
    public Model model;
    public ModelBuilder modelBuilder;
    public ModelInstance instance;
    public ArrayList<ModelInstance> instances;
    public ArrayList<Box> boxes;
    public Vector3 pos;
    public float r = 5;
    public HUD hud;
    private Fractals f;

    public ReversedMengerFractal(Fractals f)
    {
        this.f = f;
        hud = new HUD(f);
        pos = new Vector3(0, 0, 0);
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(r, r, r,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        instances = new ArrayList<ModelInstance>();
        instances.add(instance);

        boxes = new ArrayList<Box>();
        boxes.add(new Box(5, new Vector3(), instance));


        camController = new CameraInputController(cam);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Array<InputProcessor> processors = new Array<InputProcessor>();
        processors.add(hud.stage);
        processors.add(this);
        processors.add(camController);
        inputMultiplexer.setProcessors(processors);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show()
    {

    }

    public void handleInput()
    {
        if (hud.isMenuPressed())
        {
            System.out.println("menu Pres");
            f.setScreen(new MainMenu(f));
        }
    }

    @Override
    public void render(float delta)
    {
        camController.update();
        handleInput();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        hud.draw();
        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        hud.update(width, height);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        int to = instances.size();
        ArrayList<ModelInstance> newList = new ArrayList<ModelInstance>();
        ArrayList<Box> newBoxes = new ArrayList<Box>();
        if (keycode == Input.Keys.R)
        {

            for (Box i : boxes)
            {
                float newR = i.radius / 3.0f;
                pos = i.position;
                for (int x = -1; x < 2; x++)
                {
                    for (int y = -1; y < 2; y++)
                    {
                        for (int z = -1; z < 2; z++)
                        {
                            int sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
                            if (sum <= 1)
                            {
                                model = modelBuilder.createBox(newR, newR, newR,
                                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                                instance = new ModelInstance(model);
                                float xpos = pos.x + newR * x;
                                float ypos = pos.y + newR * y;
                                float zpos = pos.z + newR * z;
                                instance.transform.setToTranslation(xpos, ypos, zpos);
                                instances.add(instance);

                                newBoxes.add(new Box(newR, new Vector3(xpos, ypos, zpos), instance));
                            }
                        }
                    }
                }

            }
            System.out.println(instances.size());

            instances.addAll(newList);
            instances.subList(0, to).clear();


            boxes.addAll(newBoxes);
            boxes.subList(0, to).clear();

            System.out.println(instances.size());


        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return true;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
