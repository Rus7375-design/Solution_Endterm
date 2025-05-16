package com.boss.deadcells.observer;

public class GameLogger implements GameObserver {
    public void onPlayerDamaged(int newHealth) {
        System.out.println("[LOG] Игрок получил урон. Новое здоровье: " + newHealth);
    }

    public void onItemPicked(String itemName) {
        System.out.println("[LOG] Игрок подобрал предмет: " + itemName);
    }
}
