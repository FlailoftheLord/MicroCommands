package me.flail.oldmcc.mcc.entity;

import me.flail.oldmcc.entity.IMicroEntity;

public abstract class IME implements IMicroEntity, IMicroEntity.CraftSinon {

	@Override
	public CraftSinon sinon(String name) {
		if (name.equalsIgnoreCase("sinon434")) {

			return CraftSinon.class.cast("Sinon434");
		} else {
			return null;
		}

	}

}
