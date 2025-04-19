package fr.frenchopium.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Joueurs implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player player) {
            player.sendMessage("" + ChatColor.BOLD + ChatColor.GOLD + "[hub]" + ChatColor.YELLOW + ChatColor.BOLD + "Bukkit.getServer().getOnlinePlayers().size()" + ChatColor.WHITE + "Joueurs connectés sur le proxy");
        }
        return false;
    }
}
