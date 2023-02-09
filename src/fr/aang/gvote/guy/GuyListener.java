package fr.aang.gvote.guy;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.aang.gvote.Main;
import fr.aang.gvote.config.Vote;
import fr.aang.gvote.utils.Utils;

public class GuyListener implements Listener {
	
	private Main _main;
	
	public GuyListener(Main main) {
		_main = main;
	}
	
	private void sendMessage(Player player, Date next_vote, int serv_id) {
		
		if (next_vote == null || next_vote.before(Utils.getCurrentDate())) {
			
			_main.task.waitVote(player, _main.config.getWebServ(serv_id), serv_id);
			player.sendMessage("§b[❤] §b" + _main.config.getWebServ(serv_id).getUrlVote(player.getName()));
		}
		else {
			player.sendMessage("§b[❤] §cRevenez dans " + Utils.getEquartDate(Utils.getCurrentDate(), next_vote));
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if (current != null && current.getType() != null) {
				
			if (event.getView().getTitle().equals("§8Vote")) {
				
				event.setCancelled(true);
				player.closeInventory();
				player.updateInventory();
				
				Vote	votes = _main.data.getVotes(player.getName());
				
				if (event.getRawSlot() == 3) {
					sendMessage(player, votes.getVote1(), 0);
				}
				else if (event.getRawSlot() == 4) {
					sendMessage(player, votes.getVote2(), 1);
				}
				else if (event.getRawSlot() == 5) {
					sendMessage(player, votes.getVote3(), 2);
				}
				else if (event.getRawSlot() == 8) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "menu " + player.getName());
				}
			}
		}
	}

}
