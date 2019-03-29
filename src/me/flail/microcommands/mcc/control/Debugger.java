package me.flail.microcommands.mcc.control;

import me.flail.microcommands.io.Logger.LogType;
import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.Locale.LocaleInit;

public class Debugger {

	MicroCommands plugin = MicroCommands.instance;

	public void run() {
		LocaleInit locale = new LocaleInit(plugin);

		plugin.logger.log(locale.readValue("Test"), LogType.CONSOLE);

	}

}
