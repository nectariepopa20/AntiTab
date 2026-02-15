package me.MurkitoDev.antitab;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class AntiTab extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    /** Blocks /<tab> (command list) for non-ops */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommandSend(PlayerCommandSendEvent event) {
        if (!event.getPlayer().isOp()) {
            event.getCommands().clear();
        }
    }

    /** Blocks /command <tab> and /co<tab> (argument completions) for non-ops */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onTabComplete(TabCompleteEvent event) {
        if (event.getSender() instanceof Player) {
            Player player = (Player) event.getSender();
            if (!player.isOp()) {
                event.setCompletions(Collections.emptyList());
            }
        }
    }
}
