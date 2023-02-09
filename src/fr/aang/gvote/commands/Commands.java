package fr.aang.gvote.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aang.gvote.Main;
import fr.aang.gvote.item.ItemKey;

public class Commands implements CommandExecutor {
	
	private Main	_main;
	
	public Commands(Main main) {
		_main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		
		if (!(sender instanceof Player)) {
			
			if (args.length == 1) {
				
				Player player = Bukkit.getPlayer(args[0]);
				
				if (player != null) {
					_main.guy.openGuy(player);
					return true;
				}
			}
		}
		else {
			if (args.length == 0) {
				
				Player player = (Player)sender;
				
				if (player.hasPermission("gvote.use")) {
					player.getInventory().addItem(ItemKey.get());
				}
			}
		}
		return false;
	}

}
