package com.win.fractals;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.win.fractals.Screens.MainMenu;

public class Fractals extends Game
{
    public SpriteBatch batch;
    public TextureAtlas atlas;

    public static int WIDTH = 480, HEIGHT = 640;

    @Override
    public void create()
    {
        batch = new SpriteBatch();

        atlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));

        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose()
    {
    }
}
