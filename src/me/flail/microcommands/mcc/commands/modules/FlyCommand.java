package me.flail.microcommands.mcc.commands.modules;

import org.bukkit.entity.Player;

import me.flail.microcommands.modules.FlyController;

public class FlyCommand implements FlyController {

	@Override
	public boolean toggleFly(Player player) {
		return false;
	}

	@Override
	public boolean toggleFly(Player player, boolean persistent) {
		return false;
	}

	@Override
	public boolean toggleFly(Player player, int duration) {
		return false;
	}

	@Override
	public boolean toggleFly(Player player, int duration, boolean persistent) {
		return false;
	}

}
