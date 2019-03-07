package me.flail.microcommands.mcc.commands;

import java.util.List;

import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import me.flail.microcommands.MicroCommands;

public class CommandCheck {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	private Server server = plugin.getServer();

	public boolean isCommandTaken(String cmd) {

		boolean isTaken = false;

		PluginCommand command = server.getPluginCommand(cmd);

		if (command != null) {

			Plugin commandOwner = command.getPlugin();

			if (!commandOwner.getDescription().getMain().contains("me.flail.microcommands")) {
				isTaken = true;
			}

		}

		return isTaken;

	}

	public boolean isDisabled(String cmd) {

		boolean isDisabled = false;

		PluginCommand command = plugin.getCommand(cmd);

		List<String> aliases = command.getAliases();

		List<String> disabledCmds = plugin.getConfig().getStringList("DisabledCommands");

		for (String s : aliases) {
			for (String c : disabledCmds) {

				if (c.equalsIgnoreCase(s)) {
					isDisabled = true;
					break;
				}

			}
		}

		return isDisabled;

	}

}
