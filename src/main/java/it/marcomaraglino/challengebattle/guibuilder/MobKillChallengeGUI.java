package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class MobKillChallengeGUI extends Gui {
    private final PaginationManager pagination = new PaginationManager(this);
    Configfile configfile = new Configfile();
    List<EntityType> mobs = configfile.getMobs();
    public MobKillChallengeGUI(@NotNull Player player) {
        super(player, "mobkillchallengegui", "Choose the mob", 6);
        this.pagination.registerPageSlotsBetween(9, 44);
        this.setTitle(configfile.getMobkilltitle());
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        final Arena current = Manager.getInstance().getArena(player.getUniqueId());

        pagination.getItems().clear();
        pagination.update();

        Icon randomIcon = new Icon(Material.ENDER_PEARL).setName(configfile.getRandom());
        randomIcon.onClick(e -> {
            Random random = new Random();
            int index = random.nextInt(mobs.size());
            EntityType mob = mobs.get(index);

            if (current != null) {
                player.sendMessage(configfile.getAlreadyInArena());
                return;
            }

            Arena arena = new Arena(GameType.MOBKILL, mobs.get(index));

            new SelectPrivateGUI(player, arena).open();
        });
        addItem(4, randomIcon);

        pagination.getItems().clear();
        pagination.update();

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

        pagination.getItems().clear();
        pagination.update();

        for (EntityType mob : configfile.getMobs()) {
            Icon mobicon;
            if (Material.getMaterial(mob.name().toUpperCase() + "_SPAWN_EGG") == null) {
                mobicon = new Icon(Material.EGG).setName(configfile.getElementcolor() + mob.name().toUpperCase().replaceAll("_", " "));
            } else {
                mobicon = new Icon(Material.getMaterial(mob.name().toUpperCase() + "_SPAWN_EGG")).setName(configfile.getElementcolor() + mob.getName().toUpperCase().replaceAll("_", ""));
            }
            this.pagination.addItem(mobicon.onClick(e -> {
                if (current != null) {
                    player.sendMessage(configfile.getAlreadyInArena());
                    return;
                }

                Arena arena = new Arena(GameType.MOBKILL, mob);
                new SelectPrivateGUI(player, arena).open();
            }));
            pagination.update();
        }
        pagination.update();
    }
}
