package it.marcomaraglino.challengebattle.configfile;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.structure.Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Configfile {
    private final FileConfiguration config = ChallengeBattle.getInstance().getConfig();
    private int requiredplayers = config.getInt("requiredplayers");
    private int maxnumberarenas = config.getInt("maxnumberarenas");
    private String arenaprefix = config.getString("arenaprefix");
    private String spawnworld = config.getString("spawnpoint.world");
    private double spawnx = config.getDouble("spawnpoint.x");
    private double spawnz = config.getDouble("spawnpoint.z");
    private double spawny = config.getDouble("spawnpoint.y");
    private final String maxnumberofarenas = config.getString("messages.maxnumberofarenas");

    private final String back = config.getString("guielements.back");
    private final String forward = config.getString("guielements.forward");
    private final String privateArena = config.getString("guielements.private");
    private final String publicArena = config.getString("guielements.public");
    private final String alreadyInArena = config.getString("messages.alreadyInArena");
    private final String notAplayer = config.getString("messages.notAPlayer");
    private  final String invalidInteger = config.getString("messages.invalidInteger");
    private final String invalidArena = config.getString("messages.invalidArena");
    private final String arenaStarted = config.getString("messages.arenaStarted");
    private final String joinedArena = config.getString("messages.joinedArena");
    private final String arenaCreated = config.getString("messages.arenaCreated");
    private final String leftArena = config.getString("messages.leftArena");
    private final String notInArena = config.getString("messages.notInArena");
    private final String inArena = config.getString("messages.inArena");
    private final String invalidSubcommand = config.getString("messages.invalidSubcommand");
    private final String cannotLeaveStartedArena = config.getString("messages.cannotLeaveStartedArena");
    private final String mustIncludeArenaID = config.getString("messages.mustIncludeArenaID");
    private final String invitedPlayer = config.getString("messages.invitedplayer");
    private final String invitedToArena = config.getString("messages.invitedtoarena");
    private final String mustIncludePlayer = config.getString("messages.mustincludeplayer");
    private final String cannotInviteSelf = config.getString("messages.cannotinviteself");
    private final String playerNotOnline = config.getString("messages.playernotonline");
    private final String clickToJoin = config.getString("messages.clicktojoin");
    private final String gamestarted = config.getString("messages.gamestarted");
    private final String findobject = config.getString("messages.findobject");
    private final String killmob = config.getString("messages.killmob");
    private final String gamewillstart = config.getString("messages.gamewillstart");
    private final String dimensionbattle = config.getString("messages.dimensionbattle");
    private final String nopermission = config.getString("messages.nopermission");
    private final String structurefind = config.getString("messages.structurefind");
    private final String biomefind = config.getString("messages.biomefind");
    private final String time = config.getString("messages.time");
    private final String wonthegame = config.getString("messages.wonthegame");
    private final String elementcolor = config.getString("guielements.elementcolor");
    private final String random = config.getString("guielements.random");
    private final Sound win_sound = Sound.valueOf(config.getString("win_sound"));
    private final Sound countdown_sound = Sound.valueOf(config.getString("countdown_sound"));
    private final Sound start_sound = Sound.valueOf(config.getString("start_sound"));
    private List<Material> createArenaMaterials = new ArrayList<>();
    private String createarenatitle = config.getString("guititles.createarenatitle");
    private String itemfound = config.getString("itemfound");
    private String biomefound = config.getString("biomefound");
    private String mobkill = config.getString("mobkill");
    private String blockedcommand = config.getString("messages.blockedcommand");
    private String structurefound = config.getString("structurefound");
    private String dimensionfound = config.getString("dimensionbattle");
    private String roomguicreate = config.getString("selectroom.createarena.title");
    private Material roomguimaterial = Material.valueOf(config.getString("selectroom.createarena.item").toUpperCase());
    private String roomguiarena = config.getString("selectroom.arenas.arena");
    private List<String> blockedcommands = (List<String>) config.getList("blockedcommands");
    private String roomguiarenamode = config.getString("selectroom.arenas.mode");
    private String roomguiarenaobject = config.getString("selectroom.arenas.object");
    private String roomsguititle = config.getString("guititles.roomsguititle");
    private String itemfindtitle = config.getString("guititles.itemfindtitle");
    private String mobkilltitle = config.getString("guititles.mobkilltitle");
    private String dimensionbattletitle = config.getString("guititles.dimensionbattletitle");
    private String structurefindtitle = config.getString("guititles.structurefindtitle");
    private String biomefindtitle = config.getString("guititles.biomefindtitle");
    private List<String> createArenaTitles = new ArrayList<>();
    private List<Integer> createArenaSlots = new ArrayList<>();
    private List<Biome> biomes = new ArrayList<>();
    private List<Material> items = new ArrayList<>();
    private List<EntityType> mobs = new ArrayList<>();
    private List<DimensionBattleStructure> dimensions = new ArrayList<>();
    private List<Structure> structures = new ArrayList<>();
    private void addStructures() {
        structures.add(Structure.ANCIENT_CITY);
        structures.add(Structure.BASTION_REMNANT);
        structures.add(Structure.BURIED_TREASURE);
        structures.add(Structure.DESERT_PYRAMID);
        structures.add(Structure.END_CITY);
        structures.add(Structure.FORTRESS);
        structures.add(Structure.IGLOO);
        structures.add(Structure.JUNGLE_PYRAMID);
        structures.add(Structure.MANSION);
        structures.add(Structure.MINESHAFT);
        structures.add(Structure.MINESHAFT_MESA);
        structures.add(Structure.MONUMENT);
        structures.add(Structure.NETHER_FOSSIL);
        structures.add(Structure.OCEAN_RUIN_COLD);
        structures.add(Structure.OCEAN_RUIN_WARM);
        structures.add(Structure.PILLAGER_OUTPOST);
        structures.add(Structure.RUINED_PORTAL);
        structures.add(Structure.RUINED_PORTAL_DESERT);
        structures.add(Structure.RUINED_PORTAL_JUNGLE);
        structures.add(Structure.RUINED_PORTAL_MOUNTAIN);
        structures.add(Structure.RUINED_PORTAL_NETHER);
        structures.add(Structure.RUINED_PORTAL_OCEAN);
        structures.add(Structure.RUINED_PORTAL_SWAMP);
        structures.add(Structure.SHIPWRECK);
        structures.add(Structure.SHIPWRECK_BEACHED);
        structures.add(Structure.STRONGHOLD);
        structures.add(Structure.SWAMP_HUT);
        structures.add(Structure.TRAIL_RUINS);
        structures.add(Structure.VILLAGE_DESERT);
        structures.add(Structure.VILLAGE_PLAINS);
        structures.add(Structure.VILLAGE_SAVANNA);
        structures.add(Structure.VILLAGE_SNOWY);
        structures.add(Structure.VILLAGE_TAIGA);
    }

    public Configfile() {

        addStructures();

        ConfigurationSection createArenaSec = config.getConfigurationSection("createArena");
        String createArenaPath = "createArena.";

        for (String key : createArenaSec.getKeys(false)) {
            createArenaTitles.add(config.getString(createArenaPath + key + ".title"));
            createArenaMaterials.add(Material.valueOf(config.getString(createArenaPath + key + ".item").toUpperCase()));
            createArenaSlots.add(config.getInt(createArenaPath + key + ".slot"));
        }


        List<String> removedItemsString = (config.getStringList("items"));
        List<Material> removedItems = new ArrayList<>();

        for (String item : removedItemsString) {
            removedItems.add(Material.valueOf(item.toUpperCase()));
        }

        items = new ArrayList<>(Arrays.asList(Material.values()));

        for (Material item : items) {
            if (!item.isItem()) {
                removedItems.add(item);
            }
        }

        items.removeAll(removedItems);

        List<String> removedBiomesString = (config.getStringList("biomes"));
        List<Biome> removedBiomes = new ArrayList<>();

        for (String biome : removedBiomesString) {
            removedBiomes.add(Biome.valueOf(biome.toUpperCase()));
        }

        biomes = new ArrayList<>(Arrays.asList(Biome.values()));
        biomes.removeAll(removedBiomes);

        List<String> removedMobsString = (config.getStringList("mobs"));
        List<EntityType> removedMobs = new ArrayList<>();

        for (String mob : removedMobsString) {
            removedMobs.add(EntityType.valueOf(mob.toUpperCase()));
        }

        mobs = new ArrayList<>(Arrays.asList(EntityType.values()));

        for (EntityType mob : mobs) {
            if (!mob.isAlive() || !mob.isSpawnable()) {
                removedMobs.add(mob);
            }
        }

        mobs.removeAll(removedMobs);

        List<Structure> removedStructures = new ArrayList<>();
        for (String structureString : config.getStringList("structures")) {
            removedStructures.add(Registry.STRUCTURE.get(NamespacedKey.minecraft(structureString.toLowerCase())));
        }
        structures.removeAll(removedStructures);


        ConfigurationSection dimensionsSec = config.getConfigurationSection("dimensions");
        String dimensionsPath = "dimensions.";

        for (String key : dimensionsSec.getKeys(false)) {
            DimensionBattleStructure dimensionBattleStructure = new DimensionBattleStructure();

            dimensionBattleStructure.setName(config.getString(dimensionsPath + key + ".name"));
            dimensionBattleStructure.setIcon(Material.valueOf(config.getString(dimensionsPath + key + ".icon").toUpperCase()));
            dimensionBattleStructure.setItem(World.Environment.valueOf(config.getString(dimensionsPath + key + ".dimension").toUpperCase()));

            dimensions.add(dimensionBattleStructure);

        }
    }
    public List<Integer> getCreateArenaSlots() {
        return createArenaSlots;
    }

    public List<Material> getCreateArenaMaterials() {
        return createArenaMaterials;
    }

    public List<String> getCreateArenaTitles() {
        return createArenaTitles;
    }

    public String getAlreadyInArena() {
        return alreadyInArena;
    }

    public String getNotAplayer() {
        return notAplayer;
    }

    public String getInvalidInteger() {
        return invalidInteger;
    }

    public String getInvalidArena() {
        return invalidArena;
    }

    public String getArenaStarted() {
        return arenaStarted;
    }

    public String getJoinedArena() {
        return joinedArena;
    }

    public String getArenaCreated() {
        return arenaCreated;
    }
    public String getNoPermission() {
        return nopermission;
    }

    public String getLeftArena() {
        return leftArena;
    }

    public String getNotInArena() {
        return notInArena;
    }

    public String getInArena() {
        return inArena;
    }

    public String getInvalidSubcommand() {
        return invalidSubcommand;
    }

    public String getCannotLeaveStartedArena() {
        return cannotLeaveStartedArena;
    }

    public String getMustIncludeArenaID() {
        return mustIncludeArenaID;
    }

    public String getCannotInviteSelf() {
        return cannotInviteSelf;
    }

    public String getInvitedPlayer() {
        return invitedPlayer;
    }

    public String getInvitedToArena() {
        return invitedToArena;
    }

    public String getMustIncludePlayer() {
        return mustIncludePlayer;
    }

    public String getPlayerNotOnline() {
        return playerNotOnline;
    }

    public String getClickToJoin() {
        return clickToJoin;
    }

    public List<DimensionBattleStructure> getDimensions() {
        return dimensions;
    }

    public int getRequiredplayers() {
        return requiredplayers;
    }

    public int getMaxnumberarenas() {
        return maxnumberarenas;
    }

    public String getArenaprefix() {
        return arenaprefix;
    }

    public String getSpawnworld() {
        return spawnworld;
    }

    public double getSpawnx() {
        return spawnx;
    }

    public double getSpawnz() {
        return spawnz;
    }

    public double getSpawny() {
        return spawny;
    }

    public String getBack() {
        return back;
    }

    public String getForward() {
        return forward;
    }

    public String getPrivateArena() {
        return privateArena;
    }

    public String getPublicArena() {
        return publicArena;
    }

    public String getGamestarted() {
        return gamestarted;
    }

    public String getFindobject() {
        return findobject;
    }

    public String getKillmob() {
        return killmob;
    }

    public String getDimensionbattle() {
        return dimensionbattle;
    }

    public String getStructurefind() {
        return structurefind;
    }

    public String getBiomefind() {
        return biomefind;
    }

    public String getTime() {
        return time;
    }

    public String getWonthegame() {
        return wonthegame;
    }

    public String getBlockedcommand() {
        return blockedcommand;
    }

    public String getMaxnumberofarenas() {
        return maxnumberofarenas;
    }

    public String getBiomefindtitle() {
        return biomefindtitle;
    }

    public String getCreatearenatitle() {
        return createarenatitle;
    }

    public String getDimensionbattletitle() {
        return dimensionbattletitle;
    }

    public String getItemfindtitle() {
        return itemfindtitle;
    }

    public String getMobkilltitle() {
        return mobkilltitle;
    }

    public String getRoomsguititle() {
        return roomsguititle;
    }

    public String getStructurefindtitle() {
        return structurefindtitle;
    }

    public String getRandom() {
        return random;
    }

    public String getElementcolor() {
        return elementcolor;
    }

    public Sound getWin_sound() {
        return win_sound;
    }

    public Sound getCountdown_sound() {
        return countdown_sound;
    }

    public Sound getStart_sound() {
        return start_sound;
    }

    public String getGamewillstart() {
        return gamewillstart;
    }

    public String getRoomguicreate() {
        return roomguicreate;
    }

    public Material getRoomguimaterial() {
        return roomguimaterial;
    }

    public String getRoomguiarena() {
        return roomguiarena;
    }

    public String getRoomguiarenamode() {
        return roomguiarenamode;
    }

    public String getRoomguiarenaobject() {
        return roomguiarenaobject;
    }

    public String getBiomefound() {
        return biomefound;
    }

    public String getItemfound() {
        return itemfound;
    }

    public String getDimensionfound() {
        return dimensionfound;
    }

    public String getMobkill() {
        return mobkill;
    }

    public List<String> getBlockedcommands() {
        return blockedcommands;
    }

    public String getStructurefound() {
        return structurefound;
    }

    public List<Biome> getBiomes() {
        return biomes;
    }

    public List<EntityType> getMobs() {
        return mobs;
    }

    public List<Material> getItems() {
        return items;
    }

    public List<Structure> getStructures() {
        return structures;
    }
}
