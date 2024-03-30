package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChallengeRoomGui extends Gui {
    public ChallengeRoomGui(@NotNull Player player) {
        super(player, "test-gui", "titolo gui", 3);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(0, new Icon(Material.NETHER_STAR).onClick(e -> {
            new ArenaCreateGUI(player).open();
        }));

        ArrayList<Arena> arenas = Manager.getInstance().getArenaList();


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
            icon.setName(ChatColor.translateAlternateColorCodes('&',"Arena " + arenas.get(i).getId()));
            icon.setLore(ChatColor.translateAlternateColorCodes('&', "&cStato: " + arenas.get(i).getState().toString().toLowerCase()));
            icon.appendLore(ChatColor.translateAlternateColorCodes('&', "Modalità: " + arenas.get(i).getGame().getGameType().name().toLowerCase()));
            icon.appendLore(ChatColor.translateAlternateColorCodes('&', "Item / Mob / Biome / Scruture: " + arenas.get(i).getGame().getItemFind()));
            icon.setAmount(arenas.get(i).getPlayers().size() + 1);

            String arena_id = ChatColor.translateAlternateColorCodes('&',icon.getItem().getItemMeta().getDisplayName().substring(6));
            icon.onClick(e -> {
                player.performCommand("arena join " + arena_id);
                setClosed(true);
            });


            addItem(i+2, icon);
        }
    }
}
