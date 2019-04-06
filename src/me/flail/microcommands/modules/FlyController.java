package me.flail.microcommands.modules;

import me.flail.microcommands.mcc.entity.player.MicroPlayer;

public class FlyController {
	private MicroPlayer subject;

	public FlyController(MicroPlayer player) {
		subject = player;
	}


	/**
	 * Toggles fly mode for specified player.
	 * @return true if their fly was enabled, false otherwise.
	 */
	public boolean toggleFly() {

		return subject.player().isFlying();
	}

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param persistent
	 *                       wether fly status should be saved if player goes offline.
	 * @return true if fly was enabled, false otherwise.
	 */
	public boolean toggleFly(boolean persistent) {
		return false;
	}

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param duration
	 *                     the time that fly should be toggled for, after which, it will be returned to
	 *                     it's original state for this player.
	 * @return true if their fly was enabled, false otherwise.
	 */
	public boolean toggleFly(int duration) {
		return false;
	}

	/**
	 * Toggles fly mode for specified player.
	 * 
	 * @param duration
	 *                       the time that fly should be toggled for, after which, it will be returned
	 *                       to it's original state for this player.
	 * @param persistent
	 *                       wether fly status should be saved if player goes offline.
	 * @return true if their fly was enabled, false otherwise.
	 */
	public boolean toggleFly(int duration, boolean persistent) {
		return false;
	}

}
