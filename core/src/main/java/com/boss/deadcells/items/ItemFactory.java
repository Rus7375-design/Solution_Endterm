package com.boss.deadcells.items;

public class ItemFactory {
    public static Item create(String type) {
        switch (type) {
            case "sword": return new Sword();
            case "bow": return new Bow();
            case "potion": return new HealthPotion();
            default: throw new IllegalArgumentException("Unknown item: " + type);
        }
    }
}
