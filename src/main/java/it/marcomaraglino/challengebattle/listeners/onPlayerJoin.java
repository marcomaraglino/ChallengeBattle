package it.marcomaraglino.challengebattle.listeners;

import it.marcomaraglino.challengebattle.manager.Manager;
import it.marcomaraglino.challengebattle.playerprofile.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.teleport(Manager.SPAWN_POINT);

        PlayerProfile profile = new PlayerProfile(player.getUniqueId());
        Manager.getInstance().addPlayerProfile(player.getUniqueId() ,profile);
    }
}
