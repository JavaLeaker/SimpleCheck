package com.pashmash.simplecheck;

import com.pashmash.simplecheck.cmd.CheckCommand;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleCheck extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        System.out.println(ChatColor.GREEN + "SimpleCheck has been enabled!");
        getCommand("check").setExecutor(new CheckCommand(this));
        getCommand("check").setTabCompleter(new CheckCommand(this));

    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "SimpleCheck has been disabled!");
    }
}
