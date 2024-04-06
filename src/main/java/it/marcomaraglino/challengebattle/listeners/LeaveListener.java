package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Arena arena = Manager.getInstance().getArena(player.getUniqueId());
        if (arena != null) {
            arena.removePlayer(player.getUniqueId());
        }
    }
}
