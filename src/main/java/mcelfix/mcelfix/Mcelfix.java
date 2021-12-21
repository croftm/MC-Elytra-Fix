package mcelfix.mcelfix;

import org.bukkit.plugin.java.JavaPlugin;

public final class Mcelfix extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Max Croft Elytra fix is enabled");
        ELytraBalance elytraBalance = new ELytraBalance();
        getServer().getPluginManager().registerEvents(elytraBalance, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
