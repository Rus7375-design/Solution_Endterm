package com.boss.deadcells.items;

import com.boss.deadcells.Player;

public class Sword extends Weapon {

    public Sword() {
        super("sword.png", 10); 
    }

    @Override
    public void use(Player player) {
        System.out.println("🗡 Меч активирован. Урон: " + damage);
        
    }
}
