package com.win.fractals.Utilities;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.win.fractals.Fractals;

/**
 * Created by WIN THU LATT on 10/21/2016.
 */

public class MandelbrotHUD implements Disposable
{

    private Fractals app;
    private Viewport viewport;
    private OrthographicCamera camera;
    public Stage stage;
    private Skin skin;
    private BitmapFont font;
    private final int XMIN = -3, XMAX = 3, YMIN = -3, YMAX = 3;
    public int xminVal = XMIN, yminVal = YMIN, xmaxVal = XMAX, ymaxVal = YMAX;

    public boolean isMenuPressed()
    {
	return menuPressed;
    }

    private boolean menuPressed;

    public MandelbrotHUD(Fractals app, final ICallback cb)
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

	Label xmin = new Label("x min", skin);
	Label xmax = new Label("x max", skin);
	Label ymin = new Label("y min", skin);
	Label ymax = new Label("y max", skin);

	final Slider xminSlider = new Slider(XMIN, XMAX, 0.1f, false, skin);
	final Slider yminSlider = new Slider(YMIN, YMAX, 0.1f, false, skin);
	final Slider xmaxSlider = new Slider(XMIN, XMAX, 0.1f, false, skin);
	final Slider ymaxSlider = new Slider(YMIN, YMAX, 0.1f, false, skin);

	xminSlider.addListener(new ChangeListener()
	{
	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		xminVal = (int) xminSlider.getValue();
		cb.Redraw(xminVal, xmaxVal, yminVal, ymaxVal);
	    }
	});

	xmaxSlider.addListener(new ChangeListener()
	{
	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		xmaxVal = (int) xmaxSlider.getValue();
		cb.Redraw(xminVal, xmaxVal, yminVal, ymaxVal);
	    }
	});

	yminSlider.addListener(new ChangeListener()
	{
	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		yminVal = (int) yminSlider.getValue();
		cb.Redraw(xminVal, xmaxVal, yminVal, ymaxVal);
	    }
	});

	ymaxSlider.addListener(new ChangeListener()
	{
	    @Override
	    public void changed(ChangeEvent event, Actor actor)
	    {
		ymaxVal = (int) ymaxSlider.getValue();
		cb.Redraw(xminVal, xmaxVal, yminVal, ymaxVal);
	    }
	});

	Table table = new Table();
	table.left().top();
	table.setFillParent(true);
	table.add(menuButton);
	table.row();
	table.add(xmin);
	table.add(xminSlider).width(100);
	table.row();
	table.add(xmax);
	table.add(xmaxSlider).width(100);
	table.row();
	table.add(ymin);
	table.add(yminSlider).width(100);
	table.row();
	table.add(ymax);
	table.add(ymaxSlider).width(100);
	stage.addActor(table);
    }

    public void draw()
    {
	if (!Gdx.app.getType().equals(ApplicationType.Android))
	{
	    app.batch.setProjectionMatrix(viewport.getCamera().combined);
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
