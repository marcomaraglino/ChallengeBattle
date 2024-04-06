package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class noPvPListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player && event.getEntity() instanceof Player target) {

            if (Manager.getInstance().getArena(player.getUniqueId()) != null && Manager.getInstance().getArena(target.getUniqueId()) != null) {
                event.setCancelled(true);
            }

        }
    }
}
