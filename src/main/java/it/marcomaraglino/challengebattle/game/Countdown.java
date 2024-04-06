package it.marcomaraglino.challengebattle.game;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Countdown extends BukkitRunnable {
    Configfile configfile = new Configfile();

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
                for (int i = 0 ; i < arena.getPlayers().size() ; i++) {
                    Player player = Bukkit.getPlayer((UUID) arena.getPlayers().get(i));
                    player.playSound(player.getLocation(), configfile.getCountdown_sound(), 1f, 1f);
                }
                arena.broadcast(configfile.getGamewillstart().replaceAll("%s", String.valueOf(time)));

            } else {
                for (int i = 0 ; i < arena.getPlayers().size() ; i++) {
                    Player player = Bukkit.getPlayer((UUID) arena.getPlayers().get(i));
                    player.playSound(player.getLocation(), configfile.getCountdown_sound(), 1f, 1f);
                }
                arena.broadcast(configfile.getGamewillstart().replaceAll("%s", String.valueOf(time)));

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
