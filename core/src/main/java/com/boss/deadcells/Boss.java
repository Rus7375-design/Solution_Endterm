package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class Boss extends Enemy {
    private float attackCooldown = 2f;

    public Boss(float x, float y) {
        super(x, y, 300, "boss.png");
    }

    @Override
    public float getWidth() {
        return 64;
    }

    @Override
    public float getHeight() {
        return 80;
    }

    @Override
    public void update(float delta, Player player, List<Platform> platforms) {
        attackCooldown -= delta;

        // Простейшее перемещение в сторону игрока
        if (player.getX() > x) x += 50 * delta;
        else x -= 50 * delta;

        // Ближняя атака
        if (attackCooldown <= 0 && getBounds().overlaps(player.getBounds())) {
            player.takeDamage(20);
            attackCooldown = 3f;
        }

        // Дальняя атака — вызывается из GameScreen или CaveLevel при необходимости
    }

    public void shootAtPlayer(Player player, List<BossProjectile> projectiles) {
        if (attackCooldown <= 0) {
            projectiles.add(new BossProjectile(x + getWidth()/2, y + getHeight()/2, player.getX(), player.getY()));
            attackCooldown = 3f;
        }
    }
}
