package com.boss.deadcells.items;

import com.boss.deadcells.Player;

public class Bow extends Weapon {

    public Bow() {
        super("bow.png", 7);
        this.name = "Bow";
    }

    @Override
    public void use(Player player) {
        System.out.println("🏹 Лук активирован. Урон: " + damage);
        // Логика стрельбы обрабатывается в GameScreen
    }
}
