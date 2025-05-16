package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
    private Rectangle bounds;
    private Texture texture;

    public Platform(float x, float y, float width, float height, String texturePath) {
        bounds = new Rectangle(x, y, width, height);
        texture = new Texture(texturePath);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }
    public void dispose() {
        texture.dispose();
    }


    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }


}
