package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boss.deadcells.Player;

public class HealthPotion extends Item {

    public HealthPotion() {
        this.name = "HealthPotion";
        this.texture = new Texture("health_potion.png"); // убедись, что этот файл есть в assets
    }

    @Override
    public void use(Player player) {
        player.heal(30);  // метод heal должен быть в Player
        System.out.println("🧪 Зелье выпито! +30 HP");
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y, 32, 32);
    }
}
