package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.eztxm.ezutilities.utils.FileManager;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (!player.hasPermission("ezutilities.teleport")) player.sendMessage(FileManager.getMessage("NoPerms"));
        else {
            switch (args.length) {
                case 1 -> {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                    else {
                        player.teleport(target);
                    }
                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 6 -> {

                }
            }
            player.sendMessage(FileManager.getMessage("HowToUseTeleport"));
        }
        return true;
    }
}
