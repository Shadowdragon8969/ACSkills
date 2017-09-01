package me.shadow.acskills.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.shadow.acskills.entity.ACPlayer;
import net.md_5.bungee.api.ChatColor;

public class PlayerMoveListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClimbStart(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		ACPlayer acp = new ACPlayer(p);
		Block block, control;
		Vector dir;
		if (!(acp.hasSkills())) return;
		if (!(p.isSprinting())) return;
		dir = p.getVelocity().setY(0.5);
		if(e.getTo().getY() > e.getFrom().getY()) {
			block = p.getWorld().getBlockAt(new Location(p.getWorld(), e.getTo().getX(), e.getTo().getY()+0.5, e.getTo().getZ()));
			control = p.getWorld().getBlockAt(new Location(p.getWorld(), e.getTo().getX(), e.getTo().getY()-0.5, e.getTo().getZ()));
		    if(!(block.getTypeId() != 0 || control.getTypeId() == 0))
			{
				//event.getPlayer().teleportTo(new Location(event.getPlayer().getWorld(), event.getTo().getX()+diffX, event.getTo().getY()+2, event.getTo().getZ()+diffZ));
				p.setVelocity(dir);
			}
		}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		ACPlayer acp = new ACPlayer(p);
		if (!(acp.hasSkills())) return;
		if (!(p.isSneaking())) return;
		if (!(e.getCause() == DamageCause.FALL)) return;
		Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ());
		if (l.getBlock().getType() == Material.HAY_BLOCK || l.getBlock().getType() == Material.SKULL) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.YELLOW + "*You dove into the hay and took no damage*");
		}else{
			e.setDamage(e.getDamage() / 8);
			p.sendMessage(ChatColor.YELLOW + "*Rolled*");
			acp.remStamina(13);
		}
	}
	
}
