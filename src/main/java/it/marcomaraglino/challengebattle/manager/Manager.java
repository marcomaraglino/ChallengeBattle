package it.marcomaraglino.challengebattle.manager;

import it.marcomaraglino.challengebattle.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class Manager {
    public final static Location SPAWN_POINT = new Location(Bukkit.getWorld("world"), 0.0, 10.0, 0.0);


    private static Manager manager;

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
    }
}
