package me.flail.microcommands.mcc.tools;

import org.bukkit.ChatColor;

import me.flail.microcommands.MicroCommands;

public class ChatUtils {
	MicroCommands plugin;

	public ChatUtils(MicroCommands inst) {
		plugin = inst;
	}

	public String getMessage(String path) {
		return plugin.fileManager.getMessage(path);
	}

	public String chat(String message) {
		String prefix = plugin.fileManager.getFile(plugin, "Messages.yml").get("Prefix", "&8(&2&lMicroCommands&8)")
				.toString();

		return ChatColor.translateAlternateColorCodes('&',
				message.replace("$prefix$", prefix).replace("$version$", plugin.version));
	}

}
