package it.marcomaraglino.challengebattle.commands;

import it.marcomaraglino.challengebattle.ChallengeBattle;
import it.marcomaraglino.challengebattle.arena.Arena;
import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.configfile.Configfile;
import it.marcomaraglino.challengebattle.guibuilder.ArenaCreateGUI;
import it.marcomaraglino.challengebattle.guibuilder.ChallengeRoomGui;
import it.marcomaraglino.challengebattle.manager.Manager;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.StructureType;

public class CommandArena implements CommandExecutor {
    Configfile configfile = new Configfile();
    ChallengeBattle main = ChallengeBattle.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(configfile.getNotAplayer());
            return true;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("arena")) {

            if (!player.hasPermission("challengebattle.arena.gui")) {
                player.sendMessage(configfile.getNoPermission());
                return true;
            }

            if (args.length < 1) {

                new ChallengeRoomGui(player).open();
                return true;
            }

            final String subCommand = args[0];

            if (subCommand.equalsIgnoreCase("join")) {

                if (!player.hasPermission("challengebattle.arena.join")) {
                    player.sendMessage(configfile.getNoPermission());
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(configfile.getMustIncludeArenaID());
                    return true;
                }

                final String idText = args[1];
                int id;

                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException e) {
                    player.sendMessage(configfile.getInvalidInteger());
                    return true;
                }

                if (!Manager.getInstance().isArena(id)) {
                    player.sendMessage(configfile.getInvalidArena());
                    return true;
                }

                final Arena arena = Manager.getInstance().getArena(id);

                final Arena current = Manager.getInstance().getArena(player.getUniqueId());
                if (current != null) {
                    player.sendMessage(configfile.getAlreadyInArena());
                    return true;
                }

                // Check if the arena permits players to join at this point.
                if (arena.getState() == GameState.STARTED) {
                    player.sendMessage(configfile.getArenaStarted());
                    return true;
                }

                arena.addPlayer(player.getUniqueId());
                player.sendMessage(configfile.getJoinedArena().replaceAll("%s", Integer.toString(arena.getId())));
                return true;

            } else if (subCommand.equalsIgnoreCase("create")) {

                if (!player.hasPermission("challengebattle.arena.create")) {
                    player.sendMessage(configfile.getNoPermission());
                    return true;
                }

                if (args.length != 2) {
                    new ArenaCreateGUI(player).open();
                    return true;
                }


            } else if (subCommand.equalsIgnoreCase("leave")) {

                if (!player.hasPermission("challengebattle.arena.leave")) {
                    player.sendMessage(configfile.getNoPermission());
                    return true;
                }

                // Get the player's current arena.
                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());


                if (arena != null) {

                    if(arena.getState() == GameState.STARTED) {
                        player.sendMessage(configfile.getCannotLeaveStartedArena());
                        return true;
                    }

                    arena.removePlayer(player.getUniqueId());

                    player.sendMessage(configfile.getLeftArena().replaceAll("%s", Integer.toString(arena.getId())));
                } else {
                    player.sendMessage(configfile.getNotInArena());
                }

                return true;

            } else if (subCommand.equalsIgnoreCase("id")) {

                if (!player.hasPermission("challengebattle.arena.id")) {
                    player.sendMessage(configfile.getNoPermission());
                    return true;
                }

                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());

                if (arena != null) {
                    player.sendMessage(configfile.getInArena().replaceAll("%s", Integer.toString(arena.getId())));
                }

                return true;

            } else if (subCommand.equalsIgnoreCase("invite")) {

                if (!player.hasPermission("challengebattle.arena.invite")) {
                    player.sendMessage(configfile.getNoPermission());
                    return true;
                }

                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());

                if (arena == null) {
                    player.sendMessage(configfile.getNotInArena());
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(configfile.getMustIncludePlayer());
                    return true;
                }

                if (args[1].equalsIgnoreCase(player.getName())) {
                    player.sendMessage(configfile.getCannotInviteSelf());
                    return true;
                }

                if (Bukkit.getPlayer(args[1]) == null) {
                    player.sendMessage(configfile.getPlayerNotOnline());
                    return true;
                }

                final Player target = player.getServer().getPlayer(args[1]);


                player.sendMessage(configfile.getInvitedPlayer().replaceAll("%s", target.getName()));
                target.sendMessage(configfile.getInvitedToArena().replaceAll("%s", player.getName()));

                TextComponent clickable = new TextComponent(configfile.getClickToJoin());
                clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/arena join " + arena.getId()));

                target.spigot().sendMessage(clickable);
            }

            else {
                player.sendMessage(configfile.getInvalidSubcommand());
                return true;
            }

        }

        return true;
    }

}
