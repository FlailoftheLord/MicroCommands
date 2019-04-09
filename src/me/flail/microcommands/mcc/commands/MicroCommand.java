package me.flail.microcommands.mcc.commands;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import me.flail.microcommands.mcc.MicroCommands;
import me.flail.microcommands.mcc.commodore.Commodore;
import me.flail.microcommands.mcc.commodore.CommodoreProvider;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MicroCommand {
	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);
	private Server server = plugin.getServer();

	protected static boolean brigadierSupported() {
		return CommodoreProvider.isSupported();
	}

	public List<String> getCommandArgs(PluginCommand command) {
		List<String> args = new ArrayList<>();
		for (String s : command.getUsage().split(" ")) {
			if (s.startsWith("/")) {
				continue;
			}

			args.add(s.toLowerCase());
		}

		return args;
	}

	public boolean isCommandTaken(String cmd) {
		boolean isTaken = false;
		PluginCommand command = server.getPluginCommand(cmd);

		if (command != null) {

			Plugin commandOwner = command.getPlugin();

			if (!commandOwner.getDescription().getMain().contains("me.flail.microcommands")) {
				isTaken = true;
			}

		}

		return isTaken;
	}

	@Nullable
	public PluginCommand getCommand(String name) {
		return server.getPluginCommand(name);
	}

	public boolean isDisabled(String cmd) {

		boolean isDisabled = false;
		PluginCommand command = plugin.getCommand(cmd);
		List<String> aliases = command.getAliases();
		List<String> disabledCmds = plugin.getConfig().getStringList("DisabledCommands");

		for (String s : aliases) {
			for (String c : disabledCmds) {

				if (c.equalsIgnoreCase(s)) {
					isDisabled = true;
					break;
				}

			}
		}

		return isDisabled;
	}

	public void registerSuggestions(PluginCommand command, String permission) {

		if (brigadierSupported()) {
			Commodore processor = CommodoreProvider.getCommodore(plugin);

			LiteralArgumentBuilder<?> builder = LiteralArgumentBuilder.literal(command.getName());

			for (String arg : this.getCommandArgs(command)) {
				builder.then(arg(arg));
			}

			builder.build();

			processor.register(command, builder);
		}

	}

	private static ArgumentBuilder arg(String arg) {
		return RequiredArgumentBuilder.argument(arg, StringArgumentType.string());

	}

}
