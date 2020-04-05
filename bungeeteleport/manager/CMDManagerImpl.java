package bungeeteleport.manager;


import bungeeteleport.BungeeTeleport;
import bungeeteleport.command.CMDManager;
import bungeeteleport.command.Command;
import bungeeteleport.data.Config;

import java.util.Optional;

public class CMDManagerImpl implements CMDManager {

    private Config cfg;

    public CMDManagerImpl(BungeeTeleport plugin) {
        cfg = plugin.getCfg();
    }


    @Override
    public Optional<Command> getCommand(String p0) {
        return cfg.getCommands().stream().filter(command -> command.isCommand(p0.toLowerCase())).findFirst();
    }
}
