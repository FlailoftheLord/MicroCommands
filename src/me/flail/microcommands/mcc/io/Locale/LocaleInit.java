package me.flail.microcommands.mcc.io.Locale;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;

public class LocaleInit extends FileManager {
	public static String f = "Locale.yml";

	private MicroCommands plugin = MicroCommands.instance;
	private ClassLoader loader = plugin.getClass().getClassLoader();

	public LocaleInit(MicroCommands plugin) {
		super(plugin);
	}

	public FileConfiguration locale() {
		if (new File(plugin.getDataFolder(), f).exists()) {
			return this.getFile(f);
		}
		return YamlConfiguration.loadConfiguration(new InputStreamReader(loader.getResourceAsStream(f)));
	}


	@Override
	public String getMessage(String value) {
		return this.readValue(value);
	}

	public String readValue(String value) {
		InputStream in = loader.getResourceAsStream(f);

		if (in != null) {
			try {
				File locale = new File(plugin.getDataFolder(), f);

				if (!locale.exists()) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					BufferedWriter writer = new BufferedWriter(new FileWriter(locale));

					for (String line : reader.lines().toArray(String[]::new)) {
						writer.append(line + "\r\n");
					}

					reader.close();
					writer.close();
				}
				FileConfiguration localeConf = YamlConfiguration.loadConfiguration(locale);

				return localeConf.get(Pattern.quote("(?i)") + value, "Invalid Value").toString();
			} catch (Exception e) {
				e.printStackTrace();
				return "An invalid file engraced itself on us!";
			}
		}
		return "Invalid Locale file.";
	}

}
