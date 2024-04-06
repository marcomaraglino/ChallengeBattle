package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public class SelectPrivateGUI extends Gui {
    private Arena arena;

    Configfile configfile = new Configfile();
    public SelectPrivateGUI(@NotNull Player player, Arena arena) {
        super(player, "selectprivategui", "Private or not?", 1);
        this.arena = arena;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        addItem(0, new Icon(Material.GREEN_WOOL).setName(configfile.getPrivateArena()).onClick(e -> {
            if (Manager.getInstance().getArenaList().size() >= 5) {
                player.sendMessage(configfile.getMaxnumberofarenas());
                return;
            }


            arena.setPrivateArena(true);
            arena.addArena();
            player.performCommand("arena join " + arena.getId());
            player.sendMessage(configfile.getArenaCreated());
            player.closeInventory();
        }));

        addItem(8, new Icon(Material.RED_WOOL).setName(configfile.getPublicArena()).onClick(e -> {
            if (Manager.getInstance().getArenaList().size() >= configfile.getMaxnumberarenas()) {
                player.sendMessage(configfile.getMaxnumberofarenas());
                return;
            }
            arena.setPrivateArena(false);
            arena.addArena();
            player.performCommand("arena join " + arena.getId());

            player.sendMessage(configfile.getArenaCreated());

            player.closeInventory();
        }));

    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        if (event.getReason().equals(InventoryCloseEvent.Reason.PLAYER)) {
            open();
        }
        super.onClose(event);
    }
}
