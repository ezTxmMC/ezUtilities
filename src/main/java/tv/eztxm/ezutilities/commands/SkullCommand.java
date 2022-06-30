package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.EzUtilities;
import tv.eztxm.ezutilities.utils.FileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkullCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.skull")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            if (args.length == 0) {
                player.getInventory().addItem(EzUtilities.getInstance().getSkull(player.getName()));
                player.sendMessage(FileManager.getMessage("Skull").replace("%target%", player.getName()));
                return true;
            }
            if (args.length == 1) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotExists"));
                else {
                    player.getInventory().addItem(EzUtilities.getInstance().getSkull(args[0]));
                    player.sendMessage(FileManager.getMessage("Skull").replace("%target%", args[0]));
                }
                return true;
            }
            player.sendMessage(FileManager.getMessage("HowToUseSkull"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            for (Player players : Bukkit.getOnlinePlayers()) {
                playerNames.add(players.getName());
            }
            return playerNames;
        }
        return Collections.emptyList();
    }
}
