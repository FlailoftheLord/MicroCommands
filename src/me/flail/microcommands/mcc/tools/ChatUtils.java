package me.flail.microcommands.mcc.tools;

import java.util.regex.Pattern;

import org.bukkit.ChatColor;

import me.flail.microcommands.mcc.MicroCommands;

public class ChatUtils {
	MicroCommands plugin = MicroCommands.instance;
	public static char codeChar = '&';

	public ChatUtils() {
		plugin = MicroCommands.instance;
		codeChar = plugin.fileManager.getFile("config").get("ChatCodeChar", "&").toString().replaceAll(" &[a-fA-Fk-oK-O0-9]", "")
				.charAt(0);
	}

	public String getMessage(String path) {
		return plugin.fileManager.getMessage(path);
	}

	public String chat(String message) {
		String prefix = plugin.fileManager.getMessage("Prefix");

		return ChatColor.translateAlternateColorCodes(codeChar,
				message.replace("$prefix$", prefix).replace("$version$", plugin.version));
	}

	public String wordToNumber(String value) {
		String[] numberPrefixes = {
				"zero:0", "ty :0", "teen:0",
				"eleven:11", "one:1", "ten:10",
				"twen:2", "two:2",
				"thir:3", "three:3",
				"four:4",
				"five:5", "fif:5",
				"six:6",
				"seven:7",
				"eight:8", "eigh:8",
				"nine:9"

		};

		for (String s : numberPrefixes) {

			String name = s.split(":")[0];
			String integer = s.split(":")[1];

			value = value.replaceAll(Pattern.quote("(?i)") + name, integer);
		}

		return value.replaceAll("[^0-9]", "");
	}

}
