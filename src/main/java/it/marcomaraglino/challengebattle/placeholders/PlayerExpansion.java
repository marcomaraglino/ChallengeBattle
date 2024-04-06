package it.marcomaraglino.challengebattle.placeholders;

import it.marcomaraglino.challengebattle.manager.Manager;
import it.marcomaraglino.challengebattle.playerprofile.PlayerProfile;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "challengebattle";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MarcoMaraglino";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public @Nullable String onPlaceholderRequest(org.bukkit.entity.Player player, @NotNull String params) {
        if (player == null) {
            return "";
        }

        if (params.equals("ifplayed")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getItemfindplayed());
        }

        if (params.equals("ifvictories")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getItemfindvictories());
        }

        if (params.equals("bfplayed")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getBiomefindplayed());
        }

        if (params.equals("bfvictories")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getBiomefindvictories());
        }

        if (params.equals("sfplayed")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getStructurefindplayed());
        }

        if (params.equals("sfvictories")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getStructurefindvictories());
        }

        if (params.equals("dgplayed")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getDimensionchangeplayed());
        }

        if (params.equals("dgvictories")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getDimensionchangevictories());
        }

        if (params.equals("mkplayed")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getMobkillplayed());
        }

        if (params.equals("mkvictories")) {
            PlayerProfile profile = Manager.getInstance().getPlayerProfiles().get(player.getUniqueId());
            return Integer.toString(profile.getMobkillvictories());
        }

        return null;
    }
}
