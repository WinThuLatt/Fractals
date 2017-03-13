package com.win.fractals.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.win.fractals.Fractals;
import com.win.fractals.Utilities.CircleFractalsHUD;

/**
 * Created by WIN THU LATT on 12/7/2016.
 * <p>
 * this is inspired from the 10 min challenges done by rainbow code from
 * youtube.
 */

public class CircleFractals implements Screen, InputProcessor
{

    public CircleFractalsHUD hud;
    private Fractals f;

    private float circleRadius = 100.0f;
    private Whatever circle;

    private float centerX = 0.0f;
    private float centerY = 0.0f;

    public class Whatever
    {
	private int index = 0;

	private Whatever parent;
	private float r;
	private float x;
	private float y;
	private Whatever child = null;

	public Whatever(float _x, float _y, float _r, Whatever _p)
	{
	    this.r = _r;
	    this.x = _x;
	    this.y = _y;
	    this.parent = _p;
	}

	public Whatever(float _x, float _y, float _r)
	{
	    this(_x, _y, _r, null);
	}

	public Whatever addChild()
	{
	    float newR = r * 0.5f;
	    float newX = x + newR;
	    float newY = y;
	    child = new Whatever(newX, newY, newR, this);
	    child.index = this.index + 1;
	    return child;
	}

	@Override
	public String toString()
	{
	    return x + " " + y + " " + r;
	}
    }

    private ShapeRenderer shapeRenderer;
    private boolean run = true;

    private void RunThis()
    {
	circle = new Whatever(centerX, centerY, circleRadius);
	Whatever source = circle;
	for (int i = 0; i < 2; i++)
	{
	    source = source.addChild();
	}
    }

    public CircleFractals(Fractals f)
    {
	this.f = f;
	hud = new CircleFractalsHUD(f);

	shapeRenderer = new ShapeRenderer();

	centerX = Gdx.graphics.getWidth() / 2f;
	centerY = Gdx.graphics.getHeight() / 2f;

	if (run)
	{
	    RunThis();
	    run = false;
	}

	InputMultiplexer inputMultiplexer = new InputMultiplexer();

	Array<InputProcessor> processors = new Array<InputProcessor>();
	processors.add(hud.stage);
	processors.add(this);
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
	    System.out.println("menu Pressed");
	    f.setScreen(new MainMenu(f));
	}

    }

    @Override
    public void render(float delta)
    {
	handleInput();
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	hud.draw();
	Whatever next = circle;

	shapeRenderer.begin(ShapeType.Line);
	while (next != null)
	{
	    shapeRenderer.circle(next.x, next.y, next.r);
	    next = next.child;
	}
	shapeRenderer.end();

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
    }

    @Override
    public boolean keyDown(int keycode)
    {
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
	return true;
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

    public float map(float val, float min, float max, float minTarget, float maxTarget)
    {
	return (float) ((val - min) / (max - min) * (maxTarget - minTarget) + minTarget);
    }
}
