package com.boss.deadcells.states;

import com.boss.deadcells.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class RunState implements PlayerState {
    public void handleInput(Player player, float delta) {
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setState(new IdleState());
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.setState(new JumpState());
        }
    }

    public void update(Player player, float delta) {
        // Можно добавить лог движения здесь
    }

    public String getName() {
        return "Running";
    }
}
