package me.flail.microcommands.modules;

import org.bukkit.Server;

import me.flail.microcommands.mcc.MicroCommands;

public class MicroServer {

	protected MicroCommands plugin;

	public MicroServer(MicroCommands plugin) {
		this.plugin = plugin;
	}

	public Server get() {
		return plugin.getServer();
	}

}
