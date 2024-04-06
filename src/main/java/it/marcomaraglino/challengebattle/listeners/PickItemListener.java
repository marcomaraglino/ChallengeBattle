package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import it.marcomaraglino.challengebattle.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PickItemListener implements Listener {

    Configfile configfile = new Configfile();
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player){

            Arena arena = Manager.getInstance().getArena(player.getUniqueId());

            if (arena == null) {
                return;
            }

            if (arena.getState() != GameState.STARTED) {
                return;
            }

            if (arena.getGame().getGameType() != GameType.ITEMFOUND) {
                return;
            }

            if (event.getItem().getItemStack().equals(new ItemStack((Material) arena.getGame().getItemFind()))){

                for (int i = 0; i < arena.getPlayers().size(); i++){
                    Manager.getInstance().getPlayerProfiles().get(arena.getPlayers().get(i)).addItemFindPlayed();
                }

                Manager.getInstance().getPlayerProfiles().get(player.getUniqueId()).addItemFindVictory();


                arena.teleportPlayersToSpawn();
                arena.broadcast(configfile.getWonthegame(), player);
                player.playSound(player.getLocation(), configfile.getWin_sound(), 1f, 1f);
                arena.removePlayer(player.getUniqueId());
                arena.reset();
            }
        }
    }

}
