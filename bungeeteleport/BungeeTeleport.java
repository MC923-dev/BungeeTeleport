package bungeeteleport;

import bungeeteleport.command.CMDManager;
import bungeeteleport.data.Config;
import bungeeteleport.handler.InternalListener;
import bungeeteleport.manager.CMDManagerImpl;
import bungeeteleport.utils.JSONDatabase;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeTeleport extends Plugin {

    private Config cfg;
    private CMDManager manager;

    public void load() {
        cfg = JSONDatabase.load(this, "config.json", Config.class);
    }

    @Override
    public void onEnable() {
        load();
        manager = new CMDManagerImpl(this);
        getProxy().getPluginManager().registerListener(this, new InternalListener(this));
    }

    public Config getCfg() {
        return cfg;
    }

    public CMDManager getManager() {
        return manager;
    }
}
