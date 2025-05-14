package com.boss.deadcells.enemies;

import com.boss.deadcells.Enemy;
import com.boss.deadcells.Player;

public interface EnemyBehavior {
    void update(float delta, Enemy enemy, Player player);
}
