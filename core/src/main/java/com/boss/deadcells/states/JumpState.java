package com.boss.deadcells.states;

import com.boss.deadcells.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class JumpState implements PlayerState {
    private float jumpTime = 0.3f;

    public void handleInput(Player player, float delta) {
        jumpTime -= delta;
        if (jumpTime <= 0) {
            player.setState(new IdleState()); // можно вернуть в Idle или Run
        }
    }

    public void update(Player player, float delta) {
        // Логика прыжка (например, установка velocityY)
    }

    public String getName() {
        return "Jumping";
    }
}
