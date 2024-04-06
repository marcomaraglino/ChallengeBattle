package it.marcomaraglino.challengebattle.playerprofile;

import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerProfile {
    private int itemfindvictories;
    private int biomefindvictories;
    private int mobkillvictories;
    private int structurefindvictories;
    private int dimensionchangevictories;
    private int itemfindplayed;
    private int biomefindplayed;
    private int mobkillplayed;
    private int structurefindplayed;
    private int dimensionchangeplayed;
    private String playerName;

    public PlayerProfile(UUID uuid) {
        this.playerName = Bukkit.getPlayer(uuid).getName();
        this.biomefindvictories = 0;
        this.itemfindvictories = 0;
        this.mobkillvictories = 0;
        this.structurefindvictories = 0;
        this.biomefindplayed = 0;
        this.itemfindplayed = 0;
        this.mobkillplayed = 0;
        this.structurefindplayed = 0;
        this.dimensionchangeplayed = 0;
        this.dimensionchangevictories = 0;
    }
    public void addDimensionChangeVictory() {
        dimensionchangevictories++;
    }
    public void addDimensionChangePlayed() {
        dimensionchangeplayed++;
    }

    public int getDimensionchangeplayed() {
        return dimensionchangeplayed;
    }

    public int getDimensionchangevictories() {
        return dimensionchangevictories;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void addItemFindVictory() {
        itemfindvictories++;
    }
    public void addBiomeFindVictory() {
        biomefindvictories++;
    }
    public void addMobKillVictory() {
        mobkillvictories++;
    }
    public void addStructureFindVictory() {
        structurefindvictories++;
    }
    public void addItemFindPlayed() {
        itemfindplayed++;
    }
    public void addBiomeFindPlayed() {
        biomefindplayed++;
    }
    public void addMobKillPlayed() {
        mobkillplayed++;
    }
    public void addStructureFindPlayed() {
        structurefindplayed++;
    }


    public int getItemfindvictories() {
        return itemfindvictories;
    }

    public int getBiomefindvictories() {
        return biomefindvictories;
    }

    public int getMobkillvictories() {
        return mobkillvictories;
    }

    public int getStructurefindvictories() {
        return structurefindvictories;
    }

    public int getItemfindplayed() {
        return itemfindplayed;
    }

    public int getBiomefindplayed() {
        return biomefindplayed;
    }

    public int getMobkillplayed() {
        return mobkillplayed;
    }

    public int getStructurefindplayed() {
        return structurefindplayed;
    }
}
