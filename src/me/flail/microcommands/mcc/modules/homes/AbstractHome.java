package me.flail.microcommands.mcc.modules.homes;

import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;

import me.flail.microcommands.mcc.entity.player.MicroPlayer;
import me.flail.microcommands.modules.homes.MicroHome;

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
		FileConfiguration playerConfig = owner.getDataFile();
		playerConfig.set(Pattern.quote("(?i)") + "Homes." + home.getName(), null);

		return !playerConfig.contains(Pattern.quote("(?i)") + "Homes." + home.getName());
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
		FileConfiguration playerConfig = owner.getDataFile();

		if (!overwrite && playerConfig.contains("Homes." + this.name())) {
			return false;
		}
		playerConfig.set("Homes." + this.name() + ".Location.World", location.getWorld().getName());
		playerConfig.set("Homes." + this.name() + ".Location.X", location.getX());
		playerConfig.set("Homes." + this.name() + ".Location.Y", location.getY());
		playerConfig.set("Homes." + this.name() + ".Location.Z", location.getZ());
		playerConfig.set("Homes." + this.name() + ".Location.Pitch", location.getPitch());
		playerConfig.set("Homes." + this.name() + ".Location.Yaw", location.getYaw());

		owner.saveDataFile(playerConfig);
		return true;
	}


}
