package it.marcomaraglino.challengebattle;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import it.marcomaraglino.challengebattle.commands.CommandArena;
import it.marcomaraglino.challengebattle.listeners.*;
import it.marcomaraglino.challengebattle.manager.Manager;
import it.marcomaraglino.challengebattle.placeholders.PlayerExpansion;
import it.marcomaraglino.challengebattle.playerprofile.PlayerProfile;
import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ChallengeBattle extends JavaPlugin {
    private static ChallengeBattle instance;
    private final File file = new File(getDataFolder(), "data.json");;
    FileConfiguration config = getConfig();
    public static ChallengeBattle getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        config.options().copyDefaults(true);
        saveConfig();


        new InventoryAPI(this).init();
        new PlayerExpansion().register();

        config.options().copyDefaults(true);
        saveConfig();

        registerCommands();
        registerListeners();

        if (file.exists()) {
            System.out.println("file exists!");
            try {
                Manager.getInstance().setPlayerProfiles(readJsonFile());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void registerCommands() {
        getCommand("arena").setExecutor(new CommandArena());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LeaveListener(), this);
        pm.registerEvents(new onPlayerJoin(), this);
        pm.registerEvents(new PickItemListener(), this);
        pm.registerEvents(new BiomeFindListener(), this);
        pm.registerEvents(new StructureFindListener(), this);
        pm.registerEvents(new MobKillListener(), this);
        pm.registerEvents(new DimensionListener(), this);
        pm.registerEvents(new noPvPListener(), this);
        pm.registerEvents(new BlockCommandListener(), this);
    }

    private HashMap<UUID, PlayerProfile> readJsonFile() throws FileNotFoundException {
        Gson gson = new Gson();
        Type listType = new TypeToken<HashMap<UUID, PlayerProfile>>() {}.getType();

        try {
            Reader reader = new FileReader(file);
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createJsonFile() throws IOException {

        getDataFolder().mkdir();

        if (!file.exists()) {
            file.createNewFile();
        }

        Gson gson = new Gson();
        Map<UUID, PlayerProfile> data = Manager.getInstance().getPlayerProfiles();

        Writer writer = new FileWriter(file, false);


        gson.toJson(data, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();

        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        try {
            createJsonFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
