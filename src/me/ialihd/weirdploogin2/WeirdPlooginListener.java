package me.ialihd.weirdploogin2;

import me.ialihd.weirdploogin2.configuration.Config;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WeirdPlooginListener implements Listener {

	public static WeirdPloogin plugin = WeirdPloogin.plugin;
	private static Config config = new Config();

	public WeirdPlooginListener(WeirdPloogin instance) {
		plugin = instance;
	}

//
//	@EventHandler
//	public void onPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
//		String playerName = e.getName();
//		Result result = AsyncPlayerPreLoginEvent.Result.KICK_FULL;
//		String message = playerName + " " + config.config.getString("message.ban");
//		e.disallow(result, message);
//		e.setLoginResult(result);
//	}

	// Fired when a player right clicks or left clicks
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		// Get the player involved in this event
		Player player = e.getPlayer();
		// Checking If the player is holding an item in his still
		if (player.getItemInHand().getType() == Material.STICK) {
			//Defining block as to whatever the block the player is targeting within the range of 30 and don't ignore any blocks
			Block block = player.getTargetBlock(null, 30);
			//Get the variable block 's location
			Location loc = block.getLocation();
			//Create an explosion at the block in the world where the player was aiming
			loc.getBlock().getWorld().createExplosion(loc, 4F);
		}
	}
}
