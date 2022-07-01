package tv.eztxm.ezutilities.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tv.eztxm.ezutilities.utils.FileManager;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!FileManager.getBoolean("DisableQuitMessage")) {
            if (FileManager.getBoolean("CustomQuitMessage")) {
                event.setQuitMessage(FileManager.getMessage("CustomQuitMessage")
                        .replace("%player%", player.getName()));
            }
        } else {
            event.setQuitMessage(null);
        }
    }
}
