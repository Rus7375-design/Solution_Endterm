package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boss.deadcells.Player;

public abstract class Weapon extends Item {
    protected int damage;

    public Weapon(String texturePath, int damage) {
        this.texture = new Texture(texturePath);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y, 32, 32);
    }

    // Атакующее оружие может переопределить этот метод
    @Override
    public void use(Player player) {
        System.out.println("⚔️ Использовано оружие, урон: " + damage);
    }
}
