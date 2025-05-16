package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Door {
    private Texture texture;
    private float x, y;
    private Rectangle bounds;

    public Door(float x, float y) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("door.png"); 
        this.bounds = new Rectangle(x, y, 40, 64);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
