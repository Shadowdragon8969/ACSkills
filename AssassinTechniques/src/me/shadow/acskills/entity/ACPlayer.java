package me.shadow.acskills.entity;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.Player;

import me.shadow.acskills.Main;
import net.md_5.bungee.api.ChatColor;

public class ACPlayer {

	private Player a;
	private static HashMap<Player, Integer> stamina = new HashMap<Player, Integer>();
	private static HashMap<Player, Boolean> shroudCounter = new HashMap<Player, Boolean>();
	
	public ACPlayer(Player arg0) {
		this.a = arg0;
		if (stamina.containsKey(a)) {
		}
		if (!stamina.containsKey(a)) {
			stamina.put(a, 0);
		}
		if (shroudCounter.containsKey(a)) {
		}
		if (!shroudCounter.containsKey(a)) {
			shroudCounter.put(a, false);
		}
	}
	
	public int getStamina() {
		return stamina.get(a);
	}
	
	public void swapCounter() {
		if (shroudCounter.get(a) == false) {
			shroudCounter.put(a, true);
			return;
		}
		if (shroudCounter.get(a) == true) {
			shroudCounter.put(a, false);
			return;
		}
	}
	
	public boolean getCounter() {
		return shroudCounter.get(a);
	}
	
	public ACPlayer addStamina(int amt) {
		int sta = stamina.get(a);
		if (sta < 100 && !(sta + amt > 100)) {
		stamina.put(a, sta + amt);
		}
		return this;
	}
	
	public boolean hasAtLeastSta(int amt) {
		int sta = stamina.get(a);
		if (sta >= amt) {
			return true;
		}else{
			return false;
		}
	}
	
	public void counterAttack(Player victim) {
		if (a.isSneaking() && hasAtLeastSta(20) && a.getLocation().distance(victim.getLocation()) > 2.5D) {
			int i = new Random().nextInt(3) + 1;
			switch(i) {
			case 1:
				remStamina(20);
				a.sendMessage(ChatColor.RED + "Counter Attack!");
				victim.damage(4.0D);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
		}
	}
	
	public ACPlayer remStamina(int amt) {
		int sta = stamina.get(a);
		if (sta >= 1 && !(sta - amt <= 0)) {
			stamina.put(a, sta - amt);
		}
		return this;
	}
	
	public boolean hasSkills() {
		if (a.getInventory().contains(Main.creed())) {
			return true;
		}else{
			return false;
		}
	}
	
	public ACPlayer setHasSkills(int arg0) {
		a.getInventory().addItem(Main.creed());
		return this;
	}
	
	public void build() {
		
	}
	
}
