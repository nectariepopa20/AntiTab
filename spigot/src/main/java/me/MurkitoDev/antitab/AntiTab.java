package me.MurkitoDev.antitab;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiTab extends JavaPlugin {

    private ProtocolManager protocolManager;

    @Override
    public void onLoad() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();
        this.protocolManager.addPacketListener((PacketListener) new PacketAdapter((Plugin) this, ListenerPriority.NORMAL, PacketType.Play.Server.TAB_COMPLETE) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (!event.getPlayer().isOp()) {
                    event.setCancelled(true);
                }
            }
        });
    }
}
