package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.configfile.DimensionBattleStructure;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class DimensionBattleGUI extends Gui {
    private final PaginationManager pagination = new PaginationManager(this);

    Configfile configfile = new Configfile();

    List<DimensionBattleStructure> dimensions = configfile.getDimensions();

    public DimensionBattleGUI(@NotNull Player player) {
        super(player, "dimensionbattlegui", "Select Dimension", 6);
        this.pagination.registerPageSlotsBetween(9, 44);
        this.setTitle(configfile.getDimensionbattletitle());
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        final Arena current = Manager.getInstance().getArena(player.getUniqueId());

        Icon randomIcon = new Icon(Material.ENDER_PEARL).setName(configfile.getRandom());
        randomIcon.onClick(e -> {
            Random random = new Random();
            int index = random.nextInt(dimensions.size());
            World.Environment dimension = dimensions.get(index).getItem();

            if (current != null) {
                player.sendMessage(configfile.getAlreadyInArena());
                return;
            }

            Arena arena = new Arena(GameType.DIMENSIONBATTLE, dimensions.get(index));
            new SelectPrivateGUI(player, arena).open();
        });
        addItem(4, randomIcon);

        if (!(pagination.getCurrentPage() == 0)) {
            Icon icon = new Icon(Material.ARROW).setName(configfile.getBack()).onClick(e -> {
                this.pagination.goPreviousPage();
                this.pagination.update();
                open();
            });
            addItem(0, icon);
        }

        if (!(pagination.isLastPage())) {

            Icon icon = new Icon(Material.ARROW).setName(configfile.getForward()).onClick(e -> {
                this.pagination.goNextPage();
                this.pagination.update();
                open();
            });

            addItem(8,icon);
        } else {
            addItem(8, new Icon(Material.AIR));
        }

        int i = 0;
        for (DimensionBattleStructure dimension : dimensions) {
            this.pagination.addItem(new Icon(dimension.getIcon()).setName(configfile.getElementcolor() + dimension.getName().replaceAll("_", " ")).onClick(e -> {
                if (current != null) {
                    player.sendMessage(configfile.getAlreadyInArena());
                    return;
                }

                Arena arena = new Arena(GameType.DIMENSIONBATTLE, dimension);
                new SelectPrivateGUI(player, arena).open();
            }));
            i++;
        }
        pagination.update();
    }
}
