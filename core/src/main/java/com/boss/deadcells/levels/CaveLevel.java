// 📄 CaveLevel.java (обновлённый и улучшенный)
package com.boss.deadcells.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.boss.deadcells.*;
import com.boss.deadcells.enemies.Skeleton;
import com.boss.deadcells.items.Bullet;
import com.boss.deadcells.items.Door;

import java.util.ArrayList;
import java.util.List;

public class CaveLevel implements Level {
    private Texture background;
    private Array<Platform> platforms;
    private Array<Crate> rocks;
    private Array<Enemy> enemies;
    private Boss boss;
    private boolean bossDefeated = false;
    private Door door;

    public CaveLevel() {
        background = new Texture("cave_background.png");

        platforms = new Array<>();
        rocks = new Array<>();
        enemies = new Array<>();

        for (int i = 0; i < 15; i++) {
            platforms.add(new Platform(i * 400, -40, 400, 64, "stone_tile.png"));
        }

        platforms.add(new Platform(600, 120, 100, 20, "platform_stone.png"));
        platforms.add(new Platform(1200, 180, 120, 20, "platform_stone.png"));
        platforms.add(new Platform(2000, 240, 120, 20, "platform_stone.png"));

        enemies.add(new Skeleton(500, 32));
        enemies.add(new Skeleton(1000, 32));

        rocks.add(new Crate(700, 22));
        rocks.add(new Crate(1500, 22));
        rocks.add(new Crate(2100, 22));

        boss = new Boss(2500, 32);
        door = new Door(2800, 22); // конец уровня через дверь
    }

    @Override
    public void update(float delta, Player player, List<Bullet> bullets) {
        List<Platform> combined = new ArrayList<>();
        for (Platform p : platforms) combined.add(p);
        for (Crate c : rocks) combined.add(c);

        player.update(delta, combined);

        for (Enemy e : enemies) e.update(delta, player, combined);
        if (!boss.isDead()) boss.update(delta, player, combined);

        for (Bullet b : bullets) {
            Rectangle bulletBounds = b.getBounds();
            for (Enemy e : enemies) {
                if (bulletBounds.overlaps(e.getBounds())) e.takeDamage(10);
            }
            if (!boss.isDead() && bulletBounds.overlaps(boss.getBounds())) {
                boss.takeDamage(10);
            }
        }

        Array<Enemy> aliveEnemies = new Array<>();
        for (Enemy e : enemies) if (!e.isDead()) aliveEnemies.add(e);
        enemies = aliveEnemies;

        bossDefeated = boss.isDead();
    }

    @Override
    public void render(SpriteBatch batch, Player player) {
        batch.draw(background, player.getX() - 400, player.getY() - 240, 800, 480);
        for (Platform p : platforms) p.render(batch);
        for (Crate c : rocks) c.render(batch);
        for (Enemy e : enemies) e.render(batch);
        if (!boss.isDead()) boss.render(batch);
        door.render(batch);
    }

    @Override
    public List<Platform> getPlatforms() {
        List<Platform> result = new ArrayList<>();
        for (Platform p : platforms) result.add(p);
        for (Crate c : rocks) result.add(c);
        return result;
    }

    @Override
    public boolean isFinished(Player player) {
        return door.getBounds().overlaps(player.getBounds());
    }


    @Override
    public boolean isCompleted() {
        return bossDefeated;
    }

    @Override
    public void dispose() {
        background.dispose();
        for (Platform p : platforms) p.dispose();
        for (Crate c : rocks) c.dispose();
        for (Enemy e : enemies) e.dispose();
        boss.dispose();
        door.dispose();
    }
}
