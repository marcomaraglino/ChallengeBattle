package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.util.StructureSearchResult;

public class StructureFindListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        Arena arena = Manager.getInstance().getArena(player.getUniqueId());

        if (arena == null) {
            return;
        }

        if (arena.getState() != GameState.STARTED) {
            return;
        }

        if (arena.getGame().getGameType() != GameType.STRUCTUREFOUND) {
            return;
        }

        StructureSearchResult currentStructure = location.getWorld().locateNearestStructure(location, (StructureType) arena.getGame().getItemFind(), 10, false);

        if (currentStructure != null && currentStructure.getStructure().getStructureType().equals(arena.getGame().getItemFind())) {
            player.teleport(Manager.SPAWN_POINT);
            arena.broadcast("Hai vinto il gioco!");
            arena.removePlayer(player.getUniqueId());
            arena.reset();
        }
    }
}
