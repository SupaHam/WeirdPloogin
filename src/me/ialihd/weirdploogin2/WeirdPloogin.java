package me.ialihd.weirdploogin2;

import java.util.HashMap;
import java.util.logging.Logger;

import me.ialihd.weirdploogin2.configuration.Config;
import me.ialihd.weirdploogin2.schedulers.AntiSpamScheduler;
import me.ialihd.weirdploogin2.util.ChatColors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WeirdPloogin extends JavaPlugin {
	// Getting Logger to send messages to console
	public final Logger log = Bukkit.getLogger();

	public static WeirdPloogin plugin;

	public static Config config;

	private static ChatColors c = new ChatColors();
	// HashMap that stores a String(player's name) and an Integer(number of
	// messages) to prevent spam
	public final static HashMap<String, Integer> spamMessages = new HashMap<String, Integer>();

	// Freeze players
	public boolean freeze = false;

	public final WeirdPlooginChatListener chatL = new WeirdPlooginChatListener(this);

	// Fires on plugin enable
	public void onEnable() {
		PluginDescriptionFile pdf = getDescription();
		Bukkit.broadcastMessage(c.gold + pdf.getName() + c.yellow + " v." + pdf.getVersion() + c.green + " successfully enabled!");
		plugin = this;
		initialize();
		// if(!Config.load()){
		// Bukkit.getPluginManager().disablePlugin(this);
		// log.warning("Config cannot load");
		// }
	}

	// Fires on plugin disable
	public void onDisable() {
		PluginDescriptionFile pdf = getDescription();
		Bukkit.broadcastMessage(c.gold + pdf.getName() + c.yellow + " v." + pdf.getVersion() + c.red + " successfully disabled!");
	}

	// Getting my custom config
	public static Config getCustomConfig() {
		return config;
	}

	// Things to be initialized on plugin enable
	public void initialize() {
		config = new Config();
		config.initialize();
		PluginManager pm = Bukkit.getPluginManager();
		// Register all the events I created in other classes
		pm.registerEvents(new WeirdPlooginChatListener(this), this);
		pm.registerEvents(new WeirdPlooginListener(this), this);
		// Every 100 ticks clear the HashMap spamMessages
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AntiSpamScheduler(), 0, 5 * 20L);
		getMyCommands();
	}

	// Getting commands
	private void getMyCommands() {

	}

	// To be fired when the player does not have permission
	public void denied(CommandSender sender) {
		sender.sendMessage(c.red + "No permission!");
	}

}
