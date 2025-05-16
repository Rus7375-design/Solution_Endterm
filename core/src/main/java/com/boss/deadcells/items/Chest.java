package com.boss.deadcells.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.boss.deadcells.Player;

public class Chest {
    private float x, y;
    private Texture textureClosed;
    private Texture textureOpened;
    private boolean opened = false;
    private Item content;
    private Texture texture;


    public Chest(float x, float y, Item content) {
        this.x = x;
        this.y = y;
        this.content = content;
        this.textureClosed = new Texture("chest.png");
        this.textureOpened = new Texture("chest_open.png"); // положи файл в assets
    }

    public void render(SpriteBatch batch) {
        Texture textureToDraw = opened ? textureOpened : textureClosed;
        batch.draw(textureToDraw, x, y, 32, 32);
    }

    public void open(Player player) {
        if (!opened) {
            opened = true;
            if (content != null) content.use(player);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    public void dispose() {
        if (texture != null) texture.dispose();
    }


    public boolean isOpened() {
        return opened;
    }
}
