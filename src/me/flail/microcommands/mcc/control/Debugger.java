package me.flail.microcommands.mcc.control;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.Locale.Message;
import me.flail.tools.Logger;

public class Debugger extends Logger {

	MicroCommands plugin = MicroCommands.instance;

	public void run() {

		console(new Message("Test").string());

	}

}
