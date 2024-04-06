package it.marcomaraglino.challengebattle.configfile;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
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
    private final String random = config.getString("guielements.random");
    private final Sound win_sound = Sound.valueOf(config.getString("win_sound"));
    private final Sound countdown_sound = Sound.valueOf(config.getString("countdown_sound"));
    private final Sound start_sound = Sound.valueOf(config.getString("start_sound"));
    private List<Material> createArenaMaterials = new ArrayList<>();
    private String createarenatitle = config.getString("guititles.createarenatitle");

    /*
    itemfound: "Item Find Battle"
biomefound: "Biome Find Battle"
mobkill: "Mob kill battle"
structurefound: "Structure Find Battle"
dimensionbattle: "Dimension Battle"
     */
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
    private List<BiomeFindStructure> biomes = new ArrayList<>();
    private List<ItemFindStructure> items = new ArrayList<>();
    private List<MobKillStructure> mobs = new ArrayList<>();
    private List<DimensionBattleStructure> dimensions = new ArrayList<>();
    private List<StructureFindStructure> structures = new ArrayList<>();
    public Configfile() {
        ConfigurationSection createArenaSec = config.getConfigurationSection("createArena");
        String createArenaPath = "createArena.";

        for (String key : createArenaSec.getKeys(false)) {
            createArenaTitles.add(config.getString(createArenaPath + key + ".title"));
            createArenaMaterials.add(Material.valueOf(config.getString(createArenaPath + key + ".item").toUpperCase()));
            createArenaSlots.add(config.getInt(createArenaPath + key + ".slot"));
        }

        ConfigurationSection itemsSec = config.getConfigurationSection("items");
        String itemsPath = "items.";

        for (String key : itemsSec.getKeys(false)) {
            ItemFindStructure itemFindStructure = new ItemFindStructure();

            itemFindStructure.setName(config.getString(itemsPath + key + ".name"));
            itemFindStructure.setIcon(Material.valueOf(config.getString(itemsPath + key + ".icon").toUpperCase()));
            itemFindStructure.setItem(Material.valueOf(config.getString(itemsPath + key + ".item").toUpperCase()));

            items.add(itemFindStructure);
        }

        ConfigurationSection biomesSec = config.getConfigurationSection("biomes");
        String biomesPath = "biomes.";

        for (String key : biomesSec.getKeys(false)) {
            BiomeFindStructure biomeFindStructure = new BiomeFindStructure();

            biomeFindStructure.setName(config.getString(biomesPath + key + ".name"));
            biomeFindStructure.setIcon(Material.valueOf(config.getString(biomesPath + key + ".icon").toUpperCase()));
            biomeFindStructure.setItem(Biome.valueOf(config.getString(biomesPath + key + ".biome").toUpperCase()));

            biomes.add(biomeFindStructure);

        }

        ConfigurationSection mobsSec = config.getConfigurationSection("mobs");
        String mobsPath = "mobs.";

        for (String key : mobsSec.getKeys(false)) {
            MobKillStructure mobKillStructure = new MobKillStructure();

            mobKillStructure.setName(config.getString(mobsPath + key + ".name"));
            mobKillStructure.setIcon(Material.valueOf(config.getString(mobsPath + key + ".icon").toUpperCase()));
            mobKillStructure.setItem(EntityType.valueOf(config.getString(mobsPath + key + ".mob").toUpperCase()));

            mobs.add(mobKillStructure);

        }

        ConfigurationSection dimensionsSec = config.getConfigurationSection("dimensions");
        String dimensionsPath = "dimensions.";

        for (String key : dimensionsSec.getKeys(false)) {
            DimensionBattleStructure dimensionBattleStructure = new DimensionBattleStructure();

            dimensionBattleStructure.setName(config.getString(dimensionsPath + key + ".name"));
            dimensionBattleStructure.setIcon(Material.valueOf(config.getString(dimensionsPath + key + ".icon").toUpperCase()));
            dimensionBattleStructure.setItem(World.Environment.valueOf(config.getString(dimensionsPath + key + ".dimension").toUpperCase()));

            dimensions.add(dimensionBattleStructure);

        }

        ConfigurationSection structuresSec = config.getConfigurationSection("structures");
        String structuresPath = "structures.";

        for (String key : structuresSec.getKeys(false)) {
            StructureFindStructure structureFindStructure = new StructureFindStructure();

            structureFindStructure.setName(config.getString(structuresPath + key + ".name"));
            structureFindStructure.setIcon(Material.valueOf(config.getString(structuresPath + key + ".icon").toUpperCase()));
            structureFindStructure.setItem(Registry.STRUCTURE.get(NamespacedKey.minecraft(config.getString(structuresPath + key + ".structure").toLowerCase())));
            structures.add(structureFindStructure);

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

    public List<BiomeFindStructure> getBiomes() {
        return biomes;
    }

    public List<ItemFindStructure> getItems() {
        return items;
    }

    public List<MobKillStructure> getMobs() {
        return mobs;
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

    public List<StructureFindStructure> getStructures() {
        return structures;
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
}
