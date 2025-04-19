package fr.frenchopium.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(sender instanceof Player player) {
            if(player.isFlying()) {
                player.sendMessage(ChatColor.RED + "Fly désormais désactivé");
                player.setFlying(false);
                if(player.getGameMode().equals(GameMode.SURVIVAL)||player.getGameMode().equals(GameMode.ADVENTURE)) {
                    player.setAllowFlight(false);
                }
            }
            else {
            player.sendMessage(ChatColor.GREEN + "Fly désormais activé");
            player.setAllowFlight(true);
            player.setFlying(true);
            }
        }


        return false;
    }
}
