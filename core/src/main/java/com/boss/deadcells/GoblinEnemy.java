package com.boss.deadcells;

import java.util.List;

public class GoblinEnemy extends Enemy {
    public GoblinEnemy(float x, float y) {
        super(x, y, 60, "goblin.png");
    }

    @Override
    public void update(float delta, Player player, List<Platform> platforms) {
        float distance = Math.abs(player.getX() - this.x);
        if (distance < 200) {
            this.x += (player.getX() > this.x ? 1 : -1) * 100 * delta;
        }
    }
}
