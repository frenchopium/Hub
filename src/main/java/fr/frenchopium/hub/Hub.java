package fr.frenchopium.hub;

import fr.frenchopium.hub.commands.FlyCmd;
import fr.frenchopium.hub.commands.HubCmds;
import fr.frenchopium.hub.commands.Joueurs;
import fr.frenchopium.hub.commands.Spec;
import fr.frenchopium.hub.events.EventManager;
import fr.frenchopium.hub.listeners.HubListeners;
import fr.frenchopium.hub.listeners.JoinEvent;
import fr.frenchopium.hub.listeners.MenuPrincipalGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public final class Hub extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getLogger().log(Level.INFO, "Le plugin Hub a été chargé avec succès");

        getCommand("fly").setExecutor(new FlyCmd());
        getCommand("spec").setExecutor(new Spec());
        getCommand("hub").setExecutor(new HubCmds());
        getCommand("joueurs").setExecutor(new Joueurs());
        getServer().getPluginManager().registerEvents(new HubListeners(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        new EventManager(this).registerEvent();
        getServer().getPluginManager().registerEvents(new MenuPrincipalGUI(this), this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

         Bukkit.getLogger().log(Level.INFO, "Le plugin Hub a été déchargé avec succès");
         
    }

    // Bungeecord
    public void sendToServer(Player p, String serv){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try{
            out.writeUTF("Connect");
            out.writeUTF(serv);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        Bukkit.getPlayer(p.getName()).sendPluginMessage(this, "BungeeCord", b.toByteArray());

    }
}


