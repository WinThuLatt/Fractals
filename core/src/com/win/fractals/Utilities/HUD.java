package com.win.fractals.Utilities;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.win.fractals.Fractals;

/**
 * Created by WIN THU LATT on 10/21/2016.
 */

public class HUD implements Disposable
{
    private Fractals app;
    private Viewport viewport;
    private OrthographicCamera camera;
    public Stage stage;
    private Skin skin;
    private BitmapFont font;

    public boolean isMenuPressed()
    {
	return menuPressed;
    }

    private boolean menuPressed;

    public HUD(Fractals app)
    {
	camera = new OrthographicCamera();
	camera.setToOrtho(false, Fractals.WIDTH, Fractals.HEIGHT);

	viewport = new FitViewport(Fractals.WIDTH, Fractals.HEIGHT, camera);
	stage = new Stage(viewport, app.batch);
	this.app = app;

	skin = new Skin(Gdx.files.internal("uiskin_1.json"));

	font = new BitmapFont(Gdx.files.internal("cool.fnt"));

	TextButton menuButton = new TextButton("Main Menu", skin);
	menuButton.addListener(new InputListener()
	{
	    @Override
	    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	    {
		menuPressed = true;
		return true;
	    }

	    @Override
	    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
	    {
		menuPressed = false;
	    }
	});

	stage.addActor(menuButton);
    }

    public void draw()
    {
	if (!Gdx.app.getType().equals(ApplicationType.Android))
	{
	    app.batch.setProjectionMatrix(viewport.getCamera().combined);
	    app.batch.begin();
	    font.draw(app.batch, "Press 'R' to subdivide", 0, Fractals.HEIGHT);
	    app.batch.end();
	    stage.draw();
	} else
	{
	    app.batch.setProjectionMatrix(viewport.getCamera().combined);
	    app.batch.begin();
	    font.draw(app.batch, "Tap to subdivide", 0, Fractals.HEIGHT);
	    app.batch.end();
	    stage.draw();
	}
    }

    public void update(int width, int height)
    {
	viewport.update(width, height);
    }

    @Override
    public void dispose()
    {
	font.dispose();
    }
}
