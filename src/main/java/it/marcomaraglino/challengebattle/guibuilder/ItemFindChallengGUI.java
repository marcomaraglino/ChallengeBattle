package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import mc.obliviate.inventory.pagination.PaginationManager;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class ItemFindChallengGUI extends Gui {
    private final PaginationManager pagination = new PaginationManager(this);
    public ItemFindChallengGUI(@NotNull Player player) {
        super(player, "itemfindchallengegui", "Choose the item", 6);
        this.pagination.registerPageSlotsBetween(9, 44);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        pagination.getItems().clear();

        Icon randomIcon = new Icon(Material.ENDER_PEARL).setName("§2Pick Random!");
        randomIcon.onClick(e -> {
            Material[] materials = Material.values();
            Random random = new Random();
            Material material = materials[random.nextInt(materials.length)];
            player.sendMessage("§aYou picked: " + material);

            final Arena current = Manager.getInstance().getArena(player.getUniqueId());

            if (current != null) {
                player.sendMessage("§cYou are already in an arena!");
                return;
            }

            Arena arena = new Arena(GameType.ITEMFOUND, material);
            player.performCommand("arena join " + arena.getId());
        });
        addItem(4, randomIcon);



        if (!(pagination.getCurrentPage() == 0)) {
            System.out.println(pagination.getCurrentPage());
            Icon icon = new Icon(Material.ARROW).setName("§aIndietro").onClick(e -> {
                this.pagination.goPreviousPage();
                this.pagination.update();
                open();
            });
            addItem(0, icon);
        }

        if (!(pagination.getCurrentPage() >= 36)) {
            System.out.println(pagination.getCurrentPage());

            Icon icon = new Icon(Material.ARROW).setName("§aAvanti").onClick(e -> {
                this.pagination.goNextPage();
                this.pagination.update();
                open();
            });

            addItem(8,icon);
        } else {
            addItem(8, new Icon(Material.AIR));
        }

        for (Material material : Material.values()) {
            this.pagination.addItem(new Icon(material).onClick(e -> {
                final Arena current = Manager.getInstance().getArena(player.getUniqueId());

                if (current != null) {
                    player.sendMessage("§cYou are already in an arena!");
                    return;
                }

                Arena arena = new Arena(GameType.ITEMFOUND, material);
                player.performCommand("arena join " + arena.getId());
                setClosed(true);
                event.setCancelled(true);
            }));
        }
        pagination.update();
    }
}
