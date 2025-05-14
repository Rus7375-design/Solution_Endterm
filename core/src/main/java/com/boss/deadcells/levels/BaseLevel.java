package com.boss.deadcells.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boss.deadcells.Player;
import com.boss.deadcells.items.Bullet;
import com.boss.deadcells.Platform;
import com.boss.deadcells.Enemy;
import com.boss.deadcells.Crate;

import java.util.List;

public abstract class BaseLevel {
    public abstract void load();
    public abstract void update(float delta, Player player, List<Bullet> bullets);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
    public abstract List<Platform> getPlatforms();
}
