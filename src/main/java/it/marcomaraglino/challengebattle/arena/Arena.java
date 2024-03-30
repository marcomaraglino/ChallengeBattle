package it.marcomaraglino.challengebattle.arena;

import it.marcomaraglino.challengebattle.game.Countdown;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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

    public Arena(GameType gameType, T itemFind) {
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
        this.location = new Location(world, 00.0, world.getHighestBlockAt(00, 00).getY(),
                00.0);

        this.countdown = new Countdown(this);
        this.game = new Game(this, gameType, itemFind);

        // The players required for the countdown to start.
        this.requiredPlayers = 1;
        this.prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "Arena " + id + ChatColor.GRAY + "] " + ChatColor.RESET;

        // Add the arena to the arena list in the manager class.
        Manager.getInstance().addArena(this);
    }

    public void reset() {
        // Clear all variables that should not continue their values into the
        // next round played in this arena.


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

    public void broadcast(String message) {

        Title title = Title.title(Component.text(prefix), Component.text(message));
        for (int i = 0; i < players.size(); i++) {
            Bukkit.getPlayer(players.get(i)).showTitle(title);
        }
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

        if (state == GameState.WAITING) {
            reset();
        }


        // Win detection. Check if the game has actually started first, though,
        // because if we don't do this, errors could occur.
        if (state == GameState.STARTED && players.size() == 1) {
            game.cancel();

            // Get the last player.
            Player winner = Bukkit.getPlayer(players.get(0));

            Bukkit.broadcastMessage(
                    ChatColor.GREEN + "" + ChatColor.BOLD + winner.getName() + " won arena " + id + "!");

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

}