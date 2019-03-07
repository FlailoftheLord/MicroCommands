package me.flail.microcommands.mcc.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.flail.microcommands.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.tools.ChatUtils;

public class MainCommand {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private FileManager fm = plugin.fileManager;

	public void mcc(CommandSender sender, String command, String[] args) {

		ChatUtils chat = plugin.tools.chat;

		String cmd = command.toLowerCase();

		Player player = null;

		if (sender instanceof Player) {
			player = (Player) sender;

		}

		String serverString = plugin.serverName + " " + plugin.serverVersion;

		if (args.length == 0) {
			sender.sendMessage(chat.chat(fm.getMessage("About").replace("$server$", serverString)));
		}

	}

}
