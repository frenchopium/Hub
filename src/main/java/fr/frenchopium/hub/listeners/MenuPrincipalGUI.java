package fr.frenchopium.hub.listeners;

import fr.frenchopium.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuPrincipalGUI implements Listener {

    Hub main;

    public MenuPrincipalGUI(Hub hub) {
        this.main = hub;
    }

    private ItemStack getItem(String name, Material material, byte b, String[] lore){
        ItemStack it = new ItemStack(material, 1);
        ItemMeta m = it.getItemMeta();
        m.setDisplayName(name);
        m.addItemFlags(new ItemFlag[]{
                ItemFlag.HIDE_ENCHANTS
        });
        m.setLore(Arrays.asList(lore));
        it.setItemMeta(m);
        return it;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
            ItemStack itm = e.getItem();
            if(itm.getType() == Material.COMPASS){
                Inventory inv = Bukkit.createInventory(null, 54, "§eMenu Principal");

                inv.setItem(0, getItem("", Material.STAINED_GLASS_PANE,(byte) 4, new String[] {}));
                inv.setItem(1, getItem("", Material.STAINED_GLASS_PANE, (byte) 4, new String[] {}));
                inv.setItem(19, getItem("§cBedWars §fSaison 1", Material.BED, (byte) 0, new String[] {"§8Mini-jeu - 1.8.x - 1.20.x", "  ", "§7Détruisez le lit des équipes ennemies tout", "§7en protégeant le vôtre pour remporter la partie! ",  "    ", "§c ✖ Bedrock", "§6» §6Cliquez §6pour rejoindre"}));
                inv.setItem(20, getItem("§6LoupGarou §fSaison 1", Material.GOLDEN_APPLE, (byte) 0, new String[] {"§8Mini-jeu - 1.8.x - 1.21.x", "  ", "§7Le mode Loup-Garou est un mode inspiré", "§7du jeu de société Les Loups-Garous de Thiercelieux",  "    ", "§a ✔ Bedrock", "§6» §6Cliquez §6pour rejoindre"}));
                e.getPlayer().openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if(current == null) return;

        if(inv.getName().equalsIgnoreCase("§eMenu Principal")){
            e.setCancelled(true);

            if(current.getType() == Material.BED){
                p.closeInventory();
                this.main.sendToServer(p, "bedwars");
            }
        }
    }


}
