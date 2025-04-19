package fr.frenchopium.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class JoinEvent implements Listener {

    public Location lobby = new Location(Bukkit.getWorld("world"), 27, 84, -9);

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        setPlayer(p);
        setScoreboard(p);
        e.setJoinMessage(p.getName() + "§7 a rejoint le lobby");
        }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage(p.getName() + "§7 a quitté le lobby");
    }

    public void setPlayer (Player p){

        p.teleport(lobby);
        p.setGameMode(GameMode.ADVENTURE);
        p.setHealth(20);
        p.setWalkSpeed((float) 0.3);
        p.setFoodLevel(20);

        p.setExp(0F);
        p.setLevel(0);
    }

    public void setScoreboard(Player p) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("general", "dummy");

        obj.setDisplayName("§6LB'Network");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore("").setScore(13);
        obj.getScore(ChatColor.GRAY + "•Connectés: " + ChatColor.WHITE + "?").setScore(12);
        obj.getScore(ChatColor.GRAY + "Lobby: " + ChatColor.WHITE + "?").setScore(11);
        obj.getScore(" ").setScore(10); // Espaces différents pour éviter les doublons
        obj.getScore(ChatColor.WHITE + p.getName()).setScore(9);
        obj.getScore(ChatColor.GRAY + "• Grade: " + ChatColor.WHITE + "?").setScore(8);
        obj.getScore(ChatColor.GRAY + "• Diodes: " + ChatColor.WHITE + "?").setScore(7);
        obj.getScore(ChatColor.GRAY + "• Coins: " + ChatColor.WHITE + "?").setScore(6);
        obj.getScore("  ").setScore(5); // Espaces différents pour éviter les doublons
        obj.getScore(ChatColor.GRAY + "Saison 1 - Saison 2").setScore(4);
        obj.getScore(ChatColor.GOLD + "▌▌▌▌▌▌▌▌▌▌▌▌▌▌").setScore(3);
        obj.getScore(ChatColor.YELLOW + "Boutique & Wiki sur").setScore(2);
        obj.getScore(ChatColor.AQUA + "discord.lb-c.fr").setScore(1);

        p.setScoreboard(scoreboard);
    }
}
