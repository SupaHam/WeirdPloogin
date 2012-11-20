package me.ialihd.weirdploogin2;

import me.ialihd.weirdploogin2.configuration.Config;
import me.ialihd.weirdploogin2.util.ChatColors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WeirdPlooginChatListener implements Listener {

	WeirdPloogin plugin;
	private static Config config = new Config();
	private static ChatColors c = new ChatColors();

	public WeirdPlooginChatListener(WeirdPloogin instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		World world = player.getWorld();
		String msg = e.getMessage();
		if (msg.startsWith("!") && msg.endsWith("d")) {
			world.setTime((int) config.config.getDouble("time.day"));
			e.setCancelled(true);
			Bukkit.broadcastMessage(c.gold + "" + c.italic + player.getName() + c.reset + c.yellow + " has set '" + c.aqua + world.getName() + c.yellow + "' "
					+ (int) config.config.getDouble("time.day") + " ticks!");
		}
		if(msg.startsWith("!") && msg.endsWith("r")){
			if(world.hasStorm()){
				world.setStorm(false);
				Bukkit.broadcastMessage(c.gold + "" + c.italic + player.getName() + c.reset + c.yellow + " has set '" + c.aqua + world.getName() + c.yellow + "' to sunny!");
				e.setCancelled(true);
			}else{
				world.setStorm(true);
				Bukkit.broadcastMessage(c.gold + "" + c.italic + player.getName() + c.reset + c.yellow + " has set '" + c.aqua + world.getName() + c.yellow + "' to storm!");
				e.setCancelled(true);
			}
		}
		if(msg.startsWith("!") && msg.endsWith("f")){
			if(plugin.freeze == false){
				plugin.freeze = true;
				e.setCancelled(true);
			}else{
				plugin.freeze = false;
				e.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void spamChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String playerName = player.getName();
		if (WeirdPloogin.spamMessages.containsKey(playerName) && WeirdPloogin.spamMessages.get(playerName).equals(3)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(c.red + "Please don't spam.");
			return;
		}
		int messageCount = 0;
		if (WeirdPloogin.spamMessages.containsKey(playerName)) {
			messageCount = WeirdPloogin.spamMessages.get(playerName);
		}
		WeirdPloogin.spamMessages.put(playerName, messageCount + 1);
	}

}
