package fr.aang.gvote.task;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.aang.gvote.Main;
import fr.aang.gvote.config.WebServ;
import fr.aang.gvote.item.ItemKey;
import fr.aang.gvote.utils.Utils;

public class TaskManager {
	
	private Main _main;
	private Map<String, BukkitTask>	_map = new HashMap<String, BukkitTask>();
	
	public TaskManager(Main main) {
		_main = main;
	}
	
	public void waitVote(Player player, WebServ serv, int serv_id) {
		
		if (_map.get(player.getName() + "_" + serv_id) != null) {
			_map.get(player.getName() + "_" + serv_id).cancel();
			_map.remove(player.getName() + "_" + serv_id);
		}
		VoteTask task = new VoteTask(_main, serv_id, _map, 5 * 6, player, serv.getUrlCheck(player.getAddress().getAddress().getHostAddress()), player.getName() + "_" + serv_id);
		_map.put(player.getName() + "_" + serv_id, task.runTaskTimer(_main, 0, 20 * 10));
	}
	
	public void giveGift(Player player) {
		
		ItemStack item = ItemKey.get();
		
		if (Utils.hasAvaliableSlot(player, 1)) {
			player.sendMessage("§a[⛃] Vous avez reçu §e" + item.getAmount() + " x §e" + item.getItemMeta().getDisplayName());
			player.getInventory().addItem(item);
		}
		else {
			giveTask(player, item);
		}
	}
	
	public void giveTask(Player player, ItemStack item) {
		
		 new BukkitRunnable() {
	        @Override
	        public void run() {
	        	    	
	        	if (Utils.hasAvaliableSlot(Bukkit.getPlayer(player.getName()), 1)) {
					player.getInventory().addItem(item);
					player.sendMessage("§a[⛃] Vous avez reçu §e" + item.getAmount() + " x §e" + item.getItemMeta().getDisplayName());
					cancel();
	        	}
	        	else {
	        		player.sendMessage("§c[⛃] §cVous avez §e1 récompense §cen attente, inventaire plein");
	        	}
	        }
		}.runTaskTimer(_main, 100, 20L);
		return ;
	}

}
