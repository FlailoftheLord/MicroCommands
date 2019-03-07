package me.flail.microcommands.mcc;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;

import me.flail.microcommands.MicroCommands;
import me.flail.microcommands.io.Logger;
import me.flail.microcommands.mcc.commands.CommandProcessor;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.io.ILogger;
import me.flail.microcommands.mcc.tools.Tools;

public class Boot {
	private MicroCommands plugin = MicroCommands.plugin();
	public Server server = plugin.getServer();
	public ConsoleCommandSender console = server.getConsoleSender();
	public Logger logger = new ILogger();
	public Tools tools = new Tools(plugin);
	public PluginManager pluginManager = server.getPluginManager();
	public CommandProcessor commandControl = new CommandProcessor(plugin);
	public FileManager fileManager = new FileManager(plugin);

	public boolean load() {

		try {
			plugin.server = server;
			plugin.console = console;
			plugin.logger = logger;
			plugin.tools = tools;
			plugin.fileManager = fileManager;
			plugin.cmdControl = commandControl;
			plugin.plugin = pluginManager;
			plugin.serverName = server.getName();

			// Load up config and other resource files before the plugin is enabled.
			console.sendMessage("================");
			console.sendMessage("loading resources...");
			plugin.saveDefaultConfig();
			console.sendMessage("loaded config.yml");
			fileManager.userDataFolder();
			console.sendMessage("loaded player data folder");

			console.sendMessage("================");

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void startup() {

	}

}
