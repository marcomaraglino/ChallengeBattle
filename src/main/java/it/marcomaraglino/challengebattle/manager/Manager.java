package it.marcomaraglino.challengebattle.manager;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.playerprofile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Manager {
    private final Configfile configfile = new Configfile();
    public static Location SPAWN_POINT;
    private static Manager manager;

    private HashMap<UUID, PlayerProfile> playerProfiles = new HashMap<>();

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    public Arena getArena(int id) {
        for (final Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }
    public Arena getArena(UUID uuid) {
        for (final Arena arena : arenas) {
            if (arena.contains(uuid)) {
                return arena;
            }
        }
        return null;
    }
    public boolean isArena(int id) {
        return getArena(id) != null;
    }

    private ArrayList<Arena> arenas;
    public void addArena(Arena arena) {
        arenas.add(arena);
    }
    public void removeArena(Arena arena) {
        arenas.remove(arena);
    }
    public ArrayList<Arena> getArenaList() {
        return arenas;
    }
    public String getArenas() {
        StringBuilder sb = new StringBuilder();
        for (Arena arena : arenas) {
            sb.append(arena.getId()).append(" ");
        }
        return sb.toString();
    }

    private Manager() {
        this.arenas = new ArrayList<>();
        SPAWN_POINT = new Location(Bukkit.getWorld(configfile.getSpawnworld()), configfile.getSpawnx(), configfile.getSpawny(), configfile.getSpawnz());
    }

    public HashMap<UUID, PlayerProfile> getPlayerProfiles() {
        return playerProfiles;
    }

    public void addPlayerProfile(UUID uuid, PlayerProfile playerProfile) {
        playerProfiles.put(uuid, playerProfile);
    }

    public void setPlayerProfiles(HashMap<UUID, PlayerProfile> playerProfiles) {
        this.playerProfiles = playerProfiles;
    }
}
