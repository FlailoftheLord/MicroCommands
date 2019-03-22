package me.flail.microcommands.mcc.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;

import me.flail.microcommands.mcc.MicroCommands;

public class LocaleInit extends FileManager {

	MicroCommands plugin = MicroCommands.instance;

	public LocaleInit(MicroCommands plugin) {
		super(plugin);
	}

	public FileConfiguration locale() {
		return this.getFile("Locale.yml");
	}

	@Override
	public String getMessage(String value) {
		return this.readValue(value);
	}

	protected String readValue(String value) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(plugin.getResource("messages.txt")));
		boolean isMultiline = false;
		String multiLineCombo = "";

		for (Object object : reader.lines().toArray()) {
			String s = object.toString();
			if (s.startsWith(Pattern.quote("(?i)") + value + ":")) {
				if (s.endsWith("|-")) {
					isMultiline = true;
					continue;
				}
				if (isMultiline) {
					if (!s.contains(":")) {
						multiLineCombo.concat(s + "\n");
						continue;
					} else {
						isMultiline = false;
						return multiLineCombo;
					}

				}

				return s.substring(s.indexOf(":"));
			}
		}

		return "Undefined";
	}

}
