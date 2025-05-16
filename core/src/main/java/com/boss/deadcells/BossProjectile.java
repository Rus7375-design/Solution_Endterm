package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BossProjectile {
    private float x, y;
    private float dx, dy;
    private float speed = 200f;
    private Texture texture;
    private Rectangle bounds;
    private boolean active = true;

    public BossProjectile(float startX, float startY, float targetX, float targetY) {
        this.x = startX;
        this.y = startY;
        this.texture = new Texture("fireball.png");

        float deltaX = targetX - startX;
        float deltaY = targetY - startY;
        float length = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        this.dx = deltaX / length;
        this.dy = deltaY / length;

        this.bounds = new Rectangle(x, y, 16, 16);
    }

    public void update(float delta) {
        x += dx * speed * delta;
        y += dy * speed * delta;
        bounds.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        if (active) batch.draw(texture, x, y, 16, 16);
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
