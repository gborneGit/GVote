package fr.aang.gvote.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.aang.gvote.Main;

public class Config {
	
	private Main				_main;
	private File				_file;
	private YamlConfiguration	_yaml;
	
	private List<WebServ>		_webserv = new ArrayList<WebServ>();
	
	public Config(Main main, String file_name) {
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
	
	private void	readConfig() {
		
		ConfigurationSection section;
		
		int i = 0;
		while ((section = _yaml.getConfigurationSection("list." + i)) != null) {
			WebServ serv = new WebServ(section.getInt("server_id"));
			serv.setName(section.getString("name"));
			serv.setDelay(section.getInt("delay"));
			serv.setUrlVote(section.getString("url_vote"));
			serv.setUrlCheck(section.getString("url_check"));
			_webserv.add(serv);
			i++;
		}
		
	}
	
	// GETTERS
	
	public WebServ	getWebServ(int id) {
		if (id < 0 || id >= _webserv.size())
			return null;
		return _webserv.get(id);
	}

}
