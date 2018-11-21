package me.flail.MicroCommands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Tools {

	private MicroCommands plugin;

	public Tools(MicroCommands plugin) {
		this.plugin = plugin;
	}

	public String chat(String s) {

		FileConfiguration messages = plugin.getMessages();
		String prefix = messages.getString("Prefix");
		String errorPrefix = messages.getString("Errors.Prefix");

		String result;

		if ((prefix != null) && (errorPrefix != null)) {
			result = ChatColor.translateAlternateColorCodes('&',
					s.replace("$prefix$", prefix).replace("$error$", errorPrefix));
		} else {
			result = ChatColor.translateAlternateColorCodes('&', s);
		}
		return result;

	}

	public String msg(String s, Player target, Player sender, String label, String permission) {

		FileConfiguration messages = plugin.getMessages();
		String prefix = messages.getString("Prefix");
		String errorPrefix = messages.getString("Errors.Prefix");

		String result = "";

		if ((target != null) && (sender != null)) {

			String targetName = target.getName();
			String targetX = target.getLocation().getBlockX() + "";
			String targetY = target.getLocation().getBlockY() + "";
			String targetZ = target.getLocation().getBlockZ() + "";
			String targetWorld = target.getLocation().getWorld().getName();
			String targetHealth = target.getHealth() + "";
			String targetNick = target.getDisplayName();

			String senderName = sender.getName();
			String senderX = sender.getLocation().getBlockX() + "";
			String senderY = sender.getLocation().getBlockY() + "";
			String senderZ = sender.getLocation().getBlockZ() + "";
			String senderWorld = sender.getLocation().getWorld().getName();
			String senderHealth = sender.getHealth() + "";
			String senderNick = sender.getDisplayName();

			result = ChatColor.translateAlternateColorCodes('&',
					s.replaceAll("$player$", targetName).replaceAll("$sender$", senderName)
							.replaceAll("$target-x$", targetX).replaceAll("$target-y$", targetY)
							.replaceAll("$target-z$", targetZ).replaceAll("$sender-x$", senderX)
							.replaceAll("$sender-y$", senderY).replaceAll("$sender-z$", senderZ)
							.replaceAll("$target-world$", targetWorld).replaceAll("$sender-world$", senderWorld)
							.replaceAll("$target-health$", targetHealth).replaceAll("$sender-health$", senderHealth)
							.replaceAll("$target-nickname$", targetNick).replaceAll("$displayname$", targetNick)
							.replaceAll("$sender-nickname$", senderNick).replaceAll("$command$", label)
							.replaceAll("$permission$", permission).replaceAll("$prefix$", prefix)
							.replaceAll("$error$", errorPrefix));

		} else {

			result = ChatColor.translateAlternateColorCodes('&',
					s.replaceAll("$command$", label).replaceAll("$permission$", permission)
							.replaceAll("$prefix$", prefix).replaceAll("$error$", errorPrefix));

		}
		return result;

	}

}
