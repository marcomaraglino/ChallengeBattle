package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.generator.structure.Structure;
import org.bukkit.util.StructureSearchResult;

public class StructureFindListener implements Listener {
    Configfile configfile = new Configfile();
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

        Structure structure = (Structure) arena.getGame().getItemFind();
        StructureSearchResult currentStructure = location.getWorld().locateNearestStructure(location, structure, 1, false);


        if (currentStructure != null && currentStructure.getStructure().equals(arena.getGame().getItemFind())) {
            for (int i = 0; i < arena.getPlayers().size(); i++){
                Manager.getInstance().getPlayerProfiles().get(arena.getPlayers().get(i)).addStructureFindPlayed();
            }

            Manager.getInstance().getPlayerProfiles().get(player.getUniqueId()).addStructureFindVictory();

            player.teleport(Manager.SPAWN_POINT);
            arena.broadcast(configfile.getWonthegame(), player);

            player.playSound(player.getLocation(), configfile.getWin_sound(), 1f, 1f);

            arena.removePlayer(player.getUniqueId());
            arena.reset();
        }
    }
}
