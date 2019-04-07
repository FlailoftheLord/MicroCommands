package me.flail.microcommands.mcc.commands.modules;

import me.flail.microcommands.mcc.MicroCommands;

public class FlyCommand {
	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	public void fly() {
		plugin.isEnabled();
	}

}
