package com.boss.deadcells.states;

import com.boss.deadcells.Player;

public interface PlayerState {
    void handleInput(Player player, float delta);
    void update(Player player, float delta);
    String getName(); // Для отладки
}
