package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.configfile.MobKillStructure;
import it.marcomaraglino.challengebattle.configfile.StructureFindStructure;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class StructureFindGUI extends Gui {
    private final PaginationManager pagination = new PaginationManager(this);
    Configfile configfile = new Configfile();
    List<StructureFindStructure> structures = configfile.getStructures();
    public StructureFindGUI(@NotNull Player player) {
        super(player, "structurefindgui", "Choose the structure", 6);
        this.setTitle(configfile.getStructurefindtitle());

        this.pagination.registerPageSlotsBetween(9, 44);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        final Arena current = Manager.getInstance().getArena(player.getUniqueId());
        pagination.getItems().clear();

        Icon randomIcon = new Icon(Material.ENDER_PEARL).setName(configfile.getRandom());
        randomIcon.onClick(e -> {
            Random random = new Random();
            int index = random.nextInt(structures.size());
            Structure structure = structures.get(index).getItem();

            if (current != null) {
                player.sendMessage(configfile.getAlreadyInArena());
                return;
            }

            Arena arena = new Arena(GameType.STRUCTUREFOUND, structures.get(index));
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


        for (StructureFindStructure structure : configfile.getStructures()) {
            this.pagination.addItem(new Icon(structure.getIcon()).setName(structure.getName()).onClick(e -> {
                if (current != null) {
                    player.sendMessage(configfile.getAlreadyInArena());
                    return;
                }

                Arena arena = new Arena(GameType.STRUCTUREFOUND, structure);
                new SelectPrivateGUI(player, arena).open();
            }));
        }
        pagination.update();
    }
}
