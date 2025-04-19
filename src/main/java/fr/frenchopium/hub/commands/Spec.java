package fr.frenchopium.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Spec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player player) {
            if (player.getGameMode() == GameMode.SPECTATOR) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.GRAY + "Passage du gamemode Spectator au gamemode Survival");
            }

            if (player.getGameMode() == GameMode.SURVIVAL) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatColor.GRAY + "Passage du gamemode Survival au gamemode Spectator");
            }

            if (player.getGameMode() == GameMode.CREATIVE) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatColor.GRAY + "Passage du gamemode Creative au gamemode Spectator");
            }

        }
        return false;
    }
}
