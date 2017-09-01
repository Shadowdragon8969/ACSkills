package me.shadow.acskills.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.shadow.acskills.entity.ACPlayer;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		ACPlayer acp = new ACPlayer(p);
		acp.build();
	}
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("/assassin")) {
			if (!(e.getPlayer().hasPermission("acskills.item"))) return;
			e.setCancelled(true);
			ACPlayer acp = new ACPlayer(e.getPlayer());
			acp.setHasSkills(1);
		}
	}
	
}
