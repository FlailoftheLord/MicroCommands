package me.flail.microcommands.lang.Locale;

import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.Locale.LocaleInit;
import me.flail.microcommands.mcc.tools.ChatUtils;

public class Locale {
	MicroCommands plugin = MicroCommands.instance;
	private LocaleInit localeInit = new LocaleInit(plugin);

	public Locale() {
	}

	/**
	 * Gets the message corresponding to the specified value.
	 * 
	 * @param ID
	 *               The value of said message to get.
	 * @return the string from the locale file.
	 */
	public String message(String ID) {
		FileConfiguration locale = localeInit.locale();

		return locale.get(Pattern.quote("(?i)") + ID, localeInit.getMessage(ID)).toString();
	}

	/**
	 * Gets the message corresponding to the specified value.
	 * 
	 * @param ID
	 *                   The value of said message to get.
	 * @param format
	 *                   Wether the plugin should automatically translate chat codes (color+format)
	 * @return the string, formatted or not, from the locale file.
	 */
	public String message(String ID, boolean format) {
		if (format) {
			ChatUtils chat = new ChatUtils();

			return chat.chat(this.message(ID));
		}
		return this.message(ID);
	}

}