package it.marcomaraglino.challengebattle.commands;

import it.marcomaraglino.challengebattle.arena.GameState;
import it.marcomaraglino.challengebattle.gamemod.GameType;
import it.marcomaraglino.challengebattle.guibuilder.ArenaCreateGUI;
import it.marcomaraglino.challengebattle.guibuilder.ItemFindChallengGUI;
import it.marcomaraglino.challengebattle.guibuilder.ChallengeRoomGui;
import it.marcomaraglino.challengebattle.manager.Manager;
import it.marcomaraglino.challengebattle.arena.Arena;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Structure;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.StructureType;

public class CommandArena implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only a player may execute this command!");
            return true;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("arena")) {

            if (args.length < 1) {
                new ChallengeRoomGui(player).open();
                return true;
            }

            final String subCommand = args[0];

            if (subCommand.equalsIgnoreCase("join")) {

                if (args.length != 2) {
                    player.sendMessage(ChatColor.RED + "You must include an arena ID.");
                    return true;
                }

                final String idText = args[1];
                int id;

                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a valid integer.");
                    return true;
                }

                if (!Manager.getInstance().isArena(id)) {
                    player.sendMessage(ChatColor.RED + "That is not a valid arena.");
                    return true;
                }

                final Arena arena = Manager.getInstance().getArena(id);

                final Arena current = Manager.getInstance().getArena(player.getUniqueId());
                if (current != null) {
                    player.sendMessage(ChatColor.RED + "You are already in an arena.");
                    return true;
                }

                // Check if the arena permits players to join at this point.
                if (arena.getState() == GameState.STARTED) {
                    player.sendMessage(ChatColor.RED + "You may not join this arena as it has already started.");
                    return true;
                }

                arena.addPlayer(player.getUniqueId());
                player.sendMessage(ChatColor.GREEN + "You have joined arena " + arena.getId() + ".");
                return true;

            } else if (subCommand.equalsIgnoreCase("create")) {


                if (args.length != 2) {
                    new ArenaCreateGUI(player).open();
                    return true;
                }
                player.sendMessage(ChatColor.GREEN + "Arena created!");
                new Arena(GameType.ITEMFOUND, Material.DIRT);


            } else if (subCommand.equalsIgnoreCase("leave")) {

                // Get the player's current arena.
                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());


                if (arena != null) {

                    if(arena.getState() == GameState.STARTED) {
                        player.sendMessage(ChatColor.RED + "You may not leave this arena as it has already started.");
                        return true;
                    }

                    arena.removePlayer(player.getUniqueId());

                    player.sendMessage(ChatColor.RED + "You have left arena " + arena.getId() + ".");
                } else {
                    player.sendMessage(ChatColor.RED + "You are not currently in an arena.");
                }

                return true;

            } else if (subCommand.equalsIgnoreCase("id")) {

                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());

                if (arena != null) {
                    player.sendMessage(ChatColor.GREEN + "You are in arena " + arena.getId() + ".");
                } else {
                    player.sendMessage(Manager.getInstance().getArenas().toString());
                }

                return true;

            } else {
                player.sendMessage(ChatColor.RED + "Invalid subcommand.");
                return true;
            }

        }

        return true;
    }

}
