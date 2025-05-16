package com.boss.deadcells.levels;

public class LevelFactory {
    public static Level createLevel(String name) {
        switch (name.toLowerCase()) {
            case "jungle":
                return new JungleLevel();
            case "cave":
                return new CaveLevel();
            default:
                throw new IllegalArgumentException("Неизвестный уровень: " + name);
        }
    }
}
