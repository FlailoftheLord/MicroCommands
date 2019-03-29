package me.flail.microcommands.mcc.control;

import org.bukkit.plugin.Plugin;

import me.flail.microcommands.mcc.MicroCommands;

public class CMIRestrictor {

	MicroCommands plugin = MicroCommands.instance;

	public boolean disable() {

		Plugin cmi = plugin.plugin.getPlugin("CMI");

		if (cmi.isEnabled()) {
			plugin.server.getScheduler().cancelTasks(cmi);

			plugin.server.getPluginCommand("cmi").setExecutor(null);

			cmi.getPluginLoader().disablePlugin(cmi);
			plugin.plugin.disablePlugin(cmi);

			return true;
		}

		return false;
	}

}
