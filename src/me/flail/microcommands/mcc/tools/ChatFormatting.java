package me.flail.microcommands.mcc.tools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.tools.Logger;

public class ChatFormatting extends Logger {

	private MicroCommands plugin = JavaPlugin.getPlugin(MicroCommands.class);

	public String chatFormat(Player player) {

		String format = plugin.config.get("ChatFormat").toString();

		return format.replace("$player$", "%1$s").replace("$message$", "%2$s");

	}

	public String chatColor(String message, String type) {

		switch (type) {

		case "magic":

			return this.convertColorCodes(message.replace("&k", "~k"));
		case "color":

			String[] colorCodes = new String[] { "a", "b", "c", "d", "e", "f", "r", "0", "1", "2", "3", "4", "5", "6",
					"7", "8", "9" };
			String newMessage = message;

			for (String c : colorCodes) {
				newMessage = this.convertColorCodes(newMessage.replace("&" + c, "~" + c));
			}

			return newMessage;
		case "format":

			return this.convertColorCodes(message.replace("&r", "~r").replace("&n", "~n").replace("&m", "~m")
					.replace("&o", "~o").replace("&l", "~l"));
		}

		return "";
	}

	public String convertColorCodes(String message) {
		return ChatColor.translateAlternateColorCodes('~', message);

	}

}
