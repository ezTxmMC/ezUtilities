package tv.eztxm.ezutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
                        player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[0]));
                    }
                }
                case 2 -> {
                    Player target1 = Bukkit.getPlayer(args[0]);
                    Player target2 = Bukkit.getPlayer(args[1]);
                    if (target1 == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                    else {
                        if (target2 == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                        else {
                            target1.teleport(target2);
                            if (player == target1) {
                                player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[1]));
                                return true;
                            }
                            target1.sendMessage(FileManager.getMessage("TeleportTo2").replace("%target1%", args[0]).replace("%target2%", args[1]));
                            player.sendMessage(FileManager.getMessage("TeleportPlayerTo").replace("%target1%", args[0]).replace("%target2%", args[1]));
                        }
                    }
                }
                case 3 -> {
                    try {
                        player.teleport(new Location(player.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])));
                        player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[0]+ " " + args[1] + " " + args[2]));
                    } catch (NumberFormatException e) {
                        player.sendMessage(FileManager.getMessage("NumberFormatNotRight"));
                    }
                }
                case 4 -> {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                    else {
                        try {
                            target.teleport(new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                            if (player == target) {
                                player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[1] + " " + args[2] + " " + args[3]));
                                return true;
                            }
                            target.sendMessage(FileManager.getMessage("TeleportTo2").replace("%target1%", args[0]).replace("%target2%", args[1] + " " + args[2] + " " + args[3]));
                            player.sendMessage(FileManager.getMessage("TeleportPlayerTo").replace("%target1%", args[0]).replace("%target2%", args[1] + " " + args[2] + " " + args[3]));
                        } catch (NumberFormatException e) {
                            player.sendMessage(FileManager.getMessage("NumberFormatNotRight"));
                        }
                    }
                }
                case 5 -> {
                        try {
                            player.teleport(new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5])));
                            player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[1] + " " + args[2] + " " + args[3]));
                        } catch (NumberFormatException e) {
                            player.sendMessage(FileManager.getMessage("NumberFormatNotRight"));
                        }
                }
                case 6 -> {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) player.sendMessage(FileManager.getMessage("PlayerNotOnline"));
                    else {
                        try {
                            target.teleport(new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5])));
                            if (player == target) {
                                player.sendMessage(FileManager.getMessage("TeleportTo").replace("%target%", args[1] + " " + args[2] + " " + args[3]));
                                return true;
                            }
                            target.sendMessage(FileManager.getMessage("TeleportTo2").replace("%target1%", args[0]).replace("%target2%", args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]));
                            player.sendMessage(FileManager.getMessage("TeleportPlayerTo").replace("%target1%", args[0]).replace("%target2%", args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5]));
                        } catch (NumberFormatException e) {
                            player.sendMessage(FileManager.getMessage("NumberFormatNotRight"));
                        }
                    }
                }
                default -> player.sendMessage(FileManager.getMessage("HowToUseTeleport"));
            }
        }
        return true;
    }
}
