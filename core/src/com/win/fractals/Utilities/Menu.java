package com.win.fractals.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.win.fractals.Fractals;

/**
 * Created by WIN THU LATT on 10/21/2016.
 */

public class Menu implements Disposable
{
    private Viewport viewport;
    private Stage stage;
    private Table table;
    private Skin skin;
    private boolean mengerPressed, mengerRPressed, mandelbrotPressed, treesPressed;
    private OrthographicCamera camera;

    public boolean isTreesPressed()
    {
	return treesPressed;
    }

    public boolean isMengerPressed()
    {
	return mengerPressed;
    }

    public boolean isMengerRPressed()
    {
	return mengerRPressed;
    }

    public boolean isMandelbrotPressed()
    {
	return mandelbrotPressed;
    }

    public Menu(Fractals app)
    {
	camera = new OrthographicCamera();
	camera.setToOrtho(false, Fractals.WIDTH, Fractals.HEIGHT);
	viewport = new FitViewport(Fractals.WIDTH, Fractals.HEIGHT, camera);
	stage = new Stage(viewport, app.batch);
	skin = new Skin(Gdx.files.internal("uiskin_1.json"));

	table = new Table(skin);

	TextButton menger = new TextButton("Menger", skin);
	TextButton mengerR = new TextButton("Reversed Menger", skin);
	TextButton mandelbrot = new TextButton("Mandelbrot Set", skin);
	TextButton trees = new TextButton("CircleFractals", skin);

	menger.addListener(new ClickListener()
	{

	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		mengerPressed = true;
	    }
	});

	mengerR.addListener(new ClickListener()
	{
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		mengerRPressed = true;
	    }
	});

	mandelbrot.addListener(new ClickListener()
	{
	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		mandelbrotPressed = true;
	    }
	});

	trees.addListener(new ClickListener()
	{

	    @Override
	    public void clicked(InputEvent event, float x, float y)
	    {
		treesPressed = true;
	    }

	});

	table.setFillParent(true);
	table.add(menger);
	table.row();
	table.add(mengerR);
	table.row();
	table.add(mandelbrot);
	table.row();
	table.add(trees);

	stage.addActor(table);
	stage.addListener(new InputListener()
	{
	    @Override
	    public boolean keyDown(InputEvent event, int keycode)
	    {
		if (keycode == Input.Keys.MENU)
		{
		    System.out.println("MENU");
		}
		return true;
	    }

	    @Override
	    public boolean keyUp(InputEvent event, int keycode)
	    {
		return super.keyUp(event, keycode);
	    }
	});

	Gdx.input.setCatchBackKey(true);
	Gdx.input.setCatchMenuKey(true);

	Gdx.input.setInputProcessor(stage);
    }

    public void draw()
    {
	stage.draw();
    }

    @Override
    public void dispose()
    {
    }

}
