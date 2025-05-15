package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boss.deadcells.Player;

public abstract class Item {
    protected Texture texture;

    public abstract void use(Player player);
    public abstract void render(SpriteBatch batch, float x, float y);
}
