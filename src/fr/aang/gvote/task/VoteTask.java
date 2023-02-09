package fr.aang.gvote.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.aang.gvote.Main;

public class VoteTask extends BukkitRunnable {

	private Main					_main;
	
	private int						_serv_id;
	private int 					_timer;
	private Player 					_player;
	private Map<String, BukkitTask>	_map;
	private	String					_string_url;
	private	String					_task_name;
	
	
	public VoteTask(Main main, int serv_id, Map<String, BukkitTask> map, int timer, Player player, String string_url, String task_name) {
		_main = main;
		_serv_id = serv_id;
		_string_url = string_url;
		_task_name = task_name;
		_timer = timer;
		_player = player;
		_map = map;
	}
	
	private String getResponse(String string_url) {
		try {
			URL url = new URL(string_url);
			String response = new BufferedReader(new InputStreamReader(url.openStream())).readLine();
			return response;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean isVote(String string_url) {
		
		String response = getResponse(string_url);

		if (response != null && response.length() > 0) {
			if (response.substring(0, 1).equals("0")) {
				return false;
			}
			else if (response.substring(0, 1).equals("1")) {
				return true;
			}
		}
		System.out.println(ChatColor.RED + "[Gvote] ERROR");
		System.out.println(ChatColor.RED + "[Gvote] URL : <" + string_url + ">");
		System.out.println(ChatColor.RED + "[Gvote] Response : <" + response + ">");
		return false;
	}
	
	@Override
	public void run() {
		
		if (_timer <= 0) {
			_map.remove(_task_name);
			_player.sendMessage("§b[❤] §cLien de vote expiré");
			cancel();
		}
		else if (isVote( _string_url)) {
			_main.data.setVote(_player.getName(), _serv_id + 1, _main.config.getWebServ(_serv_id).getDelay());
			_map.remove(_task_name);
			System.out.println(ChatColor.GOLD + "[Gvote] " + ChatColor.WHITE + _player.getName() + ChatColor.GOLD + " viens de voter");
			_main.task.giveGift(_player);
			cancel();
		}
		_timer--;
	}

}
