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
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class MenuPrincipalGUI implements Listener {

    Hub main;

    public MenuPrincipalGUI(Hub hub) {
        this.main = hub;
    }

    private ItemStack getItem(String name, Material material, String[] lore){
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

    /**
     * Crée une tête personnalisée avec une texture Base64
     * @param name Nom à afficher pour la tête
     * @param encodedTexture Valeur Base64 de la texture
     * @param lore Description de l'item
     * @return ItemStack de la tête personnalisée
     */
    private ItemStack getCustomHead(String name, String encodedTexture, String[] lore) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3); // SKULL_ITEM avec data 3 pour les versions avant 1.13
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        // Créer un GameProfile pour la texture
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", encodedTexture));

        // Appliquer le GameProfile via réflexion
        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Définir le nom et la lore
        headMeta.setDisplayName(name);
        if (lore != null) {
            headMeta.setLore(Arrays.asList(lore));
        }
        headMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        head.setItemMeta(headMeta);
        return head;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_AIR){
            ItemStack itm = e.getItem();
            if(itm != null && itm.getType() == Material.COMPASS){
                Inventory PagePrincipal = Bukkit.createInventory(null, 54, "§7Menu Principal");

                // Tetes custom
                String crownTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVhYWQxNDU3YTMyMTYzMGQxMzA3NDZhODYwZTQwMjdiNDA4MGY0YTUzOTIzZGI4ZTU4MzdiZTM2OGVkNmYzMCJ9fX0=";
                String islandTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDVlZDJlZjMyN2RkYTZmMmRlYmU3YzI0MWNmMjFjNWVmMGI3MzdiZjYxMTc4N2ZlNGJmNTM5YzhhNTcyMDM2In19fQ=";
                String werewolfTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk4MWI5NWVhN2MxYjkzNWQ2Yzg3NDYyMGVlZGNlMWYzNGE4NzIzMzIyNDI1OWU5M2NiOTU3ZWNmMTAzZmJlZSJ9fX0=";
                String statusTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc5YTk0MmRjZTFlMjM2ZmUxNjM2ODRjYjY5OGY5ZGJmYjgxYmFjOTU4ZDA5OWMyNzQxZDQyYTIyNzRjN2U5In19fQ==";
                String compatibiliteTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWE0NjY1MzY4NDFmYzQwOWNhOWM0MDc1NTRkM2JiMzViMTJhMzdlN2JlN2ZjYmNmMzEyMDlkN2Q4MjcxNTQ0ZSJ9fX0=";

                // Couleur de la vitre
                ItemStack yellowGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
                ItemMeta meta = yellowGlass.getItemMeta();
                meta.setDisplayName(" ");
                yellowGlass.setItemMeta(meta);

                // Mis en page des items
                PagePrincipal.setItem(0, yellowGlass);
                PagePrincipal.setItem(1, yellowGlass);
                PagePrincipal.setItem(7, yellowGlass);
                PagePrincipal.setItem(8, yellowGlass);
                PagePrincipal.setItem(2, getCustomHead("§e§lBoutique VIP", crownTexture, new String[]{"§7Accédez à la boutique pour", "§7acheter des avantages exclusifs!", "", "§6» §6Cliquez §6pour ouvrir"}));
                PagePrincipal.setItem(3, getCustomHead("§9§lCompatibilité", compatibiliteTexture, new String[] {"§7Accédez a la page de compatibilité pour voir", "§7sur quel console/platformes le serveur est jouable.", "", "§6» §6Cliquez §6pour ouvrir"}));
                PagePrincipal.setItem(4, getCustomHead("§a§lStatus", statusTexture, new String[] {"§7Accédez a la page de status pour savoir", "§7quels serveurs sont actuellement en ligne.", "", "§6» §6Cliquez §6pour ouvrir"}));
                PagePrincipal.setItem(19, getItem("§cBedWars §fSaison 1", Material.BED, new String[] {"§8Mini-jeu - 1.8.x - 1.20.x", "  ", "§7Détruisez le lit des équipes ennemies tout", "§7en protégeant le vôtre pour remporter la partie! ",  "    ", "§c ✖ Bedrock", "§6» §6Cliquez §6pour rejoindre"}));
                PagePrincipal.setItem(20, getCustomHead("§6LoupGarou §fSaison 1", werewolfTexture, new String[] {"§8Mini-jeu - 1.8.x - 1.21.x", "  ", "§7Le mode Loup-Garou est un mode inspiré", "§7du jeu de société Les Loups-Garous de Thiercelieux",  "    ", "§a ✔ Bedrock", "§6» §6Cliquez §6pour rejoindre"}));
                PagePrincipal.setItem(21, getCustomHead("§aSkyBlock §bSAISONS UPDATE", islandTexture, new String[] {"§8Mini-jeu - +1.21.4", "  ", "§7Commencez votre aventure sur une petite ile", "§7tout seul ou étant accompagnée.", "§7Améliorez vos ressources et agrandissez votre ile avec cela!",  "    ", "§a ✔ Bedrock", "§6» §6Cliquez §6pour rejoindre"}));
                PagePrincipal.setItem(45, yellowGlass);
                PagePrincipal.setItem(46, yellowGlass);
                PagePrincipal.setItem(52, yellowGlass);
                PagePrincipal.setItem(53, yellowGlass);
                e.getPlayer().openInventory(PagePrincipal);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if(current == null) return;

        if(inv.getName().equalsIgnoreCase("§7Menu Principal")){
            e.setCancelled(true);

            if(current.getType() == Material.BED){
                p.closeInventory();
                this.main.sendToServer(p, "bedwars");
            }

            // Gestion des clics sur les têtes personnalisées
            if(current.getType() == Material.SKULL_ITEM) {
                String itemName = current.getItemMeta().getDisplayName();

                if(itemName.equals("§9§lCompatibilité")) {
                    p.closeInventory();
                    Inventory Compatibilite = Bukkit.createInventory(null, 27, "§7Compatibilité");


                    // Couleur de la vitre
                    ItemStack yellowGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
                    ItemMeta meta = yellowGlass.getItemMeta();
                    meta.setDisplayName(" ");
                    yellowGlass.setItemMeta(meta);

                    // Tetes custom
                    String ps5Texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE0ZGI3ZGRhZTZmMTMwZjc3NzQzZjlkNzYxYTk0NzYyMTEwNTZkMWIwZTQ3MjdjMDZhMGJlYWU1MjAxNGEyZSJ9fX0=";
                    String ps4Texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkZWMwM2I5N2VhM2Q4MDNmMzI4YjkwN2UxMjcxZmExMDc3ZWExMTZmODU2ZjNjMzM4NTlkNGM3OTdiODJkYSJ9fX0=";
                    String xboxTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk0MjAzMzk2YWIzYzAxMmNjZWQ0ZGI4YmE2MWQ2ZDlkM2E1NGYyMWUyNTI3M2NmNGZlODQyYjcwYjZmOTQzMCJ9fX0=";
                    String switchTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NmZjExYjRhN2M2YjRkNzVmYjY1ZjViNTNiMmJiNGNhNjJiOTg5OGMzMTFjZWQ3MThjZjg4NDQzZTU5YTM4OSJ9fX0=";
                    String xboxOneTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmI4NDM2NzM1ZDZiNmI2MzcyNmMzMjU1Y2E0NTVkZjRmNmMzYzc5ODk5ZWJmNzcxOGI4MDRhMWQzN2QwZTc5NiJ9fX0=";
                    String telephoneTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTIwNjVkMTUyYTgwYjE1MDZiZWQ5NjAxNDhhOWIzYmM3MGExMzVjZTU0NGNmOGY5OGNhYmNiMzUxZTNhZWFiOSJ9fX0=";
                    String pcTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U5OGI1NTY3MWQ0ZWYyZjY0MjU5ZWZlNmFhNjcyOWZlNjVjNzdkYTE5NmMyZDQ1Njg2NjM3NmVjY2NhNjYwYiJ9fX0=";

                    Compatibilite.setItem(10, getCustomHead("§7§lPC", pcTexture, new String[] {"§f» §7Un accès a tous les mini-jeux du serveur", "§f» §7Queue prioritaire", "§f» §7Serveur accesible dans toutes les versions du jeu"}));
                    Compatibilite.setItem(11, getCustomHead("§9§lPS5", ps5Texture, new String[] {"§f» §7Un accès restreint au mini-jeux du serveur", "§f» §7Queue plus lente", "§f» §7Serveur accesible en dernière version du jeu"}));
                    Compatibilite.setItem(12, getCustomHead("§9§lPS4", ps4Texture, new String[] {"§f» §7Un accès restreint au mini-jeux du serveur", "§f» §7Queue plus lente", "§f» §7Serveur accesible en dernière version du jeu"}));
                    Compatibilite.setItem(13, getCustomHead("§2§lXbox Series X & S", xboxTexture, new String[] {"§f» §7Un accès restreint au mini-jeux du serveur", "§f» §7Queue plus lente", "§f» §7Serveur accesible en dernière version du jeu"}));
                    Compatibilite.setItem(14, getCustomHead("§2§lXbox One", xboxOneTexture, new String[] {"§f» §7Un accès restreint au mini-jeux du serveur", "§f» §7Queue plus lente", "§f» §7Serveur accesible en dernière version du jeu"}));
                    Compatibilite.setItem(15, getCustomHead("§c§lNintendo Switch", switchTexture, new String[] {"§f» §7Un accès restreint au mini-jeux du serveur", "§f» §7Queue plus lente", "§f» §7Serveur accesible en dernière version du jeu"}));
                    Compatibilite.setItem(16, getCustomHead("§7§lTéléphone", telephoneTexture, new String[] {"§f» §7Un accès restreint au mini-jeux du serveur", "§f» §7Queue plus lente", "§f» §7Serveur accesible en dernière version du jeu"}));
                    p.openInventory(Compatibilite);
                }
            }
        }
    }
}