package bungeeteleport.command;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.List;

public class Command {

    private String name;
    private String destination;
    private List<String> aliases;
    private boolean teleportToMostFree;
    private List<String> servers;

    public Command(String name, String destination, List<String> aliases, boolean teleportToMostFree, List<String> servers) {
        this.name = name;
        this.destination = destination;
        this.aliases = aliases;
        this.teleportToMostFree = teleportToMostFree;
        this.servers = servers;
    }

    public String getDestination() {
        return destination;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getName() {
        return name;
    }

    public boolean isCommand(String cmd) {
        return name.equalsIgnoreCase(cmd) || aliases.stream().anyMatch((command) -> command.equalsIgnoreCase(cmd));
    }

    public boolean isTeleportToMostFree() {
        return teleportToMostFree;
    }

    public List<String> getServers() {
        return servers;
    }

    public ServerInfo getMostFreeServer() {
        ServerInfo min = null;
        for (String s : getServers()) {
            ServerInfo inf = ProxyServer.getInstance().getServerInfo(s);
            if (inf == null) {
                continue;
            }
            if (min == null) {
                min = inf;
            }
            else {
                if (min.getPlayersCount() <= inf.getPlayersCount()) {
                    continue;
                }
                min = inf;
            }
        }
        return min;
    }
    private int getOnline(String server) {
        ServerInfo info = ProxyServer.getInstance().getServerInfo(server);
        if (info == null) {
            return 0;
        }
        return info.getPlayersCount();
    }
}