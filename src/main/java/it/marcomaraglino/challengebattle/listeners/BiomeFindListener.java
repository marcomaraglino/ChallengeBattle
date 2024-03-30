package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BiomeFindListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Biome currentBiome = player.getLocation().getBlock().getBiome();

        Arena arena = Manager.getInstance().getArena(player.getUniqueId());

        if (arena == null) {
            return;
        }

        if (arena.getState() != GameState.STARTED) {
            return;
        }

        if (arena.getGame().getGameType() != GameType.BIOMEFOUND) {
            return;
        }


        if (currentBiome.equals(arena.getGame().getItemFind())) {
            player.teleport(Manager.SPAWN_POINT);
            arena.broadcast("Hai vinto il gioco!");
            arena.removePlayer(player.getUniqueId());
            arena.reset();
        }
    }
}
