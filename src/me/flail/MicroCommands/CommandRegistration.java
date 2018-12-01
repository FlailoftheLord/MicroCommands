package me.flail.MicroCommands;

import java.util.Set;

import org.bukkit.command.Command;

public class CommandRegistration {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	public void registerCommands() {

		Set<String> commands = plugin.getDescription().getCommands().keySet();

		for (String cmd : commands) {

			Command command = plugin.getCommand(cmd);

		}

	}

}
