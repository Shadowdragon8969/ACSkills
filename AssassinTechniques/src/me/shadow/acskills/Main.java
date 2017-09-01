package me.shadow.acskills;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.shadow.acskills.entity.ACPlayer;
import me.shadow.acskills.listener.PlayerClickListener;
import me.shadow.acskills.listener.PlayerJoinListener;
import me.shadow.acskills.listener.PlayerMoveListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin {

	public static Main instance;
	public static ItemStack creed() {
		ItemStack i = new ItemStack(Material.PAPER);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.RED + "Binding of the Creed");
		l.add(ChatColor.GREEN + "This page shows that your");
		l.add(ChatColor.GREEN + "training with the brotherhood");
		l.add(ChatColor.GREEN + "is complete. You may now use skills");
		l.add(ChatColor.GREEN + "outside of training.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	public static ItemStack wristBlade() {
		ItemStack i = new ItemStack(Material.FEATHER);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + "Hidden Blade");
		l.add(ChatColor.GREEN + "The prefered weapon of an assassin.");
		l.add(ChatColor.RED + "Damage: 6.5");
		l.add(ChatColor.DARK_GREEN + "Poison Chance: 25%");
		l.add(ChatColor.DARK_RED + "Instant-Kill Chance: 5%");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	public static ItemStack wristBladeWithGun() {
		ItemStack i = new ItemStack(Material.FEATHER);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + "Hidden Blade (Gun Installed)");
		l.add(ChatColor.GREEN + "The prefered weapon of an assassin.");
		l.add(ChatColor.RED + "Damage: 6.5");
		l.add(ChatColor.DARK_GREEN + "Poison Chance: 25%");
		l.add(ChatColor.DARK_RED + "Instant-Kill Chance: 5%");
		l.add(ChatColor.GRAY + "Right-Click to shoot");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	public static ItemStack appleOfEden() {
		ItemStack i = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + "Apple of Eden");
		l.add(ChatColor.GREEN + "A mysterious orb that was");
		l.add(ChatColor.GREEN + "plucked from the tree of knowlage");
		l.add(ChatColor.GREEN + "by Adam and Eve, the first");
		l.add(ChatColor.GREEN + "sinners.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	public static ItemStack staffOfEden() {
		ItemStack i = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + "Staff of Eden");
		l.add(ChatColor.GREEN + "A staff that was once used");
		l.add(ChatColor.GREEN + "to rule over the people of");
		l.add(ChatColor.GREEN + "Earth, it possesses strange");
		l.add(ChatColor.GREEN + "powers.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	public static ItemStack shroudOfEden() {
		Itemstack i = new ItemStack(Material.PAPER);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + "Shroud of Eden");
		l.add(ChatColor.GREEN + "A smooth clean sheet,");
		l.add(ChatColor.GREEN + "except for the blood stains.");
		l.add(ChatColor.GREEN + "Place this over any wound to");
		l.add(ChatColor.GREEN + "instantly recover, but use it");
		l.add(ChatColor.GREEN + "more than once, and you will");
		l.add(ChatColor.GREEN + "torn apart by its creator.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	public static ItemStack swordOfEden() {
		Itemstack i = new ItemStack(Material.GOLD_SWORD);
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		im.setDisplayName(ChatColor.GOLD + "Blade of Eden");
		l.add(ChatColor.GREEN + "A blade that had been stuck");
		l.add(ChatColor.GREEN + "inside of a rock for years,");
		l.add(ChatColor.GREEN + "waiting for the chosen one");
		l.add(ChatColor.GREEN + "to one day wield it again.");
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		instance = this;
		getLogger().info("Syncing Skills");
		registerEvents();
		ShapedRecipe bladeR = new ShapedRecipe(wristBlade());
		bladeR.shape("mm ",
				     "sii",
				     "mm ");
		bladeR.setIngredient('m', Material.LEATHER);
		bladeR.setIngredient('s', Material.STRING);
		bladeR.setIngredient('i', Material.IRON_INGOT);
		Bukkit.getServer().addRecipe(bladeR);
		ShapedRecipe bladeG = new ShapedRecipe(wristBladeWithGun());
		bladeG.shape("mm ",
				     "sxi",
				     "mm ");
		bladeG.setIngredient('m', Material.LEATHER);
		bladeG.setIngredient('s', Material.STRING);
		bladeG.setIngredient('i', Material.IRON_INGOT);
		bladeG.setIngredient('x', Material.DISPENSER);
		Bukkit.getServer().addRecipe(bladeG);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
			
			public void run() {
				for (Player p: Bukkit.getServer().getOnlinePlayers()) {
					ACPlayer acp = new ACPlayer(p);
					if(acp.hasSkills()) {
						acp.addStamina(1);
						sendActionBar(p, ChatColor.GOLD + "Stamina: " + acp.getStamina());
					}
				}
			}
		}, 0, 2);
	}
	
	public void registerEvents() {
		PluginManager plm = Bukkit.getServer().getPluginManager();
		plm.registerEvents(new PlayerClickListener(), getInstance());
		plm.registerEvents(new PlayerJoinListener(), getInstance());
		plm.registerEvents(new PlayerMoveListener(), getInstance());
	}
	
	public void onDisable() {
		getLogger().info("Unsyncing");
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static void sendActionBar(Player player, String args) {
		//IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + args + "\"}");
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(args));
	}
	
}
