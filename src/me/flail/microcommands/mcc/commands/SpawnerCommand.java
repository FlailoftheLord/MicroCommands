package me.flail.microcommands.mcc.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.microcommands.mcc.MicroCommands;

@SuppressWarnings("unused")
public class SpawnerCommand {
	public Command cmd;
	public Player operator = null;
	public String[] args = new String[] {};

	private MicroCommands plugin = JavaPlugin.getPlugin(MicroCommands.class);

	public SpawnerCommand(Player operator, Command command, String[] args) {
		cmd = command;
		this.operator = operator;
		this.args = args;
	}

}
