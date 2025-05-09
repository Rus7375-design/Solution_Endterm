package com.boss.deadcells;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;


import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Texture background;
    private List<Platform> platforms;
    private List<Enemy> enemies;
    private List<Crate> crates;


    @Override
    public void show() {
        batch = new SpriteBatch();
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        crates = new ArrayList<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        player = new Player(100, 200);
        background = new Texture("background_forest.png");

        // Платформы (опущены ниже для совпадения с фоном)
        platforms.add(new Platform(0, -100, 500, 64, "ground_tile.png"));
        platforms.add(new Platform(600, -40, 300, 64, "ground_tile.png"));
        platforms.add(new Platform(1000, -40, 400, 64, "ground_tile.png"));
        platforms.add(new Platform(1400, -40, 400, 64, "ground_tile.png"));
        platforms.add(new Platform(2200, -40, 500, 64, "ground_tile.png"));

        // Воздушные платформы
        platforms.add(new Platform(700, 150, 100, 20, "platform.png"));
        platforms.add(new Platform(1200, 250, 150, 20, "platform.png"));
        platforms.add(new Platform(1700, 100, 100, 20, "platform.png"));

        // Враги
        enemies.add(new Enemy(400, 32));
        enemies.add(new Enemy(800, 32));
        enemies.add(new Enemy(1500, 32));

        // Ящики
        crates.add(new Crate(700, 32));
        crates.add(new Crate(1000, 32));
        crates.add(new Crate(1600, 32));
        crates.add(new Crate(2100, 32));
    }

    @Override
    public void render(float delta) {
        List<Platform> allPlatforms = new ArrayList<>(platforms);
        allPlatforms.addAll(crates);

        player.update(delta, allPlatforms);

        for (Enemy e : enemies) {
            e.update(delta, player, allPlatforms);
        }

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background, camera.position.x - 400, camera.position.y - 240, 800, 480);

        for (Platform p : platforms) p.render(batch);
        for (Crate crate : crates) crate.render(batch);

        player.render(batch);
        for (Enemy e : enemies) e.render(batch);

        if (player.isAttacking()) {
            Rectangle attackHitbox = new Rectangle(player.getX(), player.getY(), 40, 64);
            for (Enemy e : enemies) {
                Rectangle enemyBox = new Rectangle(e.getX(), e.getY(), 32, 64);
                if (attackHitbox.overlaps(enemyBox)) {
                    e.takeDamage(10);
                }
            }
        }

        batch.end();

    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
