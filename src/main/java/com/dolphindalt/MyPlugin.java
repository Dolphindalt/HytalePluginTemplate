package com.dolphindalt;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import jakarta.annotation.Nonnull;

public class MyPlugin extends JavaPlugin {

    private static MyPlugin instance;

    public MyPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    public static MyPlugin get() {
        return instance;
    }

    @Override
    protected void setup() {
        instance = this;
        getLogger().atInfo().log("Plugin setup complete!");
    }

    @Override
    protected void start() {
        getLogger().atInfo().log("Plugin started!");
    }

    @Override
    protected void shutdown() {
        getLogger().atInfo().log("Plugin shutting down!");
    }
}
