package it.marcomaraglino.challengebattle.game;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private int time;

    private final Arena arena;


    public Countdown(Arena arena) {

        this.arena = arena;

        this.time = 0;

    }

    public void start(int time) {

        arena.setState(GameState.COUNTDOWN);

        this.time = time;

        this.runTaskTimer(ChallengeBattle.getInstance(), 0L, 20L);

    }

    public boolean isRunning() {

        return arena.getState() == GameState.COUNTDOWN;

    }


    @Override
    public void run() {


        if (time == 0) {

            cancel();

            arena.getGame().start();

            return; // Get out of the run method.

        }


        if (time % 15 == 0 || time <= 10) {

// If the time is divisible by 15 then broadcast a countdown

// message.

            if (time != 1) {

                arena.broadcast(ChatColor.AQUA + "Game will start in " + time + " seconds.");

            } else {

                arena.broadcast(ChatColor.AQUA + "Game will start in " + time + " second.");

            }

        }


        if (arena.getPlayers().size() < arena.getRequiredPlayers()) {

            cancel();

            arena.setState(GameState.WAITING);

            arena.broadcast(ChatColor.RED + "There are too few players. Countdown stopped.");

            return; // Get out of the run method.

        }


        time--;

    }
}
