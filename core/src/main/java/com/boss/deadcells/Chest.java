package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.boss.deadcells.items.Item;

public class Chest {
    private float x, y;
    private Texture textureClosed = new Texture("chest_closed.png");
    private Texture textureOpened = new Texture("chest_opened.png");
    private boolean opened = false;
    private Item item;

    public Chest(float x, float y, Item item) {
        this.x = x;
        this.y = y;
        this.item = item;
    }

    public void render(SpriteBatch batch) {
        batch.draw(opened ? textureOpened : textureClosed, x, y, 32, 32);
    }

    public void update(Player player) {
        if (!opened && new Rectangle(x, y, 32, 32).overlaps(new Rectangle(player.getX() - 16, player.getY() - 24, 32, 48))) {
            opened = true;
            item.use(player);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public boolean isOpened() {
        return opened;
    }
}
