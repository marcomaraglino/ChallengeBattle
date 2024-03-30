package it.marcomaraglino.challengebattle.arena;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Game<T> extends BukkitRunnable {

    private final Arena<T> arena;
    private GameType gameType;
    private int time;
    private BossBar bossBar;
    private T itemFind;

    public int getTime() {
        return time;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public T getItemFind() {
        return itemFind;
    }

    public GameType getGameType() {
        return gameType;
    }

    public Game(Arena<T> arena, GameType gameType, T itemFind) {
        this.itemFind = itemFind;
        this.arena = arena;
        this.time = 0;
        this.gameType = gameType;
    }

    public void start() {
        arena.setState(GameState.STARTED);
        arena.broadcast(ChatColor.AQUA + "The game has started!");

        //TODO: Controllare se funziona senza il toString()
        switch (gameType) {
            case ITEMFOUND:
                bossBar = Bukkit.createBossBar("Trova oggetto: " + itemFind.toString(), BarColor.PURPLE, BarStyle.SOLID);
                break;
            case STRUCTUREFOUND:
                bossBar = Bukkit.createBossBar("Trova bioma: " + itemFind.toString(), BarColor.PURPLE, BarStyle.SOLID);
                break;
            case BIOMEFOUND:
                bossBar = Bukkit.createBossBar("Trova: " + itemFind, BarColor.PURPLE, BarStyle.SOLID);
                break;
            case MOBKILL:
                bossBar = Bukkit.createBossBar("Uccidi: " + itemFind, BarColor.PURPLE, BarStyle.SOLID);
                break;
        }


        // Give players their kit items and invoke onStart method.
        for (UUID uuid : arena.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);

            bossBar.addPlayer(player);

            // Teleport players to the arena spawn point.
            player.teleport(arena.getLocation());
        }


        this.runTaskTimer(ChallengeBattle.getInstance(), 0L, 20L);
    }

    @Override
    public void run() {

        for (UUID uuid : arena.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Tempo: " + time + "s"));
        }


        time++;
    }

}
