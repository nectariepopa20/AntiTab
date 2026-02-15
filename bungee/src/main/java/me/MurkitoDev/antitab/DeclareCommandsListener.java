package me.MurkitoDev.antitab;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDeclareCommands;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ProxyServer;
import com.github.retrooper.packetevents.protocol.chat.Node;

import java.util.Collections;
import java.util.List;

/**
 * Intercepts the DECLARE_COMMANDS packet (Brigadier command tree) sent to the client.
 * For players without antitab.bypass, replaces the tree with an empty root so the client
 * shows no tab completions for Bungee commands.
 */
public final class DeclareCommandsListener implements PacketListener {

    private final AntiTabBungee plugin;

    public DeclareCommandsListener(AntiTabBungee plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.DECLARE_COMMANDS) {
            return;
        }
        User user = event.getUser();
        if (user == null) {
            return;
        }
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(user.getUUID());
        if (player == null || !plugin.shouldBlock(player)) {
            return;
        }
        WrapperPlayServerDeclareCommands packet = new WrapperPlayServerDeclareCommands(event);
        Node emptyRoot = new Node(
                Node.TYPE_ROOT,
                Collections.emptyList(),
                -1,
                null,
                (Integer) null,
                null,
                null
        );
        packet.setNodes(List.of(emptyRoot));
        packet.setRootIndex(0);
        event.markForReEncode(true);
    }
}
