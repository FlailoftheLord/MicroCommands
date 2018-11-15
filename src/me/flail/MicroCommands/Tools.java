package me.flail.MicroCommands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

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

	public String msg(String s, String sender, String player) {

		FileConfiguration messages = plugin.getMessages();
		String prefix = messages.getString("Prefix");
		String errorPrefix = messages.getString("Errors.Prefix");

		String result = ChatColor.translateAlternateColorCodes('&', s.replace("$prefix$", prefix)
				.replace("$error$", errorPrefix).replace("$sender$", sender).replace("$player$", player));
		return result;

	}

	public String cmd(String s, String sender, String target, String label) {

		FileConfiguration messages = plugin.getMessages();
		String prefix = messages.getString("Prefix");
		String errorPrefix = messages.getString("Errors.Prefix");

		String result = ChatColor.translateAlternateColorCodes('&',
				s.replace("$prefix$", prefix).replace("$error$", errorPrefix).replace("$player$", target)
						.replace("$sender$", sender).replace("$command$", label));
		return result;

	}

}
