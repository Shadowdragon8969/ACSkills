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
		Player p = e.getPlayer();
		if (e.getMessage().equalsIgnoreCase("/assassin")) {
			if (!(e.getPlayer().hasPermission("acskills.item"))) return;
			e.setCancelled(true);
			ACPlayer acp = new ACPlayer(e.getPlayer());
			acp.setHasSkills(1);
		}
		if (e.getMessage().equalsIgnoreCase("/edenStave")) {
			if (!(e.getPlayer().hasPermission("acskills.eden.staff"))) return;
			e.setCancelled(true);
			p.getInventory().addItem(Main.staffOfEden());
		}
		if (e.getMessage().equalsIgnoreCase("/edenApple")) {
			if (!(e.getPlayer().hasPermission("acskills.eden.apple"))) return;
			e.setCancelled(true);
			p.getInventory().addItem(Main.applefEden());
		}
		if (e.getMessage().equalsIgnoreCase("/edenShroud")) {
			if (!(e.getPlayer().hasPermission("acskills.eden.shroud"))) return;
			e.setCancelled(true);
			p.getInventory().addItem(Main.shroudOfEden());
		}
		if (e.getMessage().equalsIgnoreCase("/edenSword")) {
			if (!(e.getPlayer().hasPermission("acskills.eden.sword"))) return;
			e.setCancelled(true);
			p.getInventory().addItem(Main.swordOfEden());
		}
	}
	
}
