package com.pashmash.simplecheck.cmd;

import com.pashmash.simplecheck.SimpleCheck;
import com.pashmash.simplecheck.utill.Colors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckCommand implements CommandExecutor, TabCompleter {
    private SimpleCheck main;
    public CheckCommand(SimpleCheck main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("simple.check")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    Location location = target.getLocation();
                    //message
                    sender.sendMessage(Colors.color("&#ff0000Checking &#15ff00" + target.getName()));
                    sender.sendMessage("");
                    //UUID
                    sender.sendMessage(Colors.color("&#15ff00" + target.getName()
                            + "&7 UUID is: "
                            + target.getUniqueId()));
                    //Gamemode
                    sender.sendMessage(Colors.color("&#15ff00" + target.getName()
                            + "&7 is in gamemode: "
                            + target.getGameMode()));
                    //Health
                    sender.sendMessage(Colors.color("&#15ff00" + target.getName()
                            + "&7 has "
                            + target.getHealth()
                            + " health"));
                    //Ip
                    if (main.getConfig().getBoolean("Log-ips")) {

                        sender.sendMessage(Colors.color("&#15ff00" + target.getName()
                                + "&7 Ip is: "
                                + target.getAddress().getAddress().getHostAddress()));
                    } else {
                        sender.sendMessage(Colors.color("&#ff0004Ip logging is disabled"));
                    }
                    //world
                    sender.sendMessage(Colors.color("&#15ff00" + target.getName()
                            + "&7 is in world: "
                            + location.getWorld().getName()));
                    //Location
                    sender.sendMessage(Colors.color("&#15ff00") + target.getName() +Colors.color("&7 is at X: " ) + location.getBlockX()
                            + " Y: " + location.getBlockY()
                            + " Z: " + location.getBlockZ());
                    //Ping
                    sender.sendMessage(Colors.color("&#15ff00" + target.getName()
                            + "s"
                            + "&7 ping is: "
                            + target.getPing()));
                } else {
                    sender.sendMessage(Colors.color("&#ff0004Player not found"));
                }
            } else {
                sender.sendMessage(Colors.color("&#ff0004Usage: /check <player>"));
            }
        } else {
            sender.sendMessage(Colors.color("&#ff0004You do not have permission to use this command"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
