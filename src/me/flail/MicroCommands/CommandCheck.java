package me.flail.MicroCommands;

import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

public class CommandCheck {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	private Server server = plugin.getServer();

	public boolean isCommandTaken(String cmd) {

		boolean isTaken = false;

		PluginCommand command = server.getPluginCommand(cmd);

		if (command != null) {

			Plugin commandOwner = command.getPlugin();

			if (!commandOwner.getName().equalsIgnoreCase("MicroCommands")) {
				isTaken = true;
			}

		}

		return isTaken;

	}

}
