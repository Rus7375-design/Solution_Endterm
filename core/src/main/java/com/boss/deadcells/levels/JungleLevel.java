package com.boss.deadcells.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.boss.deadcells.*;
import com.boss.deadcells.items.Bullet;
import com.boss.deadcells.items.Chest;
import com.boss.deadcells.items.Door;
import com.boss.deadcells.items.ItemFactory;
import com.boss.deadcells.enemies.Skeleton;
import com.boss.deadcells.GoblinEnemy;
import com.boss.deadcells.ShamanEnemy;

import java.util.ArrayList;
import java.util.List;

public class JungleLevel implements Level {
    private Texture background;
    private Array<Platform> platforms;
    private Array<Crate> crates;
    private Array<Enemy> enemies;
    private Array<Chest> chests;
    private Door door;

    public JungleLevel() {
        background = new Texture("background_forest.png");

        platforms = new Array<>();
        crates = new Array<>();
        enemies = new Array<>();
        chests = new Array<>();
        door = new Door(2900, 22);

        for (int i = 0; i < 20; i++) {
            platforms.add(new Platform(i * 400, -40, 400, 64, "ground_tile.png"));
        }

        platforms.add(new Platform(700, 150, 100, 20, "platform.png"));
        platforms.add(new Platform(1300, 180, 120, 20, "platform.png"));
        platforms.add(new Platform(700, 150, 100, 20, "platform.png"));
        platforms.add(new Platform(1300, 180, 120, 20, "platform.png"));
        platforms.add(new Platform(2000, 220, 120, 20, "platform.png"));
        platforms.add(new Platform(2500, 260, 120, 20, "platform.png"));

        enemies.add(new GoblinEnemy(800, 32));
        enemies.add(new GoblinEnemy(1400, 32));
        enemies.add(new ShamanEnemy(2000, 32));
        enemies.add(new GoblinEnemy(2500, 32));
        enemies.add(new ShamanEnemy(2800, 32));

        crates.add(new Crate(1000, 22));
        crates.add(new Crate(1600, 22));
        crates.add(new Crate(2100, 22));
        crates.add(new Crate(2700, 22));

        chests.add(new Chest(800, 22, ItemFactory.create("potion")));
        chests.add(new Chest(1700, 22, ItemFactory.create("bow")));
        chests.add(new Chest(2600, 22, ItemFactory.create("sword")));
    }

    @Override
    public void update(float delta, Player player, List<Bullet> bullets) {
        List<Platform> allPlatforms = new ArrayList<>();
        for (Platform p : platforms) allPlatforms.add(p);
        for (Crate c : crates) allPlatforms.add(c);

        player.update(delta, allPlatforms);
        for (Enemy e : enemies) e.update(delta, player, allPlatforms);

        for (Bullet b : bullets) {
            Rectangle bulletBox = b.getBounds();
            for (Enemy e : enemies) {
                if (bulletBox.overlaps(e.getBounds())) e.takeDamage(10);
            }
        }

        Array<Enemy> aliveEnemies = new Array<>();
        for (Enemy e : enemies) {
            if (!e.isDead()) aliveEnemies.add(e);
        }
        enemies = aliveEnemies;

        for (Chest chest : chests) {
            if (!chest.isOpened() && chest.getBounds().overlaps(player.getBounds())) {
                chest.open(player);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch, Player player) {
        batch.draw(background, player.getX() - 400, player.getY() - 240, 800, 480);
        for (Platform p : platforms) p.render(batch);
        for (Crate c : crates) c.render(batch);
        for (Enemy e : enemies) e.render(batch);
        for (Chest chest : chests) chest.render(batch);
        door.render(batch);
    }

    @Override
    public List<Platform> getPlatforms() {
        List<Platform> all = new ArrayList<>();
        for (Platform p : platforms) all.add(p);
        for (Crate c : crates) all.add(c);
        return all;
    }

    @Override
    public boolean isFinished(Player player) {
        return door.getBounds().overlaps(player.getBounds());
    }


    @Override
    public boolean isCompleted() {
        return enemies.size == 0;
    }

    @Override
    public void dispose() {
        background.dispose();
        for (Platform p : platforms) p.dispose();
        for (Crate c : crates) c.dispose();
        for (Enemy e : enemies) e.dispose();
        for (Chest c : chests) c.dispose();
        door.dispose();
    }
}
