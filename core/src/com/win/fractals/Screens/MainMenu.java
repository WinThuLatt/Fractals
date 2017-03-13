package com.win.fractals.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.win.fractals.Fractals;
import com.win.fractals.Utilities.Menu;

/**
 * Created by WIN THU LATT on 12/11/2016.
 */

public class MainMenu implements Screen
{
    private Fractals f;
    private Menu m;

    public MainMenu(Fractals f)
    {
	this.f = f;

	m = new Menu(f);
    }

    @Override
    public void show()
    {

    }

    public void handleInput()
    {
	if (m.isMengerPressed())
	{
	    f.setScreen(new MengerFractal(f));
	}
	if (m.isMengerRPressed())
	{
	    f.setScreen(new ReversedMengerFractal(f));
	}
	if (m.isMandelbrotPressed())
	{
	    f.setScreen(new Mandelbrot(f));
	}
	if (m.isTreesPressed())
	{
	    f.setScreen(new CircleFractals(f));
	}
    }

    @Override
    public void render(float delta)
    {
	Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	handleInput();
	m.draw();
    }

    @Override
    public void resize(int width, int height)
    {

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

}
