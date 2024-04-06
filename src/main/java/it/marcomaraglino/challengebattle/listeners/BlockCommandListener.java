package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.manager.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockCommandListener implements Listener {
    Configfile configfile = new Configfile();
    @EventHandler
    public void onBlockCommand(PlayerCommandPreprocessEvent event) {
        for (String command : configfile.getBlockedcommands()) {
            Player player = event.getPlayer();

            Arena arena = Manager.getInstance().getArena(player.getUniqueId());

            if (arena == null) {
                return;
            }

            if (arena.getState() != GameState.STARTED) {
                return;
            }

            if (event.getMessage().toLowerCase().startsWith(command.toLowerCase())) {
                event.setCancelled(true);
                player.sendMessage(configfile.getBlockedcommand());
            }
        }
    }
}
