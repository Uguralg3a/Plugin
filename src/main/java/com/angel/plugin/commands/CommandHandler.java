package com.angel.plugin.commands;

public abstract class CommandHandler {
    protected final CommandManager commandManager;

    protected CommandHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public abstract void register();
}