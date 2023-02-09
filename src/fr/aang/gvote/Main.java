package fr.aang.gvote;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import fr.aang.gvote.commands.Commands;
import fr.aang.gvote.config.Config;
import fr.aang.gvote.config.Data;
import fr.aang.gvote.guy.Guy;
import fr.aang.gvote.guy.GuyListener;
import fr.aang.gvote.task.TaskManager;

public class Main extends JavaPlugin {
	
	public Guy			guy;
	public Config		config;
	public Data			data;
	public TaskManager	task;

	@Override
	public void onEnable() {
		
		this.guy = new Guy(this);
		this.config = new Config(this, "config.yml");
		this.data = new Data(this, "data.yml");
		this.task = new TaskManager(this);
		
		getServer().getPluginManager().registerEvents(new GuyListener(this), this);
		getCommand("vote").setExecutor(new Commands(this));
	}
	
	public File getDirectory() {
		return getDataFolder();
	}
}
