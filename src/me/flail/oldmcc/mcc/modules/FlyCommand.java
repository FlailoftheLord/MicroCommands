package me.flail.oldmcc.mcc.modules;

import me.flail.oldmcc.mcc.MicroCommands;

public class FlyCommand {
	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	public void fly() {
		plugin.isEnabled();
	}

}
