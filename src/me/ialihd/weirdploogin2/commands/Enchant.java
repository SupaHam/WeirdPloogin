package me.ialihd.weirdploogin2.commands;

import me.ialihd.weirdploogin2.WeirdPloogin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enchant implements CommandExecutor {
	WeirdPloogin plugin;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		player.openEnchanting(null, true);
		return false;
	}
	
	public int getEnchantmentBonus(){
		return 30;
	}

}
