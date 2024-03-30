package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import it.marcomaraglino.challengebattle.arena.Arena;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PickItemListener implements Listener {

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

                player.teleport(Manager.SPAWN_POINT);
                arena.broadcast("Hai vinto il gioco!");
                arena.removePlayer(player.getUniqueId());
                arena.reset();
            }
        }
    }

}
