package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

public class Enemy {
    private float x, y;
    private float width = 32, height = 64;
    private float velocityY = 0;
    private float gravity = -500;
    private float speed = 50;

    private Texture texture;
    private boolean facingRight = true;
    private int health = 30;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("enemy.png");
    }

    public void update(float delta, Player player, List<Platform> platforms) {
        velocityY += gravity * delta;
        y += velocityY * delta;

        boolean onGround = false;
        Platform currentPlatform = null;

        for (Platform platform : platforms) {
            if (x + width > platform.getX() && x < platform.getX() + platform.getWidth()) {
                if (y > platform.getY() && y + velocityY * delta <= platform.getY() + platform.getHeight()) {
                    y = platform.getY() + platform.getHeight();
                    velocityY = 0;
                    onGround = true;
                    currentPlatform = platform;
                    break;
                }
            }
        }

        if (onGround) {
            float distanceToPlayer = Math.abs(player.getX() - x);
            if (distanceToPlayer < 150) {
                float nextX = x + (player.getX() < x ? -speed : speed) * delta;
                if (currentPlatform != null &&
                    nextX >= currentPlatform.getX() &&
                    nextX + width <= currentPlatform.getX() + currentPlatform.getWidth()) {
                    x = nextX;
                    facingRight = player.getX() >= x;
                }
            }
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("💀 Враг получил урон! Осталось HP: " + health);
        if (health <= 0) {
            x = -9999;
            y = -9999;
        }
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
