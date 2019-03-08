package me.flail.microcommands.mcc;

import org.bukkit.plugin.PluginManager;

import me.flail.microcommands.mcc.commands.CommandRegistration;
import me.flail.microcommands.mcc.entity.MobTags.InvisyTag;
import me.flail.microcommands.mcc.events.ChatListener;
import me.flail.microcommands.mcc.events.PlayerJoin;
import me.flail.microcommands.mcc.events.PlayerQuit;
import me.flail.microcommands.mcc.io.FileManager;

public class MicroManager {
	private MicroCommands plugin = MicroCommands.instance;
	private PluginManager manager = plugin.getServer().getPluginManager();

	public void registerEvents() {

		manager.registerEvents(new PlayerJoin(), plugin);
		manager.registerEvents(new PlayerQuit(), plugin);

		manager.registerEvents(new InvisyTag(), plugin);

		manager.registerEvents(new ChatListener(), plugin);

	}

	public void registerCommands() {
		CommandRegistration registry = new CommandRegistration();
	}

	public boolean resourceLoader() {
		FileManager fileManager = new FileManager(plugin);

		try {

			fileManager.loadFile("Messages.yml");

			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
