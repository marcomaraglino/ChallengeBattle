package it.marcomaraglino.challengebattle;

import it.marcomaraglino.challengebattle.commands.CommandArena;
import it.marcomaraglino.challengebattle.listeners.BiomeFindListener;
import it.marcomaraglino.challengebattle.listeners.MobKillListener;
import it.marcomaraglino.challengebattle.listeners.PickItemListener;
import it.marcomaraglino.challengebattle.listeners.StructureFindListener;
import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public final class ChallengeBattle extends JavaPlugin {
    private static ChallengeBattle instance;

    public static ChallengeBattle getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        new InventoryAPI(this).init();

        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getCommand("arena").setExecutor(new CommandArena());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PickItemListener(), this);
        pm.registerEvents(new BiomeFindListener(), this);
        pm.registerEvents(new StructureFindListener(), this);
        pm.registerEvents(new MobKillListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
