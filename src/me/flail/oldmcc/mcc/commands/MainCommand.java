package me.flail.oldmcc.mcc.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.flail.oldmcc.mcc.MicroCommands;
import me.flail.oldmcc.mcc.control.Debugger;
import me.flail.oldmcc.mcc.tools.ChatUtils;
import me.flail.oldmcc.mcc.tools.Tools;
import me.flail.tools.Message;

@SuppressWarnings("unused")
public class MainCommand {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
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
			sender.sendMessage(chat.chat(new Message("About").string().replace("$server$", serverString)));
			break;
		case 1:
			String first = args[0].toLowerCase();

			if (first.contains("help")) {

				break;
			}

			if (first.equals("debug")) {
				if (sender.hasPermission("microcommands.debug")) {
					new Debugger().run();
				}
			}


		}

	}

}
