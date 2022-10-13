package com.angel.plugin;

import com.angel.plugin.commands.CommandManager;
import com.angel.plugin.util.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Plugin extends JavaPlugin {

    public static Plugin plugin;
    private MessageUtil messageUtil;

    @Override
    public void onLoad() {
        if(plugin == null){
            plugin = this;
        }

        plugin.saveDefaultConfig();

        this.messageUtil = new MessageUtil( new File(this.getDataFolder()+"/"+ plugin.getConfig().getString("messages")));

    }

    @Override
    public void onEnable() {

        try {
            new CommandManager();
            getLogger().info("Commands registered");
        } catch (Exception e) {
            getLogger().warning("Failed to initialize command manager: " + e.getMessage());
        }
    }

    @Override
    public void onDisable() {

    }

    public MessageUtil getMessageUtil() {
        return messageUtil;
    }
}