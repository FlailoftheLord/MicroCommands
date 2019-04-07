package me.flail.microcommands.mcc.commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.MicroManager;
import me.flail.microcommands.mcc.io.FileManager;
import me.flail.microcommands.mcc.tools.ChatUtils;

public class CommandRegistration {
	MicroCommands plugin = MicroCommands.instance;
	private FileManager fileManager = new FileManager(plugin);

	public PluginCommand[] getCommands() {
		ConfigurationSection commands = fileManager.getFile("Commands.yml").getConfigurationSection("Commands");
		List<PluginCommand> list = new ArrayList<>();

		if (commands != null) {

			for (String command : commands.getKeys(false)) {
				list.add(this.command(command, plugin));
			}
		}

		return list.toArray(new PluginCommand[] {});
	}

	public void loadCommandsFromFile() {
		ChatUtils chat = new ChatUtils();
		ConfigurationSection commands = fileManager.getFile("Commands.yml").getConfigurationSection("Commands");

		for (PluginCommand cmd : (getCommands() != null) ? getCommands() : new PluginCommand[] {}) {
			String command = cmd.getName();

			List<String> aliases = commands.getStringList(command + ".aliases");
			List<String> args = new ArrayList<>();
			String description = commands.get(command + ".description", "").toString();

			String permission = commands.get(command + ".permission", "").toString();
			String permMessage = commands.get(command + ".no-permission", "$no-permission$").toString();

			for (String arg : commands.get(command + ".usage", "/" + command + " <args>").toString().split(" ")) {
				args.add(arg);
			}

			cmd.setDescription(chat.chat(description));
			cmd.setPermission(permission);
			cmd.setPermissionMessage(permMessage);
			cmd.setAliases(aliases);
			cmd.setLabel(command);
			cmd.setUsage(chat.chat(commands.get(command + ".usage", "/" + command + " <args>").toString()
					.replace("$command$", command)));

			cmd.setExecutor(plugin);
			cmd.setTabCompleter(plugin);

			MicroManager.registerCommandToServer(cmd);

			new MicroCommand().registerSuggestions(cmd, permission);

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
