package com.boss.deadcells.enemies;

import com.boss.deadcells.Enemy;
import com.boss.deadcells.Player;

public class RangedBehavior implements EnemyBehavior {
    private float cooldown = 3f;

    @Override
    public void update(float delta, Enemy enemy, Player player) {
        cooldown -= delta;

        if (cooldown <= 0) {
            // TODO: реализовать выстрел пули от шамана
            cooldown = 3f;
        }
    }
}
