package me.flail.microcommands.mcc.commands;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

public class MicroCommand {

	public TabCompleter tab(PluginCommand command) {

		TabCompleter completer = command.getTabCompleter();

		return completer;
	}

	public void suggest() {

	}

}
