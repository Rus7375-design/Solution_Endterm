package com.boss.deadcells.states;

import com.boss.deadcells.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class IdleState implements PlayerState {
    public void handleInput(Player player, float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setState(new RunState());
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.setState(new JumpState());
        }
    }

    public void update(Player player, float delta) {
        // Ничего не делает
    }

    public String getName() {
        return "Idle";
    }
}
