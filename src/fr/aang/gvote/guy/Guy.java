package fr.aang.gvote.guy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.aang.gvote.Main;
import fr.aang.gvote.config.Vote;
import fr.aang.gvote.utils.Utils;

public class Guy {
	
	private Main _main;
	
	public Guy(Main main) {
		_main = main;
	}
	
	private ItemStack	getVoteItem(Player player, Date next_vote, int serv_id) {
		
		List<String> lore = new ArrayList<String>();
		lore.add("§7" + _main.config.getWebServ(serv_id).getName());
		if (next_vote == null || next_vote.before(Utils.getCurrentDate())) {
			lore.add("§e(●) §aVoter");
			return Utils.addEnchantEffect(Utils.getItem(Material.FEATHER, "§r§6Vote " + (serv_id + 1), lore));
		}
		else {
			lore.add("§7▪ §c" + Utils.getEquartDate(Utils.getCurrentDate(), next_vote));
			return Utils.getItem(Material.FEATHER, "§r§6Vote " + (serv_id + 1), lore);
		}
	}

	public void	openGuy(Player player) {
		
		Inventory	inv = Bukkit.createInventory(null, 9, "§8Vote");
		Vote		votes = _main.data.getVotes(player.getName());

		inv.setItem(3, getVoteItem(player, votes.getVote1(), 0));
		inv.setItem(4, getVoteItem(player, votes.getVote2(), 1));
		inv.setItem(5, getVoteItem(player, votes.getVote3(), 2));
			
		inv.setItem(8, Utils.getItem(Material.BARRIER, "§e(●) §cRetour", null));

		player.openInventory(inv);
	}
}
