package com.boss.deadcells.commands;

public class StateChangeCommand implements GameEventCommand {
    private final String stateName;

    public StateChangeCommand(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public void execute() {
        System.out.println("Player state changed to: " + stateName);
    }
}
