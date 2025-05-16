// 📄 Enemy.java (добавлена отрисовка с размерами и обработка урона от пуль)
package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.boss.deadcells.enemies.EnemyBehavior;
import java.util.List;

public abstract class Enemy {
    protected float x, y;
    protected float velocityY = 0;
    protected boolean onGround = false;
    protected int health;
    protected int maxHealth;
    protected Texture texture;
    protected boolean recentlyHit = false;
    protected EnemyBehavior behavior;
    protected float speed = 80f;
    protected float attackCooldown = 0;

    public Enemy(float x, float y, int health, String texturePath) {
        this.x = x;
        this.y = y;
        this.health = this.maxHealth = health;
        this.texture = new Texture(texturePath);
    }

    public void updateMovement(float delta, Player player, List<Platform> platforms) {
        float gravity = -800;
        velocityY += gravity * delta;

        float direction = player.getX() > x ? 1 : -1;
        float nextX = x + direction * speed * delta;
        float nextY = y + velocityY * delta;

        Rectangle bounds = new Rectangle(nextX, nextY, getWidth(), getHeight());
        onGround = false;

        for (Platform p : platforms) {
            if (bounds.overlaps(p.getBounds())) {
                if (y > p.getY()) {
                    nextY = p.getY() + p.getHeight();
                    velocityY = 0;
                    onGround = true;
                } else if (x < p.getX()) {
                    nextX = p.getX() - getWidth();
                } else {
                    nextX = p.getX() + p.getWidth();
                }
            }
        }

        if (onGround && Math.abs(player.getX() - x) < 300) {
            x += direction * speed * delta;
        }

        if (onGround && Math.abs(player.getX() - x) < 150 && Math.abs(player.getY() - y) > 30) {
            velocityY = 400;
        }

        x = nextX;
        y = nextY;

        // атака при касании каждые 2 секунды
        attackCooldown -= delta;
        if (getBounds().overlaps(player.getBounds()) && attackCooldown <= 0) {
            player.takeDamage(10);
            attackCooldown = 2f;
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        recentlyHit = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return 32; }
    public float getHeight() { return 48; }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public abstract void update(float delta, Player player, List<Platform> platforms);

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, getWidth(), getHeight());
    }

    public void dispose() {
        texture.dispose();
    }
}
