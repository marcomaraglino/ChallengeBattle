package it.marcomaraglino.challengebattle.guibuilder;

import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.manager.Manager;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArenaCreateGUI extends Gui {
    Configfile configfile = new Configfile();
    public ArenaCreateGUI(@NotNull Player player) {
        super(player, "creategui", "Create ROOM", 1);
        this.setTitle(configfile.getCreatearenatitle());
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        for (int i=0; i < GameType.values().length; i++) {
            GameType gameType = GameType.values()[i];
            List<Material> materials = configfile.getCreateArenaMaterials();
            List<String> titles = configfile.getCreateArenaTitles();
            List<Integer> slots = configfile.getCreateArenaSlots();

            addItem(slots.get(i) ,new Icon(materials.get(i)).setName(titles.get(i)).onClick(e -> {
                switch (gameType) {
                    case BIOMEFOUND -> new BiomeFindChallengeGUI(player).open();
                    case ITEMFOUND -> new ItemFindChallengGUI(player).open();
                    case MOBKILL -> new MobKillChallengeGUI(player).open();
                    case STRUCTUREFOUND -> new StructureFindGUI(player).open();
                    case DIMENSIONBATTLE -> new DimensionBattleGUI(player).open();
                    default -> System.out.println("Invalid game type!");
                }
            }));

        }
    }
}
