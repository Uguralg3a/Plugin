package com.angel.plugin.commands;

import cloud.commandframework.context.CommandContext;
import com.angel.plugin.Plugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class TestCommand extends CommandHandler {
    protected TestCommand(CommandManager commandManager) {
        super(commandManager);
    }

    @Override
    public void register() {
        commandManager.command(
                commandManager.commandBuilder("test").handler(this::test)
        );
    }

    private void test(@NonNull CommandContext<CommandSender> context) {
        if(context.getSender() instanceof Player player){
            if (context.hasPermission("plugin.test")) {
                Plugin.plugin.getMessageUtil().sendMessage(player, "test_message");
            }
        }
    }
}
