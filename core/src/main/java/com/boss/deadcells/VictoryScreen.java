package com.boss.deadcells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class VictoryScreen implements Screen {
    private final DeadCellsGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background;
    private Music music;
    private boolean musicPlayed = false;

    public VictoryScreen(DeadCellsGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        font = new BitmapFont();
        background = new Texture("menu_background.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("victory_music.mp3"));
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!musicPlayed) {
            music.play();
            musicPlayed = true;
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 800, 480);
        font.getData().setScale(1.5f);
        font.draw(batch, "VICTORY!", 330, 400);

        font.getData().setScale(1);
        font.draw(batch, "Congratulations, you defeated all enemies!", 180, 340);
        font.draw(batch, "Press R to Restart or M for Main Menu", 220, 300);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            music.stop();
            game.setScreen(new GameScreen(game));
            dispose();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            music.stop();
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        background.dispose();
        music.dispose();
    }
}
