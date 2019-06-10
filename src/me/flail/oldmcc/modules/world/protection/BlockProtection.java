package me.flail.oldmcc.modules.world.protection;

import me.flail.oldmcc.mcc.MicroCommands;

public class BlockProtection {
	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	public void run() {
		plugin.isEnabled();
	}



}
