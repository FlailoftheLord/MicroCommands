package me.flail.microcommands.mcc.commands;

import java.util.Locale;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.commands.homes.HomeCommand;
import me.flail.microcommands.mcc.tools.ChatUtils;
import me.flail.microcommands.mcc.tools.Tools;

public class CommandProcessor {
	MicroCommands plugin;

	private Tools tools = new Tools();

	public CommandProcessor(MicroCommands plugin) {
		this.plugin = plugin;
	}

	public boolean processCommand(CommandSender cmdSender, Command command, String label, String[] args) {

		ChatUtils chatUtils = tools.chat;

		String cmd = command.getName().toLowerCase(Locale.ENGLISH);

		switch (cmd) {

		case "microcommands":
			new MainCommand().mcc(cmdSender, cmd, args);
			break;
		case "mnms":
			cmdSender.sendMessage(chatUtils.chat("$prefix$ &6&lLol"));
			break;
		case "spawner":

			break;
		case "home":
			return new HomeCommand().home(cmdSender, label, args);

		case "fly":
			if (cmdSender instanceof Player) {
				Player player = (Player) cmdSender;
				if (player.hasPermission("microcommands.fly")) {
					player.setAllowFlight(true);
					player.setFlying(true);
				}
			}

		}

		return true;

	}

}
