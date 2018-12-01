package me.flail.MicroCommands;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class PermissionControl {

	private MicroCommands plugin;

	public PermissionControl(MicroCommands plugin) {
		this.plugin = plugin;
	}

	public boolean playerHasAccess(Player player, String[] permissions) {

		boolean response = false;

		for (String s : permissions) {

			if (player.hasPermission(s)) {
				response = true;
				break;
			}

		}

		return response;
	}

}
