package me.flail.microcommands.mcc.control;

import org.bukkit.plugin.Plugin;

import me.flail.microcommands.mcc.MicroCommands;

public class EssentialsRestricter {

	MicroCommands plugin = MicroCommands.instance;

	public boolean disable() {
		Plugin essentials = plugin.plugin.getPlugin("Essentials");

		if (essentials.isEnabled()) {
			plugin.server.getScheduler().cancelTasks(essentials);
			for (String command : essentials.getDescription().getCommands().keySet()) {
				if (command.startsWith("essentials")) {
					plugin.server.getPluginCommand(command).setExecutor(null);
				}
			}

			essentials.getPluginLoader().disablePlugin(essentials);
			plugin.plugin.disablePlugin(essentials);
			return true;
		}

		return false;
	}

}
