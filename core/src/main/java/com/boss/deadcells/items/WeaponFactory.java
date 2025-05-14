package com.boss.deadcells.items;

public class WeaponFactory {
    public static Weapon create(String type) {
        if ("sword".equals(type)) {
            return new Sword();
        } else if ("bow".equals(type)) {
            return new Bow();
        } else {
            return null;
        }
    }
}
