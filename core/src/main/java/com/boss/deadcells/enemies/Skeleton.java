package com.boss.deadcells.enemies;

import com.boss.deadcells.Enemy;
import com.boss.deadcells.Player;
import com.boss.deadcells.Platform;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class Skeleton extends Enemy {

    public Skeleton(float x, float y) {
        super(x, y, 60, "skeleton.png");
        this.behavior = new MeleeBehavior();
    }

    @Override
    public void update(float delta, Player player, List<Platform> platforms) {
        behavior.update(delta, this, player);
    }
}
