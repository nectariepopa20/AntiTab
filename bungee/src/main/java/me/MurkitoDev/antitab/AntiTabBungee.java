package me.MurkitoDev.antitab;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
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

    private boolean shouldBlock(ProxiedPlayer player) {
        return player != null && !player.hasPermission(BYPASS_PERMISSION);
    }

    /** Proxy-side tab complete (e.g. /server, /bungee, /&lt;tab&gt;, /co&lt;tab&gt;) */
    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if (shouldBlock(player)) {
                event.getSuggestions().clear();
                event.setCancelled(true);
            }
        }
    }

    /** Backend server tab complete response */
    @EventHandler
    public void onTabCompleteResponse(TabCompleteResponseEvent event) {
        if (event.getReceiver() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getReceiver();
            if (shouldBlock(player)) {
                event.getSuggestions().clear();
                event.setCancelled(true);
            }
        }
    }
}
