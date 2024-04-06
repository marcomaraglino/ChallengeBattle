package it.marcomaraglino.challengebattle.arena;

import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.configfile.GameConfigStructure;
import it.marcomaraglino.challengebattle.game.Countdown;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


public class Arena<T> {

    private final int id;
    private ArrayList<UUID> players;
    private GameState state;
    private final Location location;

    private final Countdown countdown;
    private final Game game;

    private final int requiredPlayers;
    private final String prefix;
    private boolean privateArena;
    private GameConfigStructure<T> structure;

    public Arena(GameType gameType, GameConfigStructure<T> structure) {
        Configfile configfile = new Configfile();

        privateArena = true;
        this.structure = structure;

        int arena_id = 1;

        ArrayList<Arena> arenaList = Manager.getInstance().getArenaList();
        for (Arena arena : arenaList) {
            if (arena.getId() != arena_id) {
                break;
            }
            arena_id++;
        }


        WorldCreator wc = new WorldCreator("arena-" + arena_id);
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.NORMAL);

        wc.createWorld();

        World world = Bukkit.getWorld("arena-" + arena_id);
        world.setAutoSave(false);

        this.id = arena_id;
        this.players = new ArrayList<>();
        // Initialise the arena's game state as waiting - what it will be when
        // the arena is created.
        this.state = GameState.WAITING;
        Random random = new Random();
        float x = random.nextFloat(10000);
        float z = random.nextFloat(10000);
        this.location = new Location(world, x, world.getHighestBlockAt((int) x, (int) z).getY(),
                z);

        this.countdown = new Countdown(this);
        this.game = new Game(this, gameType, structure.getItem(), structure.getName());

        // The players required for the countdown to start.
        this.requiredPlayers = configfile.getRequiredplayers();
        this.prefix = configfile.getArenaprefix().replaceAll("%s", String.valueOf(id));

        // Add the arena to the arena list in the manager class.
        if (Manager.getInstance().getArenaList().size() >= configfile.getMaxnumberarenas()) {
            return;
        }
    }

    public void addArena() {
        Manager.getInstance().addArena(this);
    }

    public void reset() {
        // Clear all variables that should not continue their values into the
        // next round played in this arena.

        this.players.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            player.teleport(Manager.SPAWN_POINT);
        });
        this.players.clear();

        this.state = GameState.WAITING;

        if (game.getBossBar() != null) {
            game.getBossBar().removeAll();
        }

        Manager.getInstance().removeArena(this);

        World delete = Bukkit.getWorld("arena-" + this.id);
        Bukkit.unloadWorld(delete, false);

        World world = Bukkit.createWorld(new WorldCreator("arena-" + this.id));
        world.setAutoSave(false);
    }
    public void teleportPlayersToSpawn() {
        for (int i = 0; i < players.size(); i++) {
            Bukkit.getPlayer(players.get(i)).teleport(Manager.SPAWN_POINT);
        }
    }

    public void broadcast(String message) {

        for (int i = 0; i < players.size(); i++) {
            Title title = Title.title(Component.text(prefix), Component.text(message));
            Bukkit.getPlayer(players.get(i)).showTitle(title);
        }

    }

    public void broadcast(String message, Player player) {

        Title title = Title.title(Component.text(prefix), Component.text(message));
        player.showTitle(title);


    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);

        // Check whether to start the countdown. Make sure that the countdown
        // isn't already running.
        if (!countdown.isRunning() && players.size() >= requiredPlayers) {
            countdown.start(60);
        }
    }

    public void removePlayer(UUID uuid) {
        players.remove(uuid);
        clearInventory(Bukkit.getPlayer(uuid));
        clearPotionEffects(Bukkit.getPlayer(uuid));

        if ((state == GameState.WAITING || state == GameState.COUNTDOWN) && players.isEmpty()) {
            reset();
        }


        // Win detection. Check if the game has actually started first, though,
        // because if we don't do this, errors could occur.
        if (state == GameState.STARTED && players.size() == 1) {
            game.cancel();

            // Get the last player.
            Player winner = Bukkit.getPlayer(players.get(0));

            winner.sendMessage("You won the game");

            removePlayer(winner.getUniqueId());

            // Reset the arena.
            reset();
        }
    }

    public boolean contains(UUID uuid) {
        return players.contains(uuid);
    }

    // Getters and setters for private variables.
    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<UUID> players) {
        this.players = players;
    }


    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Location getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public Countdown getCountdown() {
        return countdown;
    }

    public Game getGame() {
        return game;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isPrivateArena() {
        return privateArena;
    }

    public void setPrivateArena(boolean privateArena) {
        this.privateArena = privateArena;
    }
    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getEquipment().clear();
    }

    public static void clearPotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public GameConfigStructure<T> getStructure() {
        return structure;
    }
}