package fr.frenchopium.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player player) {
            player.sendMessage(ChatColor.GRAY + "[hub] Commandes disponibles sur le plugin");
            player.sendMessage("");
            player.sendMessage(ChatColor.GOLD + "/spec" + ChatColor.WHITE + " » Permet de passer du gamemode survival au gamemode creative");
            player.sendMessage(ChatColor.GOLD + "/fly" + ChatColor.WHITE + " » Permet de voler en gamemode survival");
            player.sendMessage(ChatColor.GOLD + "/boutique" + ChatColor.WHITE + " » Bientot disponible...");
            player.sendMessage(ChatColor.GOLD + "/joueurs" + ChatColor.WHITE + " » Permet de voir tous les joueurs connectés sur le Proxy");
            player.sendMessage("");
            player.sendMessage("" + ChatColor.BOLD + ChatColor.YELLOW + "frenchopium | 2025");
        }
            return false;
    }
}
