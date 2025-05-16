package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private float x, y;
    private final float width = 16, height = 8;
    private final float speed = 500;
    private final boolean toRight;
    private final Texture texture;

    public Bullet(float x, float y, boolean toRight) {
        this.x = x;
        this.y = y;
        this.toRight = toRight;
        this.texture = new Texture("bullet.png"); // обязательно наличие файла
    }

    public void update(float delta) {
        x += (toRight ? 1 : -1) * speed * delta;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public void dispose() {
        texture.dispose();
    }
}
