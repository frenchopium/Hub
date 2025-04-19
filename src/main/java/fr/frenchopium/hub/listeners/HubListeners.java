package fr.frenchopium.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.getInventory().clear();

        ItemStack customcompass = new ItemStack(Material.COMPASS, 1);
        ItemMeta customM = customcompass.getItemMeta();
        customM.setDisplayName("§eMenu Principal §f» §7Clique-droit");
        customcompass.setItemMeta(customM);

        player.getInventory().setItem(0, customcompass);
        player.updateInventory();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if (it == null) return;

        if(it.getType() == Material.COMPASS && it.hasItemMeta() &&
                it.getItemMeta().getDisplayName().equalsIgnoreCase("§eMenu Principal §f» §7Clique-droit")){

            Inventory menuprincipal = Bukkit.createInventory(null, 54, "§7Menu Principal");

            player.openInventory(menuprincipal);

        }
    }
}
