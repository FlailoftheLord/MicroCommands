package me.flail.oldmcc.mcc.commands;

import org.bukkit.command.CommandSender;

import me.flail.oldmcc.mcc.permissions.Permission;
import me.flail.tools.Logger;

public class HomeCommand extends Logger {

	public boolean home(CommandSender sender, String alias, String[] args) {

		switch (convert(alias)) {

		case "mhome":

			return home(sender, args);
		case "homes":

			return listHomes(sender, args);
		case "sethome":

			return setHome(sender, args);
		case "delhome":

			return delHome(sender, args);
		default:
			return false;
		}

	}

	private boolean home(CommandSender operator, String[] args) {


		return false;
	}

	private boolean listHomes(CommandSender operator, String[] args) {

		return false;
	}

	private boolean setHome(CommandSender operator, String[] args) {
		if (args.length > 0) {
			if (args.length > 1) {
				if (operator.hasPermission(new Permission("home").node())) {

				}


			}


		}

		return false;
	}


	private boolean delHome(CommandSender operator, String[] args) {

		return false;
	}

	private String convert(String alias) {
		String a = alias.toLowerCase();
		if (a.contains("del")) {
			return "delhome";
		}
		if (a.contains("set") || a.contains("add")) {
			return "sethome";
		}

		if (a.contains("list") || a.contains("homes")) {
			return "homes";
		}

		return "mhome";
	}

}
