package com.boss.deadcells;

import java.util.List;

public class GoblinEnemy extends Enemy {
    public GoblinEnemy(float x, float y) {
        super(x, y, 60, "goblin.png");
    }

    @Override
    public void update(float delta, Player player, List<Platform> platforms) {
        super.update(delta, player, platforms);
    }
}
