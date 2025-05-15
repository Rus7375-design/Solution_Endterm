package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boss.deadcells.Player;

public class WeaponItem extends Item {

    public WeaponItem() {
        this.texture = new Texture("sword.png");
    }

    @Override
    public void use(Player player) {
        player.upgradeWeapon(); 
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y, 24, 24);
    }
}
