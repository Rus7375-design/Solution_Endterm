package com.boss.deadcells;

import java.util.List;

public class ShamanEnemy extends Enemy {
    private float shootTimer = 0;

    public ShamanEnemy(float x, float y) {
        super(x, y, 50, "shaman.png");
    }

    @Override
    public void update(float delta, Player player, List<Platform> platforms) {
        shootTimer += delta;
        if (shootTimer >= 3f) {
            shootTimer = 0;
            // Тут можно реализовать создание пули, если надо
            System.out.println("🧙‍♂ Шаман стреляет!");
        }
    }
}
