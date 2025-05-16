package com.boss.deadcells.observer;

public interface GameObserver {
    void onPlayerDamaged(int newHealth);
    void onItemPicked(String itemName);
}
