package com.angel.plugin.util;

import com.angel.plugin.Plugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MessageUtil {

    private final FileConfiguration externalConfig;
    private final FileConfiguration internalConfig;

    public MessageUtil(File messageConfig){
        externalConfig = YamlConfiguration.loadConfiguration(messageConfig);

        InputStream inputStream = Plugin.plugin.getResource(messageConfig.getName());

        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        internalConfig = YamlConfiguration.loadConfiguration(inputStreamReader);
    }

    public void sendMessage(CommandSender sender, String configString, TagResolver... tagResolvers){
        sender.sendMessage(getMessage(configString,tagResolvers));
    }

    public Component getMessage(String configString, TagResolver... tagResolvers){
        return MiniMessage.miniMessage().deserialize(getString(configString), tagResolvers);
    }


    public String getString(String configString){
        if(externalConfig.contains(configString)){
            return externalConfig.getString(configString);
        }else {
            return internalConfig.getString(configString);
        }
    }

    public List<String> getStrings(String configString){
        if(externalConfig.contains(configString)){
            return externalConfig.getStringList(configString);
        }else {
            return internalConfig.getStringList(configString);
        }
    }
}