package me.flail.microcommands.mcc.commands;

import java.lang.reflect.Constructor;
import java.util.regex.Pattern;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.io.FileManager;

public class CommandRegistration {
	private MicroCommands plugin = MicroCommands.instance;
	private FileManager fileManager = new FileManager(plugin);

	public void loadCommandsFromFile() {
		FileConfiguration commands = fileManager.getFile("Commands.yml");

		for (String command : commands.getKeys(false)) {
			PluginCommand cmd = this.command(command, plugin);
			TabCompleter tabCompleter = new MicroCommand().tab(cmd);

			cmd.setExecutor(plugin);
			cmd.setAliases(commands.getStringList(Pattern.quote("(?i)") + command + ".aliases"));
			cmd.setLabel(command);
			cmd.setTabCompleter(tabCompleter);

		}

	}

	protected PluginCommand command(String name, Plugin plugin) {
		try {
			Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class,
					Plugin.class);
			constructor.setAccessible(true);

			return constructor.newInstance(name, plugin);
		} catch (Throwable t) {
			return null;
		}

	}

}
