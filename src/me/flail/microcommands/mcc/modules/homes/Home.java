package me.flail.microcommands.mcc.modules.homes;

import me.flail.microcommands.mcc.entity.player.MicroPlayer;

public class Home extends AbstractHome {


	public Home(MicroPlayer owner, HomeLocation home) {
		super(owner, home);
	}

	public Home setOwner(MicroPlayer newOwner) {
		return new Home(newOwner, home);
	}

	@Override
	public void teleport() {
		home.teleport(owner);
	}

}
