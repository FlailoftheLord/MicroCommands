package me.flail.oldmcc.mcc.modules.homes;

import java.util.regex.Pattern;

import me.flail.oldmcc.mcc.entity.player.MicroPlayer;
import me.flail.oldmcc.modules.homes.MicroHome;
import me.flail.tools.DataFile;

/**
 * Note for plugin devs: use {@link Home} instead of accessing this class.
 * 
 * @author FlailoftheLord
 */
public abstract class AbstractHome implements MicroHome {
	protected MicroPlayer owner;
	protected HomeLocation home;

	/**
	 * Note for plugin devs: use {@link Home} instead of accessing this class.
	 * 
	 * @param owner
	 *                  The owner of this home.
	 * @param home
	 *                  This homes' location: {@link HomeLocation} object.
	 */
	protected AbstractHome(MicroPlayer owner, HomeLocation home) {
		this.owner = owner;
		this.home = home;
	}

	@Override
	public String name() {
		return home.getName();
	}

	@Override
	public MicroPlayer owner() {
		return owner;
	}

	@Override
	public HomeLocation location() {
		return home;
	}

	@Override
	public void setName(String name) {
		home.name(name);
	}

	@Override
	public void setLocation(HomeLocation location) {
		home = location;
	}

	@Override
	public boolean delete() {
		DataFile playerConfig = owner.dataFile();
		playerConfig.setValue(Pattern.compile("(?i)") + "Homes." + home.getName(), null);

		return !playerConfig.hasValue(Pattern.compile("(?i)") + "Homes." + home.getName());
	}

	/**
	 * Saves this home to the file, if and ONLY if it doesn't already exist.
	 * Use {@linkplain Home#save(boolean overwrite)} to specify wether to overwrite homes on save.
	 */
	public void save() {
		save(false);
	}

	@Override
	public boolean save(boolean overwrite) {
		return this.set(home, overwrite);
	}

	private boolean set(HomeLocation location, boolean overwrite) {
		DataFile playerData = owner.dataFile();

		if (!overwrite && playerData.hasValue("Homes." + this.name())) {
			return false;
		}
		playerData.setValue("Homes." + this.name() + ".Location.World", location.getWorld().getName());
		playerData.setValue("Homes." + this.name() + ".Location.X", location.getX());
		playerData.setValue("Homes." + this.name() + ".Location.Y", location.getY());
		playerData.setValue("Homes." + this.name() + ".Location.Z", location.getZ());
		playerData.setValue("Homes." + this.name() + ".Location.Pitch", location.getPitch());
		playerData.setValue("Homes." + this.name() + ".Location.Yaw", location.getYaw());

		return true;
	}


}
