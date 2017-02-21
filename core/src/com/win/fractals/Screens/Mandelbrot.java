package com.win.fractals.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.utils.Array;
import com.win.fractals.Fractals;
import com.win.fractals.Utilities.HUD2;
import com.win.fractals.Utilities.ICallback;

/**
 * Created by WIN THU LATT on 12/7/2016.
 * <p>
 * this is inspired from the 10 min challenges done by rainbow code from
 * youtube.
 */

public class Mandelbrot implements Screen, InputProcessor, ICallback
{
    public HUD2 hud;
    private Fractals f;

    private Texture texture;
    private int textureWidth = 360;
    private int textureHeight = 360;

    public Mandelbrot(Fractals f)
    {
	this.f = f;
	hud = new HUD2(f, this);

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

	if (texture != null)
	{
	    f.batch.begin();
	    f.batch.draw(texture, 0, 0);
	    f.batch.end();
	}
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

    @Override
    public void Redraw(float xmin, float xmax, float ymin, float ymax)
    {
	float R = 1;
	float G = 1;
	float B = 1;
	int maxIterations = 500;
	Color color;
	Pixmap pixmap = new Pixmap(textureWidth, textureHeight, Format.RGBA8888);

	for (int row = 0; row < textureWidth; row++)
	{
	    for (int col = 0; col < textureHeight; col++)
	    {

		float a = map(row, 0, textureWidth, xmin, xmax);
		float b = map(col, 0, textureHeight, ymin, ymax);

		float ca = a;
		float cb = b;

		int iter = 0;

		while (iter < maxIterations)
		{
		    float aa = a * a - b * b;
		    float bb = 2 * a * b;

		    a = aa + ca;
		    b = bb + cb;

		    if (a + b > 16)
		    {
			break;
		    }
		    iter++;
		}
		G = map(iter, 0, 100, 0, 1);
		G = map((float) Math.sqrt(G), 0, 1, 0, 1);
		if (iter == maxIterations)
		{
		    G = 0;
		}

		color = new Color(R, G, B, 1);
		pixmap.setColor(color);
		pixmap.drawPixel(row, col);
	    }
	}

	texture = new Texture(pixmap);

	pixmap.dispose();

	Gdx.app.log("redraw", "redraw called" + texture.toString());
    }

    public float map(float val, float min, float max, float minTarget, float maxTarget)
    {
	return (float) ((val - min) / (max - min) * (maxTarget - minTarget) + minTarget);
    }
}
