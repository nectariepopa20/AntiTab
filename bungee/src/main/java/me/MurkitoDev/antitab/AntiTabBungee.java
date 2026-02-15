package me.MurkitoDev.antitab;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
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
        try {
            PacketEvents.getAPI().getEventManager().registerListener(
                    new DeclareCommandsListener(this),
                    PacketListenerPriority.NORMAL
            );
        } catch (Throwable t) {
            getLogger().warning(
                    "PacketEvents not found - Bungee command tab completion will not be blocked. " +
                    "Install the PacketEvents plugin on the proxy for full tab blocking."
            );
        }
    }

    /** Visible for DeclareCommandsListener (PacketEvents). */
    public boolean shouldBlock(ProxiedPlayer player) {
        return player != null && !player.hasPermission(BYPASS_PERMISSION);
    }

    /**
     * Tab complete REQUEST from client (UpstreamBridge).
     * Sender = player (con), receiver = server (con.getServer()).
     * When cancelled, Bungee throws CancelSendSignal so the request is NOT forwarded to the backend.
     */
    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if (shouldBlock(player)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Tab complete RESPONSE from backend to client (DownstreamBridge).
     * Sender = server, receiver = player (con).
     * We clear suggestions and do NOT cancel so Bungee sends an empty packet to the client
     * (cancelling would send nothing; client may need a valid empty response).
     */
    @EventHandler
    public void onTabCompleteResponse(TabCompleteResponseEvent event) {
        if (event.getReceiver() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getReceiver();
            if (shouldBlock(player)) {
                event.getSuggestions().clear();
            }
        }
    }
}
