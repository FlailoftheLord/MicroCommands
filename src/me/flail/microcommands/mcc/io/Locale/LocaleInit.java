package me.flail.microcommands.mcc.io.Locale;

import java.io.DataInputStream;

import org.bukkit.configuration.file.FileConfiguration;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;

public class LocaleInit extends FileManager {
	static String f = "Locale.yml";

	MicroCommands plugin = MicroCommands.instance;

	public LocaleInit(MicroCommands plugin) {
		super(plugin);
	}

	public FileConfiguration locale() {
		return this.getFile(f);
	}


	@Override
	public String getMessage(String value) {
		return this.readValue(value);
	}

	public String readValue(String value) {
		DataInputStream in = (DataInputStream) plugin.getResource("Locale.txt");

		if (in != null) {




			boolean isMultiline = false;
			String multiLineCombo = "";



		}
		return "Not working at all...";
	}

}