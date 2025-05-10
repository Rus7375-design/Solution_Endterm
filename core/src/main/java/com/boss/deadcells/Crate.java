package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Crate extends Platform {
    private Texture texture;

    public Crate(float x, float y) {
        super(x, y, 32, 32, "crate.png");
        texture = new Texture("crate.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
