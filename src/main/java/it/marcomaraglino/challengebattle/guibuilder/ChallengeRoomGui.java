package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeRoomGui extends Gui {
    Configfile configfile = new Configfile();
    public ChallengeRoomGui(@NotNull Player player) {
        super(player, "test-gui", "titolo gui", 3);
        this.setTitle(configfile.getRoomsguititle());
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(0, new Icon(configfile.getRoomguimaterial()).setName(configfile.getRoomguicreate()).onClick(e -> {
            if (!player.hasPermission("challengebattle.arena.create")) {
                player.sendMessage(configfile.getNoPermission());
                return;
            }
            new ArenaCreateGUI(player).open();
        }));

        List<Arena> arenas = Manager.getInstance().getArenaList().stream()
                .filter(arena -> !arena.isPrivateArena())
                .collect(Collectors.toList());


        for (int i = 0; i < arenas.size(); i++) {
            Icon icon;
            switch (arenas.get(i).getState()) {
                case WAITING:
                    icon = new Icon(Material.GREEN_WOOL);
                    break;
                case COUNTDOWN:
                    icon = new Icon(Material.YELLOW_WOOL);
                    break;
                default:
                    icon = new Icon(Material.RED_WOOL);
                    break;
            }
            icon.setName(configfile.getRoomguiarena().replaceAll("%s", Integer.toString(arenas.get(i).getId())));

            switch (arenas.get(i).getGame().getGameType()) {
                case ITEMFOUND:
                    icon.appendLore(configfile.getRoomguiarenamode().replaceAll("%s", configfile.getItemfound()));
                    break;
                case MOBKILL:
                    icon.appendLore(configfile.getRoomguiarenamode().replaceAll("%s", configfile.getMobkill()));
                    break;
                case STRUCTUREFOUND:
                    icon.appendLore(configfile.getRoomguiarenamode().replaceAll("%s", configfile.getStructurefound()));
                    break;
                case BIOMEFOUND:
                    icon.appendLore(configfile.getRoomguiarenamode().replaceAll("%s", configfile.getBiomefound()));
                    break;
                case DIMENSIONBATTLE:
                    icon.appendLore(configfile.getRoomguiarenamode().replaceAll("%s", configfile.getDimensionbattle()));
                    break;
            }
            icon.appendLore(configfile.getRoomguiarenaobject().replaceAll("%s", arenas.get(i).getStructure().getName()));
            icon.setAmount(arenas.get(i).getPlayers().size());


            int arena_id = i+1;
            icon.onClick(e -> {
                player.performCommand("arena join " + arena_id);
                setClosed(true);
            });


            addItem(i+2, icon);
        }
    }
}
