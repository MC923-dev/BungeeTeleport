package bungeeteleport.handler;

import bungeeteleport.BungeeTeleport;
import bungeeteleport.command.CMDManager;
import bungeeteleport.command.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Optional;

public class InternalListener implements Listener {

    private CMDManager manager;

    public InternalListener(BungeeTeleport plugin) {
        manager = plugin.getManager();
    }


    @EventHandler
    public void onChat(ChatEvent e) {
        if (!e.isCommand() || !(e.getSender() instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        String message = e.getMessage().replaceAll("/", "");
        Optional<Command> optionalCmd = manager.getCommand(message);
        if (!optionalCmd.isPresent()) {
            return;
        }
        Command command = optionalCmd.get();
        if (command.isTeleportToMostFree()) {
            ServerInfo server = command.getMostFreeServer();
            p.connect(server);
            e.setCancelled(true);
            return;
        }
        String destination = command.getDestination();
        ServerInfo server = ProxyServer.getInstance().getServerInfo(destination);
        if (server == null) {
            ProxyServer.getInstance().getLogger().severe(ChatColor.RED + "Error! Defined server doesn't exist: " + destination);
            ProxyServer.getInstance().getLogger().severe(ChatColor.RED + "Check your config.");
            return;
        }
        p.connect(server);
        e.setCancelled(true);
    }
}
