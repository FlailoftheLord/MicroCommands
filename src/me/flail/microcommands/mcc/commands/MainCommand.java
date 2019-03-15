package me.flail.microcommands.mcc.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.tools.ChatUtils;
import me.flail.microcommands.mcc.tools.Tools;

public class MainCommand {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private FileManager fm = plugin.fileManager;
	private Tools tools = new Tools();

	public void mcc(CommandSender sender, String command, String[] args) {

		ChatUtils chat = tools.chat;

		String cmd = command.toLowerCase();

		Player player = null;

		if (sender instanceof Player) {
			player = (Player) sender;

		}

		String serverString = plugin.serverName + " " + plugin.serverVersion;

		switch (args.length) {
		case 0:
			sender.sendMessage(chat.chat(fm.getMessage("About").replace("$server$", serverString)));
			break;
		case 1:
			String first = args[0].toLowerCase();

			if (first.equals("help") || first.equals("h")) {

			}


		}

	}

}
