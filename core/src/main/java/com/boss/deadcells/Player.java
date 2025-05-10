package com.boss.deadcells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class Player {
    private float x, y;
    private float width = 32, height = 48;
    private float velocityY = 0;
    private int health = 100;
    private float speed = 200;
    private boolean onGround = false;
    private boolean attacking = false;
    private float attackCooldown = 0;

    private Texture texture;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        texture = new Texture("player.png");
    }

    public void update(float delta, List<Platform> platforms) {
        float gravity = -800;
        float jumpPower = 400;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += speed * delta;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        velocityY += gravity * delta;
        y += velocityY * delta;

        Rectangle playerRect = new Rectangle(x, y, width, height);
        onGround = false;

        for (Platform p : platforms) {
            if (playerRect.overlaps(p.getBounds()) && velocityY <= 0) {
                y = p.getBounds().y + p.getBounds().height;
                velocityY = 0;
                onGround = true;
                break;
            }
        }

        // Атака
        if (Gdx.input.isKeyJustPressed(Input.Keys.F) && attackCooldown <= 0) {
            attacking = true;
            attackCooldown = 0.5f;
        }

        attackCooldown -= delta;
        if (attackCooldown <= 0) attacking = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("💔 Игрок получил урон! Осталось HP: " + health);
    }

    public int getHealth() {
        return health;
    }

    public float getX() {
        return x + width / 2;
    }

    public float getY() {
        return y + height / 2;
    }
}
