package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class BiomeFindChallengeGUI extends Gui {
    private final PaginationManager pagination = new PaginationManager(this);

    Configfile configfile = new Configfile();

    List<Biome> biomes = configfile.getBiomes();

    public BiomeFindChallengeGUI(@NotNull Player player) {
        super(player, "biomefindgui", "Select BIOME", 6);
        this.pagination.registerPageSlotsBetween(9, 44);
        this.setTitle(configfile.getBiomefindtitle());
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        final Arena current = Manager.getInstance().getArena(player.getUniqueId());
        pagination.getItems().clear();

        Icon randomIcon = new Icon(Material.ENDER_PEARL).setName(configfile.getRandom());
        randomIcon.onClick(e -> {
            Random random = new Random();
            int index = random.nextInt(biomes.size());
            Biome biome = biomes.get(index);

            if (current != null) {
                player.sendMessage(configfile.getAlreadyInArena());
                return;
            }

            Arena arena = new Arena(GameType.BIOMEFOUND, biomes.get(index));

            new SelectPrivateGUI(player, arena).open();
        });
        addItem(4, randomIcon);

            Icon back = new Icon(Material.ARROW).setName(configfile.getBack()).onClick(e -> {
                this.pagination.goPreviousPage();
                this.pagination.update();
                open();
            });
            addItem(0, back);


            Icon forward = new Icon(Material.ARROW).setName(configfile.getForward()).onClick(e -> {
                this.pagination.goNextPage();
                this.pagination.update();
                open();
            });

            addItem(8,forward);

        for (Biome biome : biomes) {
            this.pagination.addItem(new Icon(Material.OAK_SAPLING).setName(configfile.getElementcolor() + biome.name().replaceAll("_", " ")).onClick(e -> {
                if (current != null) {
                    player.sendMessage(configfile.getAlreadyInArena());
                    return;
                }

                Arena arena = new Arena(GameType.BIOMEFOUND, biome);
                new SelectPrivateGUI(player, arena).open();
            }));
        }
        pagination.update();
    }
}
