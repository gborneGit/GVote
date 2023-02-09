package fr.aang.gvote.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.aang.gvote.Main;
import fr.aang.gvote.utils.Utils;

public class Data {
	
	private Main				_main;
	private File				_file;
	private YamlConfiguration	_yaml;
	
	private Map<String, Vote>	_map = new HashMap<String, Vote>();
	
	public Data(Main main, String file_name) {
		_main = main;
		_yaml = loadConfig(file_name);
		readConfig();
	}
	
	private YamlConfiguration loadConfig(String file_name) {
		
		if(!_main.getDirectory().exists()) {
			_main.getDirectory().mkdir();
		}
		
		_file = new File(_main.getDataFolder(), file_name);
		
		if (!_file.exists()) {
			_main.saveResource(file_name, false);
		}
		
		return YamlConfiguration.loadConfiguration(_file);
	}
	
	public void save() {
        try {
        	_yaml.save(_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void	readConfig() {
		
		ConfigurationSection section;
		
		int i = 0;
		while((section = _yaml.getConfigurationSection("" + i)) != null) {
			String	player_name = section.getString("name");
			Vote	votes = new Vote();
			if (section.isSet("1"))
				votes.setVote1(Utils.stringToDate(section.getString("1")));
			if (section.isSet("2"))
				votes.setVote2(Utils.stringToDate(section.getString("2")));
			if (section.isSet("3"))
				votes.setVote3(Utils.stringToDate(section.getString("3")));
			_map.put(player_name, votes);
			i++;
		}
	}
	
	private ConfigurationSection getPlayer(String player_name) {
		
		ConfigurationSection section;
		
		int i = 0;
		while((section = _yaml.getConfigurationSection("" + i)) != null) {
			if (section.get("name").equals(player_name))
				return  section;
			i++;
		}
		_yaml.set(i + ".name", player_name);
		return _yaml.getConfigurationSection("" + i);
	}
	
	public void setVote(String player_name, int vote_id, int delay_min) {
		
		Vote votes = _map.get(player_name);
		if (votes == null) {
			votes = new Vote();
			if (vote_id == 1)
				votes.setVote1(Utils.getNextDate(delay_min));
			else if (vote_id == 2)
				votes.setVote2(Utils.getNextDate(delay_min));
			else if (vote_id == 3)
				votes.setVote3(Utils.getNextDate(delay_min));
			_map.put(player_name, votes);
		}
		else {
			if (vote_id == 1)
				votes.setVote1(Utils.getNextDate(delay_min));
			else if (vote_id == 2)
				votes.setVote2(Utils.getNextDate(delay_min));
			else if (vote_id == 3)
				votes.setVote3(Utils.getNextDate(delay_min));
		}
		ConfigurationSection section = getPlayer(player_name);
		section.set("" + vote_id, Utils.dateToString(Utils.getNextDate(delay_min)));
		save();
	}
	
	public Vote	getVotes(String player_name) {
		Vote votes = _map.get(player_name);
		if (votes == null)
			votes = new Vote();
		return votes;
	}

}
