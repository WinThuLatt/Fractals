package com.win.fractals.Screens;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by my on 12/8/2016.
 */

public class Box
{
    public float radius = 5.0f;
    public ModelInstance instance;
    public Vector3 position = new Vector3();

    public Box(float r, Vector3 pos, ModelInstance ins)
    {
        radius = r;
        position = pos;
        instance = ins;
    }

    public ModelInstance getInstance()
    {
        return instance;
    }
}
