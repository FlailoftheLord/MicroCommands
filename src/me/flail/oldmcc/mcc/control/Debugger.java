package me.flail.oldmcc.mcc.control;

import me.flail.oldmcc.mcc.MicroCommands;
import me.flail.tools.Logger;
import me.flail.tools.Message;

public class Debugger extends Logger {

	MicroCommands plugin = MicroCommands.instance;

	public void run() {

		console(new Message("Test").string());

	}

}
