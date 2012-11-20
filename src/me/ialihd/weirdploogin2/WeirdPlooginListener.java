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

	public void playerEnchant(EnchantItemEvent e) {

	}

	@EventHandler
	public void onPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
		String playerName = e.getName();
		Result result = AsyncPlayerPreLoginEvent.Result.KICK_FULL;
		String message = playerName + " " + config.config.getString("message.ban");
		e.disallow(result, message);
		e.setLoginResult(result);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType() == Material.WEB) {
			Block block = player.getTargetBlock(null, 30);
			Location loc = block.getLocation().add(0, 1, 0);
			loc.getBlock().setType(Material.WEB);
			if (player.getGameMode() != GameMode.CREATIVE) {
				ItemStack web = player.getItemInHand();
				ItemStack newWeb = new ItemStack(Material.WEB, web.getAmount() - 1);
				player.setItemInHand(newWeb);
			}
		}
	}
}
