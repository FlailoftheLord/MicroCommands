package me.flail.microcommands.mcc.commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.command.Command;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.StringArgument;
import me.flail.microcommands.mcc.MicroCommands;

public class MicroCommand {

	MicroCommands plugin = MicroCommands.instance;

	public List<String> getCommandArgs(Command command) {

		return new ArrayList<>();
	}

	public void registerSuggestions(Command command, String permission, List<String> aliases, List<String> args) {
		if (plugin.isCommandAPI) {
			try {
				Constructor<CommandPermission> cmdPermConst = CommandPermission.class.getConstructor(String.class);
				cmdPermConst.setAccessible(true);
				CommandPermission perm = cmdPermConst.newInstance(permission);

				LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();

				for (String arg : args) {
					arguments.put(arg, new StringArgument());
				}

				this.register(command.getName(), perm, aliases.toArray(new String[] {}), arguments);

			} catch (Throwable t) {
			}
		}
	}

	protected boolean register(String command, CommandPermission permission, String[] aliases,
			LinkedHashMap<String, Argument> args) {
		try {
			CommandAPI.getInstance().register(command, permission, aliases, args, MicroCommands.instance);

			return true;
		} catch (Throwable t) {
			return false;
		}

	}

}
