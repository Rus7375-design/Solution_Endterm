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

public class MainMenuScreen implements Screen {
    private final DeadCellsGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background;
    private Music music;

    public MainMenuScreen(DeadCellsGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        font = new BitmapFont();
        background = new Texture("menu_background.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("menu_music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 800, 480);
        font.getData().setScale(1.5f);
        font.draw(batch, "DEADCELLS CLONE", 300, 440);

        font.getData().setScale(1f);
        font.draw(batch, "Controls:", 50, 380);
        font.draw(batch, "F - Melee Attack", 50, 350);
        font.draw(batch, "J - Ranged Attack", 50, 320);
        font.draw(batch, "WASD - Move", 50, 290);
        font.draw(batch, "SPACE - Jump", 50, 260);

        font.getData().setScale(1.2f);
        GlyphLayout layout = new GlyphLayout(font, "Press ENTER to Start");
        font.draw(batch, layout, (800 - layout.width) / 2, 180);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            music.stop();
            game.setScreen(new GameScreen(game));
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
