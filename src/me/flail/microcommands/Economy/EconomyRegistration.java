package me.flail.microcommands.Economy;

import org.bukkit.plugin.Plugin;

import me.flail.microcommands.MicroCommands;

public class EconomyRegistration {

	public void registerEconomy(Plugin plugin) {
		if (plugin.getName().toLowerCase().contains("microcommands")) {
			MicroCommands.plugin();
		}

	}

}
