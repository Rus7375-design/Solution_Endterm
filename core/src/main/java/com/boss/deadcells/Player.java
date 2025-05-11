package com.boss.deadcells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;
import com.boss.deadcells.items.Weapon;
import com.boss.deadcells.items.WeaponFactory;
import com.boss.deadcells.items.Bullet;


public class Player {
    private Weapon currentWeapon;
    private boolean facingRight = true;

    private float x, y;
    private final float width = 32, height = 48;
    private float velocityY = 0;
    private final int maxHealth = 100;
    private int health = maxHealth;
    private final float speed = 200;
    private boolean onGround = false;
    private boolean attacking = false;
    private float attackCooldown = 0;

    private Texture texture;

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        texture = new Texture("player.png");
        this.currentWeapon = WeaponFactory.create("sword"); // стартовое оружие

    }

    public void update(float delta, List<Platform> platforms) {

        float gravity = -800;
        float jumpPower = 400;


        // Горизонтальное движение с коллизией
        float nextX = x;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            nextX -= speed * delta;
            facingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            nextX += speed * delta;
            facingRight = true;
        }


        Rectangle nextRect = new Rectangle(nextX, y, width, height);
        boolean blocked = false;
        for (Platform p : platforms) {
            if (nextRect.overlaps(p.getBounds())) {
                blocked = true;
                break;
            }
        }
        if (!blocked) x = nextX;

        // Прыжок
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        // Гравитация
        velocityY += gravity * delta;
        y += velocityY * delta;

        // Вертикальные коллизии
        Rectangle playerRect = new Rectangle(x, y, width, height);
        onGround = false;
        for (Platform p : platforms) {
            Rectangle platformRect = p.getBounds();
            if (playerRect.overlaps(platformRect) && velocityY <= 0) {
                y = platformRect.y + platformRect.height;
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
    public boolean isFacingRight() {
        return facingRight;
    }


    public boolean isAttacking() {
        return attacking;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public int getCurrentHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isFalling() {
        return !onGround && y < -100;
    }

    public float getX() {
        return x + width / 2;
    }

    public float getY() {
        return y + height / 2;
    }

    public Weapon getWeapon() {
        return currentWeapon;
    }

}
