package me.flail.MicroCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandProcessor {

	private MicroCommands plugin;

	public void processCommand(CommandSender cmdSender, String command, String[] args) {
		plugin = JavaPlugin.getPlugin(MicroCommands.class);

	}

}
