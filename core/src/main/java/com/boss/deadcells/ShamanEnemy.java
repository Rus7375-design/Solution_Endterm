package com.boss.deadcells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShamanEnemy extends Enemy {
    private float shootTimer = 0;
    private float preferredDistance = 200;
    private List<ShamanProjectile> projectiles = new ArrayList<>();

    public ShamanEnemy(float x, float y) {
        super(x, y, 50, "shaman.png");
    }

    @Override
    public void update(float delta, Player player, List<Platform> platforms) {
        if (!active && isPlayerNear(player)) active = true;

        if (!active) return;

        shootTimer += delta;

        float distance = player.getX() - x;
        boolean tooClose = Math.abs(distance) < preferredDistance;

        if (tooClose) {
            x -= Math.signum(distance) * 50 * delta;
        }

        if (shootTimer >= 3f) {
            shootTimer = 0;
            projectiles.add(new ShamanProjectile(x + getWidth() / 2, y + getHeight() / 2, player.getX(), player.getY()));
        }

        Iterator<ShamanProjectile> iter = projectiles.iterator();
        while (iter.hasNext()) {
            ShamanProjectile p = iter.next();
            p.update(delta);
            if (p.getBounds().overlaps(player.getBounds())) {
                player.takeDamage(10);
                p.deactivate();
            }
            if (!p.isActive()) iter.remove();
        }

        updateMovement(delta, player, platforms);
    }

    public void renderProjectiles(SpriteBatch batch) {
        for (ShamanProjectile p : projectiles) p.render(batch);
    }

    private static class ShamanProjectile {
        private float x, y, dx, dy, speed = 180f;
        private boolean active = true;
        private Rectangle bounds = new Rectangle();
        private Texture texture = new Texture("fireball.png");

        public ShamanProjectile(float startX, float startY, float targetX, float targetY) {
            this.x = startX;
            this.y = startY;
            float distX = targetX - startX;
            float distY = targetY - startY;
            float length = (float) Math.sqrt(distX * distX + distY * distY);
            dx = distX / length;
            dy = distY / length;
            bounds.set(x, y, 12, 12);
        }

        public void update(float delta) {
            x += dx * speed * delta;
            y += dy * speed * delta;
            bounds.setPosition(x, y);
        }

        public void render(SpriteBatch batch) {
            if (active) batch.draw(texture, x, y, 12, 12);
        }

        public Rectangle getBounds() { return bounds; }
        public boolean isActive() { return active; }
        public void deactivate() { active = false; }
    }
}
