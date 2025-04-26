package fr.frenchopium.hub.events;

import fr.frenchopium.hub.Hub;
import fr.frenchopium.hub.listeners.LobbyProtector;
import fr.frenchopium.hub.listeners.MenuPrincipalGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {
    public Plugin plugin;
    public PluginManager pm;

    private Hub hubInstance;

    public EventManager(Plugin plugin) {
        this.plugin = plugin;
        pm = Bukkit.getPluginManager();

        if (plugin instanceof Hub) {
            this.hubInstance = (Hub) plugin;
        }
    }

    public void registerEvent() {
        pm.registerEvents(new LobbyProtector(), plugin);
        pm.registerEvents(new MenuPrincipalGUI(hubInstance), plugin);
    }
}

