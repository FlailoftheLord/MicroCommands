package me.flail.microcommands.mcc.tools;

import java.util.regex.Pattern;

import org.bukkit.ChatColor;

import me.flail.microcommands.mcc.MicroCommands;

public class ChatUtils {
	MicroCommands plugin = MicroCommands.instance;

	public String getMessage(String path) {
		return plugin.fileManager.getMessage(path);
	}

	public String getMessage(String path, String defaultValue) {
		return plugin.fileManager.getFile("Messages.yml").get(path, defaultValue).toString();
	}

	public String chat(String message) {
		String prefix = plugin.fileManager.getFile("Messages.yml").get("Prefix", "&8(&2&lMicroCommands&8)").toString();

		return ChatColor.translateAlternateColorCodes('&',
				message.replace("$prefix$", prefix).replace("$version$", plugin.version));
	}

	public String wordToNumber(String value) {
		String[] numberPrefixes = {
				"zero:0", "ty :0", "teen:0",
				"eleven:11", "one:1",
				"twen:2", "two:2",
				"thir:3", "three:3",
				"four:4",
				"five:5", "fif:5", "fifty-" + Pattern.quote("[a-zA-Z]") + ":5"

		};

		for (String s : numberPrefixes) {

			String name = s.split(":")[0];
			String integer = s.split(":")[1];

			value = value.replace(Pattern.quote("(?i)") + name, integer);
		}

		return value;
	}

}
