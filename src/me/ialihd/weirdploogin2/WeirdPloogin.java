package me.ialihd.weirdploogin2;

import java.util.HashMap;
import java.util.logging.Logger;

import me.ialihd.weirdploogin2.commands.Enchant;
import me.ialihd.weirdploogin2.configuration.Config;
import me.ialihd.weirdploogin2.schedulers.AntiSpamScheduler;
import me.ialihd.weirdploogin2.util.ChatColors;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WeirdPloogin extends JavaPlugin{

	public final Logger log = Bukkit.getLogger();
	public static WeirdPloogin plugin;
	public static Config config;
	private static ChatColors c = new ChatColors();
	public final static HashMap<String, Integer> spamMessages = new HashMap<String, Integer>();	
	
	public boolean freeze = false;
	
	public final WeirdPlooginChatListener chatL = new WeirdPlooginChatListener(this);
	
	public void onEnable(){
		PluginDescriptionFile pdf = getDescription();
		Bukkit.broadcastMessage(c.gold + pdf.getName() + c.yellow + " v." + pdf.getVersion() + c.green + " successfully enabled!");
		plugin = this;
		initialize();
//		if(!Config.load()){
//			Bukkit.getPluginManager().disablePlugin(this);
//			log.warning("Config cannot load");
//		}
	}
	public void onDisable(){
		PluginDescriptionFile pdf = getDescription();
		Bukkit.broadcastMessage(c.gold + pdf.getName() + c.yellow + " v." + pdf.getVersion() + c.red + " successfully disabled!");
	}
	
	public static Config getCustomConfig(){
		return config;
	}
	public void initialize(){
		config = new Config();
		config.initialize();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new WeirdPlooginChatListener(this), this);
		pm.registerEvents(new WeirdPlooginListener(this), this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AntiSpamScheduler(), 0, 5*20L);
		getMyCommands();
	}

	private void getMyCommands() {
		getCommand("enchant").setExecutor(new Enchant());
	}

	
}
