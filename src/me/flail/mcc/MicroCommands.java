package me.flail.mcc;

import java.util.List;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.tools.DataFile;

public class MicroCommands extends JavaPlugin {
	public static MicroCommands mcc;

	private MccLoader loader;
	public Server server;
	public PluginManager pm;

	public DataFile messages;
	public DataFile config;
	public DataFile cmdFile;

	@Override
	public void onLoad() {
		mcc = this;

		loader = new MccLoader();
		server = getServer();
		pm = server.getPluginManager();
	}

	@Override
	public void onEnable() {
		loader.startup();

	}

	@Override
	public void onDisable() {
		loader.shutdown();

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return null;
	}

}
