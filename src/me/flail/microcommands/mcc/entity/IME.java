package me.flail.microcommands.mcc.entity;

import me.flail.microcommands.entity.MicroEntity;

public abstract class IME implements MicroEntity, MicroEntity.CraftSinon {

	@Override
	public CraftSinon sinon(String name) {
		if (name.equalsIgnoreCase("sinon434")) {

			return CraftSinon.class.cast("Sinon434");
		} else {
			return null;
		}

	}

}
