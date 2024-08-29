package it.marcomaraglino.challengebattle.arena;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.configfile.DimensionBattleStructure;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.Structure;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Game<T> extends BukkitRunnable {
    private Configfile configfile = new Configfile();
    private final Arena<T> arena;
    private GameType gameType;
    private int time;
    private BossBar bossBar;
    private String title;
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
        arena.broadcast(configfile.getGamestarted());

        switch (gameType) {
            case ITEMFOUND:
                title = ((org.bukkit.Material) itemFind).name();
                bossBar = Bukkit.createBossBar(configfile.getFindobject().replaceAll("%s", title), BarColor.PURPLE, BarStyle.SOLID);
                break;
            case STRUCTUREFOUND:
                Structure structure = (Structure) itemFind;
                title = configfile.getElementcolor() + structure.getKey().asString()
                        .replaceAll("minecraft:", "")
                        .replaceAll("_", " ");
                bossBar = Bukkit.createBossBar(configfile.getStructurefind().replaceAll("%s", title), BarColor.PURPLE, BarStyle.SOLID);
                break;
            case BIOMEFOUND:
                Biome biome = (Biome) itemFind;
                title = configfile.getElementcolor() + biome.name().replaceAll("_", " ");
                bossBar = Bukkit.createBossBar(configfile.getBiomefind().replaceAll("%s", title), BarColor.PURPLE, BarStyle.SOLID);
                break;
            case MOBKILL:
                EntityType mob = (EntityType) itemFind;
                title = configfile.getElementcolor() + mob.getName().toUpperCase().replaceAll("_", "");
                bossBar = Bukkit.createBossBar(configfile.getKillmob().replaceAll("%s", title), BarColor.PURPLE, BarStyle.SOLID);
                break;
            case DIMENSIONBATTLE:
                DimensionBattleStructure dimensionBattleStructure = (DimensionBattleStructure) itemFind;
                World.Environment environment = dimensionBattleStructure.getItem();
                title = environment.name();
                bossBar = Bukkit.createBossBar(configfile.getDimensionbattle().replaceAll("%s", title), BarColor.PURPLE, BarStyle.SOLID);
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
        int seconds = time % 60;
        int minutes = (time % 3600) / 60;
        int hours = time / 3600;

        for (UUID uuid : arena.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(configfile.getTime()
                    .replaceAll("%s", String.valueOf(seconds))
                    .replaceAll("%m", String.valueOf(minutes))
                    .replaceAll("%h", String.valueOf(hours))));
        }


        time++;

        /*if (getTime() > 260) {
            cancel();
            arena.reset();
        }

         */
    }

}
