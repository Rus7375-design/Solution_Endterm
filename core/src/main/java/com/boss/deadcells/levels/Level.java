package com.boss.deadcells.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boss.deadcells.Player;
import com.boss.deadcells.Platform;
import com.boss.deadcells.items.Bullet;

import java.util.List;

public interface Level {
    void update(float delta, Player player, List<Bullet> bullets);
    void render(SpriteBatch batch, Player player);
    List<Platform> getPlatforms();
    boolean isFinished(Player player);
    boolean isCompleted();
    void dispose();
}
