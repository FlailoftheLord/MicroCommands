package me.flail.mcc;

import me.flail.mcc.listener.PlayerListener;
import me.flail.tools.DataFile;
import me.flail.tools.Logger;

public class MccLoader extends Logger {

	public void startup() {
		// Firstly, make sure the config is loaded.
		plugin.saveDefaultConfig();
		// Then, load the rest of the files ;D
		this.loadDataFiles();

		// Register commands & event listeners.
		registerCommands();
		registerListeners();

	}

	public void reload() {

	}

	public void shutdown() {

	}

	protected void loadDataFiles() {
		plugin.config = new DataFile("config.yml");
		plugin.messages = new DataFile("Messages.yml");
		plugin.cmdFile = new DataFile("Commands.yml");

	}

	public void registerCommands() {

	}

	public void registerListeners() {
		plugin.pm.registerEvents(new PlayerListener(), plugin);

	}

}
