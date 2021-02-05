package me.azlaaz.bazooka;

import org.bukkit.plugin.java.JavaPlugin;

public final class Bazooka extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("roketatar").setExecutor( new Commands());
        getServer().getPluginManager().registerEvents(new Events(),this);


    }
}
