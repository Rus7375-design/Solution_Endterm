package com.boss.deadcells.enemies;

import com.boss.deadcells.Enemy;
import com.boss.deadcells.Player;

public class MeleeBehavior implements EnemyBehavior {
    private float attackCooldown = 2f;

    @Override
    public void update(float delta, Enemy enemy, Player player) {
        attackCooldown -= delta;

        float moveSpeed = 40 * delta;
        float enemyX = enemy.getX(); // используем геттер
        if (player.getX() > enemyX) {
            enemy.setX(enemyX + moveSpeed); // используем сеттер
        } else {
            enemy.setX(enemyX - moveSpeed);
        }

        if (attackCooldown <= 0 && enemy.getBounds().overlaps(player.getBounds())) {
            player.takeDamage(10);
            attackCooldown = 2f;
        }
    }
}
