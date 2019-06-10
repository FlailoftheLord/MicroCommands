package me.flail.oldmcc.mcc.modules.homes;

import org.bukkit.Location;
import org.bukkit.World;

import me.flail.oldmcc.mcc.entity.player.MicroPlayer;

public class HomeLocation {
	private String home;
	private World world;
	private int x;
	private int y;
	private int z;
	private float pitch;
	private float yaw;

	public HomeLocation(String home, World world, int x, int y, int z) {
		set(home, world, x, y, z, 90, 00);
	}

	public HomeLocation(String home, World world, int x, int y, int z, float pitch, float yaw) {
		set(home, world, x, y, z, pitch, yaw);
	}

	public HomeLocation(String home, Location loc) {
		set(home, loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getPitch(), loc.getYaw());
	}

	/**
	 * Visible only to plain extents.
	 * Used to set all options, prevents repetetive code.s
	 * 
	 * @param home
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param pitch
	 * @param yaw
	 */
	protected void set(String home, World world, int x, int y, int z, float pitch, float yaw) {
		this.home = home;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	public Location toLocation() {
		return new Location(world, x, y, z, pitch, yaw);
	}

	public String getName() {
		return home;
	}

	public void name(String newName) {
		home = newName;
	}

	public void teleport(MicroPlayer player) {
		player.player().teleport(this.toLocation());
	}

	public World getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
