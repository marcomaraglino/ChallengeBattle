package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.configfile.DimensionBattleStructure;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class DimensionListener implements Listener {
    Configfile configfile = new Configfile();
    @EventHandler
    public void onDimensionChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        Arena arena = Manager.getInstance().getArena(player.getUniqueId());

        if (arena == null) {
            return;
        }

        if (arena.getState() != GameState.STARTED) {
            return;
        }

        if (arena.getGame().getGameType() != GameType.DIMENSIONBATTLE) {
            return;
        }

        World.Environment player_environment = player.getPlayer().getWorld().getEnvironment();
        DimensionBattleStructure dimensionBattleStructure = (DimensionBattleStructure) arena.getGame().getItemFind();
        World.Environment game_environment = dimensionBattleStructure.getItem();

        if (game_environment.equals(player_environment)) {
            for (int i = 0; i < arena.getPlayers().size(); i++){
                Manager.getInstance().getPlayerProfiles().get(arena.getPlayers().get(i)).addDimensionChangePlayed();
            }

            Manager.getInstance().getPlayerProfiles().get(player.getUniqueId()).addDimensionChangeVictory();

            arena.teleportPlayersToSpawn();
            arena.broadcast(configfile.getWonthegame(), player);
            player.playSound(player.getLocation(), configfile.getWin_sound(), 1f, 1f);

            arena.removePlayer(player.getUniqueId());
            arena.reset();
        }
    }
}
