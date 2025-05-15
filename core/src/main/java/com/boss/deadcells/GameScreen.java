package com.boss.deadcells;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.boss.deadcells.items.*;
import com.boss.deadcells.levels.*;

import java.util.*;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture whitePixel;

    private Player player;
    private Level currentLevel;
    private List<Bullet> bullets;

    private boolean gameOver = false;
    private Level jungleLevel;
    private Level caveLevel;
    private boolean inCave = false;
    private boolean victory = false;


    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        font = new BitmapFont();
        jungleLevel = new JungleLevel();
        caveLevel = new CaveLevel();
        currentLevel = jungleLevel;

        whitePixel = new Texture("white_pixel.png");

        player = new Player(100, 200);
        currentLevel = new JungleLevel(); // стартовый уровень
        bullets = new ArrayList<>();
    }

    @Override
    public void render(float delta) {
        if (gameOver || player.getCurrentHealth() <= 0 || player.getY() < -150) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            GlyphLayout layout = new GlyphLayout(font, "GAME OVER");
            font.draw(batch, layout, 400 - layout.width / 2, 240);
            batch.end();
            return;
        }

        // Update
        currentLevel.update(delta, player, bullets);
        player.update(delta, currentLevel.getPlatforms());

        if (currentLevel.isFinished(player)) {
            currentLevel.dispose();
            if (currentLevel instanceof JungleLevel) {
                currentLevel = new CaveLevel();
            } else {
                // Победа
                gameOver = true;
                return;
            }
        }


        if (!inCave && jungleLevel.isCompleted()) {
            currentLevel.dispose();
            currentLevel = caveLevel;
            inCave = true;
        }
        if (inCave && caveLevel.isCompleted()) {
            victory = true;
        }
        if (victory) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            GlyphLayout layout = new GlyphLayout(font, "VICTORY!");
            font.draw(batch, layout, 400 - layout.width / 2, 240);
            batch.end();
            return;
        }


        // Стрельба
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            bullets.add(new Bullet(player.getX(), player.getY() + 20, player.isFacingRight()));
        }

        for (Bullet b : bullets) b.update(delta);

        // Переход на следующий уровень
        if (currentLevel.isFinished(player)) {
            currentLevel.dispose();
            // Пока оставим JungleLevel, можно заменить на CaveLevel позже
            currentLevel = new JungleLevel(); // 🔁 поменяй на новый уровень здесь
            player.setPosition(100, 200);
        }

        // Камера
        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        // Render
        Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        currentLevel.render(batch, player);
        for (Bullet b : bullets) b.render(batch);
        player.render(batch);

        // UI HP Bar
        float healthRatio = (float) player.getCurrentHealth() / player.getMaxHealth();
        batch.setColor(Color.RED);
        batch.draw(whitePixel, camera.position.x - 390, camera.position.y + 210, 100, 8);
        batch.setColor(Color.GREEN);
        batch.draw(whitePixel, camera.position.x - 390, camera.position.y + 210, 100 * healthRatio, 8);
        font.setColor(Color.WHITE);
        font.draw(batch, player.getCurrentHealth() + " / " + player.getMaxHealth() + " HP",
            camera.position.x - 390, camera.position.y + 230);
        batch.setColor(Color.WHITE);

        batch.end();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        whitePixel.dispose();
        currentLevel.dispose();
    }
}
