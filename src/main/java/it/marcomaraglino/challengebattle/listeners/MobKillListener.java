package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKillListener implements Listener {
    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {

        Entity entity = event.getEntity();

        if (!(entity instanceof Mob)) {
            return;
        }

        Player killer = ((Mob) entity).getKiller();

        if (killer == null) {
            return;
        }

        Arena arena = Manager.getInstance().getArena(killer.getUniqueId());

        if (arena == null) {
            return;
        }

        if (arena.getState() != GameState.STARTED) {
            return;
        }

        if (arena.getGame().getGameType() != GameType.MOBKILL) {
            return;
        }

        EntityType entityType = entity.getType();

        if (entityType == arena.getGame().getItemFind()) {
            killer.teleport(Manager.SPAWN_POINT);
            arena.broadcast("Hai vinto il gioco!");
            arena.removePlayer(killer.getUniqueId());
            arena.reset();
        }
    }
}
