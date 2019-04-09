package me.flail.microcommands.mcc.permissions;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.commands.MicroCommand;

public class Permission {
	private String node;

	MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	MicroCommand mcc = new MicroCommand();

	public Permission(String command) {
		node = mcc.getCommand(command).getPermission();
	}

	public String node() {
		return node;
	}

}
