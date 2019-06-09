package me.flail.microcommands.mcc;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import me.flail.microcommands.mcc.commands.CommandProcessor;
import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.microcommands.mcc.tools.Tools;
import me.flail.tools.DataFile;
import me.flail.tools.Logger;

public class BootManager extends Logger {
	private MicroCommands plugin = MicroCommands.instance;
	public Server server = plugin.getServer();
	public ConsoleCommandSender console = server.getConsoleSender();
	public Logger logger = new Logger();
	public Tools tools = new Tools();
	public PluginManager pluginManager = server.getPluginManager();
	public CommandProcessor commandControl = new CommandProcessor(plugin);
	private MicroManager manager = new MicroManager();

	public boolean load() {

		try {
			plugin.console = console;
			plugin.logger = logger;
			plugin.cmdControl = commandControl;
			plugin.plugin = pluginManager;
			plugin.serverName = server.getName();

			plugin.saveDefaultConfig();
			// Load up config and other resource files before the plugin is enabled.
			console.sendMessage("loaded player data folder");
			this.loadExtraResources();
			console.sendMessage("loaded configuration files.");

			console.sendMessage("---------------------------");

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public void startup() {
		long startTime = System.currentTimeMillis();

		manager.registerCommands();
		console("Loaded commands.");
		manager.registerEvents();
		console("Events initiated.");
		this.loadPlayers();
		console("Player Database Loaded!");

		long endTime = System.currentTimeMillis();
		double bootTime = (endTime - startTime);
		console("");
		if (bootTime > 1000) {
			console("Startup finished in " + (bootTime / 1000) + "s\n");
			return;
		}

		console("Startup finished in " + bootTime + "ms\n");

	}

	private void loadExtraResources() {

		new DataFile("Commands.yml").load();
		new DataFile("ServerData.yml").load();
	}

	public void loadPlayers() {
		plugin.offlinePlayers.clear();
		plugin.playerDatabase.clear();
		for (OfflinePlayer player : plugin.server.getOfflinePlayers()) {
			plugin.offlinePlayers.putIfAbsent(player.getName().toLowerCase(), player.getUniqueId());
		}
		for (Player player : plugin.server.getOnlinePlayers()) {
			plugin.playerDatabase.add(new MicroPlayer(player.getUniqueId()));
		}

	}

}
