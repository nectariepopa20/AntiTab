package me.MurkitoDev.antitab;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteResponseEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class AntiTabBungee extends Plugin implements Listener {

    public static final String BYPASS_PERMISSION = "antitab.bypass";

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onTabCompleteResponse(TabCompleteResponseEvent event) {
        if (event.getReceiver() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getReceiver();
            if (!player.hasPermission(BYPASS_PERMISSION)) {
                event.setCancelled(true);
            }
        }
    }
}
