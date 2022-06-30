package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.utils.FileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.gamemode")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 1) {
                switch (args[0]) {
                    case "survival", "0", "s" -> {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Survival"));
                    }
                    case "creative", "1", "c" -> {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Creative"));
                    }
                    case "adventure", "2", "a" -> {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Adventure"));
                    }
                    case "spectator", "3", "sp" -> {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Spectator"));
                    }
                    default -> player.sendMessage(FileManager.getMessage("HowToUseGamemode"));
                }
                return true;
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                else {
                    switch (args[0]) {
                        case "survival", "0", "s" -> {
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Survival"));
                            player.sendMessage(FileManager.getMessage("ChangeGamemodeByPlayer")
                                    .replace("%gamemode%", "Survival").replace("%target%", target.getName()));
                        }
                        case "creative", "1", "c" -> {
                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Survival"));
                            player.sendMessage(FileManager.getMessage("ChangeGamemodeByPlayer")
                                    .replace("%gamemode%", "Creative").replace("%target%", target.getName()));
                        }
                        case "adventure", "2", "a" -> {
                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Survival"));
                            player.sendMessage(FileManager.getMessage("ChangeGamemodeByPlayer")
                                    .replace("%gamemode%", "Adventure").replace("%target%", target.getName()));
                        }
                        case "spectator", "3", "sp" -> {
                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage(FileManager.getMessage("ChangeGamemode").replace("%gamemode%", "Survival"));
                            player.sendMessage(FileManager.getMessage("ChangeGamemodeByPlayer")
                                    .replace("%gamemode%", "Spectator").replace("%target%", target.getName()));
                        }
                        default -> player.sendMessage(FileManager.getMessage("HowToUseGamemode"));
                    }
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseGamemode"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> gamemodes = new ArrayList<>();
            gamemodes.add("survival");
            gamemodes.add("creative");
            gamemodes.add("adventure");
            gamemodes.add("spectator");
            return gamemodes;
        }
        if (args.length == 2) {
            List<String> playerNames = new ArrayList<>();
            for (Player players : Bukkit.getOnlinePlayers()) {
                playerNames.add(players.getName());
            }
            return playerNames;
        }
        return Collections.emptyList();
    }
}
