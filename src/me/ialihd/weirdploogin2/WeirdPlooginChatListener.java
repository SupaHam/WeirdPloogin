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

	// Fires whenever a player types a message in-game
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		// Player involved in the Chat Event
		Player player = e.getPlayer();
		// Player's name
		String playerName = player.getName();
		// World the player is in
		World world = player.getWorld();
		// Message the player sent
		String msg = e.getMessage();
		// Adding a custom command where if it starts with a '!' and ends with a
		// 'd'
		if (msg.startsWith("!") && msg.endsWith("d")) {
			// Getting the ticks to set as from the config file
			world.setTime((int) config.config.getDouble("time.day"));
			// Canceling the message "!d" from being sent
			e.setCancelled(true);
			// Broadcast the action performed by the player
			Bukkit.broadcastMessage(c.gold + "" + c.italic + player.getName() + c.reset + c.yellow + " has set '" + c.aqua + world.getName() + c.yellow + "' "
					+ (int) config.config.getDouble("time.day") + " ticks!");
		}
		// Adding a custom command where if it starts with a '!' and ends with a
		// 'r'
		if (msg.startsWith("!") && msg.endsWith("r")) {
			// Checking if the world is currently raining
			if (world.hasStorm()) {
				// Turning the rain off
				world.setStorm(false);
				// Canceling the message "!r" from being sent
				e.setCancelled(true);
				// Broadcast the action performed by the player
				Bukkit.broadcastMessage(c.gold + "" + c.italic + player.getName() + c.reset + c.yellow + " has set '" + c.aqua + world.getName() + c.yellow + "' to sunny!");

			} else {
				// Turning the rain on
				world.setStorm(true);
				// Canceling the message "!r" from being sent
				e.setCancelled(true);
				// Broadcast the action performed by the player
				Bukkit.broadcastMessage(c.gold + "" + c.italic + player.getName() + c.reset + c.yellow + " has set '" + c.aqua + world.getName() + c.yellow + "' to storm!");
			}
		}
		// Adding a custom command where if it starts with a '!' and ends with a
		// 'f'
		if (msg.startsWith("!") && msg.endsWith("f")) {
			// Checking freeze boolean
			if (plugin.freeze == false) {
				// Setting the freeze boolean to true
				plugin.freeze = true;
				// Canceling the message "!f" from being sent
				e.setCancelled(true);
			} else {
				// Setting the freeze boolean to false
				plugin.freeze = false;
				// Canceling the message "!f" from being sent
				e.setCancelled(true);
			}
		}
		// Checking if the HashMap spamMessages contains the String(player's
		// name) and if it stored it 3 times
		if (WeirdPloogin.spamMessages.containsKey(playerName) && WeirdPloogin.spamMessages.get(playerName).equals(3)) {
			// Cancel the spammer's message from being sent
			e.setCancelled(true);
			// Send the spammer a message warning him
			e.getPlayer().sendMessage(c.red + "Please don't spam.");
			return;
		}
		// The amount of messages a player has sent
		int messageCount = 0;
		// Checking if the HashMap spamMessages contains the String(player's
		// name)
		if (WeirdPloogin.spamMessages.containsKey(playerName)) {
			// Defining the variable messageCount to the player's name
			messageCount = WeirdPloogin.spamMessages.get(playerName);
		}
		// Add the players name to the HashMap spamMessages once every message
		WeirdPloogin.spamMessages.put(playerName, messageCount + 1);
	}

}
