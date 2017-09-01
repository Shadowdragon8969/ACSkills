package me.shadow.acskills.listener;

import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.shadow.acskills.Main;
import me.shadow.acskills.entity.ACPlayer;
import net.md_5.bungee.api.ChatColor;

public class PlayerClickListener implements Listener {

	@EventHandler
	public void climbButton(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ());
		ACPlayer acp = new ACPlayer(p);
		if (!(acp.hasSkills())) return;
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (!(e.getClickedBlock().getType() == Material.STONE_BUTTON)) return;
		if (!(loc.distance(e.getClickedBlock().getLocation()) < 2)) return;
		Vector dir = p.getVelocity().setY(0.7);
		p.setVelocity(dir);
 	}
	
	@EventHandler
	public void climbButton2(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ());
		ACPlayer acp = new ACPlayer(p);
		if (!(acp.hasSkills())) return;
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (!(e.getClickedBlock().getType() == Material.WOOD_BUTTON)) return;
		if (!(loc.distance(e.getClickedBlock().getLocation()) < 2)) return;
		Vector dir = p.getVelocity().setY(0.7);
		p.setVelocity(dir);
 	}
	
	@EventHandler
	public void climbBars(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ());
		ACPlayer acp = new ACPlayer(p);
		if (!(acp.hasSkills())) return;
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (!(e.getClickedBlock().getType() == Material.IRON_FENCE)) return;
		if (!(loc.distance(e.getClickedBlock().getLocation()) < 2)) return;
		Vector dir = p.getVelocity().setY(0.7);
		p.setVelocity(dir);

 	}
	
	@EventHandler
	public void climbWall(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ());
		ACPlayer acp = new ACPlayer(p);
		if (!(acp.hasSkills())) return;
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (!(e.getClickedBlock().getType() == Material.COBBLE_WALL)) return;
		if (!(loc.distance(e.getClickedBlock().getLocation()) < 2)) return;
		Vector dir = p.getVelocity().setY(0.7);
		p.setVelocity(dir);
 	}
	
	@EventHandler
	public void punchAssassin(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player v = (Player) e.getDamager();
			ACPlayer acp = new ACPlayer(p);
			ACPlayer acv = new ACPlayer(v);
			if (acp.hasSkills() && acv.hasSkills()) {
				int r = new Random().nextInt(1) + 1;
				switch(r) {
				case 1:
					acv.counterAttack(p);
					e.setCancelled(true);
					break;
				case 2:
					break;
				}
			}
			if (acp.hasSkills() && !acv.hasSkills()) return;
			if (!acp.hasSkills() && acv.hasSkills()) {
				e.setCancelled(true);
				acv.counterAttack(p);
			}
		}else {
			return;
		}
	}
	
	@EventHandler
	public void healthBurst(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ACPlayer acp = new ACPlayer(p);
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		ItemStack it = p.getInventory().getItemInMainHand();
		if (it != null && it.getType() == Material.GOLD_SWORD
				&& it.hasItemMeta() && it.getItemMeta().hasDisplayName()
				&& it.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Shroud of Eden")) {
			if (acp.getCounter() == false) {
				p.setHealth(p.getMaxHealth());
				acp.swapCounter();
			}else{
				if (acp.getCounter() == true) {
					p.setHealth(0.0);
					Bukkit.getServer().broadcastMessage(ChatColor.GREEN + p.getName() + " was torn apart by the Shroud of Eden");
					acp.swapCounter();
				}
			}
		}
	
	@EventHandler
	public void swordBurst(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ACPlayer acp = new ACPlayer(p);
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		ItemStack it = p.getInventory().getItemInMainHand();
		if (it != null && it.getType() == Material.GOLD_SWORD
				&& it.hasItemMeta() && it.getItemMeta().hasDisplayName()
				&& it.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Blade of Eden")) {
			p.damage(1.5);
			new BukkitRunnable() {
				Player p = e.getPlayer();
				Vector dir = p.getLocation().getDirection().normalize();
				Location loc = p.getLocation();
				double t = 0;
				@SuppressWarnings("deprecation")
				public void run() {
					t = t + 1.0;
					double x = dir.getX() * t;
					double y = dir.getY() * t + 1.3;
					double z = dir.getZ() * t;
					loc.add(x,y,z);
					for (Player play : loc.getWorld().getPlayers()) {
						play.playEffect(loc, Effect.FLAME, 0);
					}
					for (Entity ent : loc.getWorld().getEntities()) {
						if (ent.getLocation().distance(loc) < 1.5) {
							if (!ent.equals(p) && ent instanceof Damageable) {
								Damageable d = (Damageable) ent;
								d.damage(2.0);
							}
						}
					}
					
						if (!(loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.BEDROCK || loc.getBlock().getType() == Material.OBSIDIAN)) {
							p.playEffect(loc, Effect.EXPLOSION, 1);
							this.cancel();
						}
					
					loc.subtract(x,y,z);
					if (t > 40) {
						this.cancel();
					}
				}
			}.runTaskTimer(Main.getInstance(), 0, 1);
		}
	}
	
	@EventHandler
	public void gunShoot(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ACPlayer acp = new ACPlayer(p);
		if (!(acp.hasSkills())) return;
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		if (!(p.getInventory().containsAtLeast(new ItemStack(Material.IRON_NUGGET), 1))) return;
		ItemStack it = p.getInventory().getItemInMainHand();
		if (it != null && it.getType() == Material.FEATHER
				&& it.hasItemMeta() && it.getItemMeta().hasDisplayName()
				&& it.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Hidden Blade (Gun Installed)")) {
			for (ItemStack itt : p.getInventory().getContents()) {
				if (itt != null && itt.getType() == Material.IRON_NUGGET) {
					itt.setAmount(itt.getAmount() - 1);
					if (itt.getAmount() < 1) {
						p.getInventory().remove(itt);
					}
				}
			}
			new BukkitRunnable() {
				Player p = e.getPlayer();
				Vector dir = p.getLocation().getDirection().normalize();
				Location loc = p.getLocation();
				double t = 0;
				@SuppressWarnings("deprecation")
				public void run() {
					t = t + 1.0;
					double x = dir.getX() * t;
					double y = dir.getY() * t + 1.3;
					double z = dir.getZ() * t;
					loc.add(x,y,z);
					for (Player play : loc.getWorld().getPlayers()) {
						play.playEffect(loc, Effect.CRIT, 0);
					}
					for (Entity ent : loc.getWorld().getEntities()) {
						if (ent.getLocation().distance(loc) < 1.5) {
							if (!ent.equals(p) && ent instanceof Damageable) {
								Damageable d = (Damageable) ent;
								d.damage(2.5);
							}
						}
					}
					
						if (!(loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.BEDROCK || loc.getBlock().getType() == Material.OBSIDIAN)) {
							p.playEffect(loc, Effect.EXPLOSION, 1);
							this.cancel();
						}
					
					loc.subtract(x,y,z);
					if (t > 40) {
						this.cancel();
					}
				}
			}.runTaskTimer(Main.getInstance(), 0, 1);
		}
	}
	
	@EventHandler
	public void martialStability(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof LivingEntity) {
				Player p = (Player) e.getDamager();
				ACPlayer acp = new ACPlayer(p);
				if (acp.hasSkills()) {
					if (p.getInventory().getItemInMainHand() == null || p.getInventory().getItemInMainHand().getType() == Material.AIR) {
						e.setDamage(e.getDamage() + 4.5);
					}
					LivingEntity d = (LivingEntity) e.getEntity();
					ItemStack it = p.getInventory().getItemInMainHand();
					if (it != null && it.getType() == Material.FEATHER
						&& it.hasItemMeta() && it.getItemMeta().hasDisplayName()
						&& it.getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Hidden Blade")) {
						e.setDamage(e.getDamage() + 6.5);
						int i = new Random().nextInt(3) + 1;
						switch(i) {
						case 2:
							d.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 1));
							p.sendMessage(ChatColor.DARK_GREEN + "*Poisoned*");
							break;
						case 1: break; case 3: break; case 4: break;
						}
						int i2 = new Random().nextInt(9) + 1;
						switch(i2) {
						case 2:
							e.setDamage(d.getHealth());
							p.sendMessage(ChatColor.RED + "*Assassinated*");
							break;
						case 1: break; case 3: break; case 4: break; case 5: break; case 6: break; case 7: break; case 8: break;
						case 9: break; case 10: break;
						}
					}
				}else{
					return;
				}
			}else{
				return;
			}
		}else{
			return;
		}
	}
	
}
