package com.boss.deadcells;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeadCellsGame extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
