package me.flail.microcommands.mcc.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TabCompleter {
	private Command command;

	public TabCompleter(CommandSender sender, Command command) {
		this.command = command;
	}

	public List<String> get(String[] arguments) {
		List<String> list = new ArrayList<>();
		String usage = command.getUsage();
		int length = arguments.length;

		try {
			String[] options = parseArgs(usage/* .substring(usage.indexOf("<")) */.split(" ")[length - 1]);

			for (String s : options) {
				if (!s.isEmpty() && s.startsWith(arguments[length - 1])) {
					list.add(s);
				}
			}

		} catch (Exception e) {
		}

		return list;
	}

	protected String[] parseArgs(String line) {
		String trimmed = line.replace("[", "").replace("]", "").replace("<", "").replace(">", "");

		return trimmed.replace(" ", "").split(":");
	}

}
