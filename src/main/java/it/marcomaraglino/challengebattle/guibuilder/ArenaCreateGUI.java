package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public class ArenaCreateGUI extends Gui {
    public ArenaCreateGUI(@NotNull Player player) {
        super(player, "creategui", "Create ROOM", 1);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        for (int i=0; i < GameType.values().length; i++) {
            GameType gameType = GameType.values()[i];

            addItem(i ,new Icon(Material.CREEPER_HEAD).setName(GameType.values()[i].name()).onClick(e -> {
                switch (gameType) {
                    case ITEMFOUND:
                        new ItemFindChallengGUI(player).open();
                        break;
                    case BIOMEFOUND:
                        break;
                }
            }));

        }
    }
}
