package fr.frenchopium.hub.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyProtector implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!e.getPlayer().hasPermission("op")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(!e.getPlayer().hasPermission("op")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInterract(PlayerInteractEvent e){
        if(!e.getPlayer().hasPermission("op") &&
                e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK)
                e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!e.getPlayer().hasPermission("op")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDammage(EntityDamageEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }
}
