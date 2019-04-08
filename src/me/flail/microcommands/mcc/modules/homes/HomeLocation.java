package me.flail.microcommands.mcc.modules.homes;

import org.bukkit.Location;
import org.bukkit.World;

import me.flail.microcommands.mcc.entity.player.MicroPlayer;

public class HomeLocation {
	private String home;
	private World world;
	private int x;
	private int y;
	private int z;
	private float pitch;
	private float yaw;

	public HomeLocation(String home, World world, int x, int y, int z) {
		this.home = home;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		pitch = 90;
		yaw = 00;
	}

	public HomeLocation(String home, World world, int x, int y, int z, float pitch, float yaw) {
		new HomeLocation(home, world, x, y, z, pitch, yaw);
	}

	public HomeLocation(String home, Location loc) {
		new HomeLocation(home, loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getPitch(), loc.getYaw());
	}

	public Location toLocation() {
		return new Location(world, x, y, z, pitch, yaw);
	}

	public void teleport(MicroPlayer player) {
		player.player().teleport(this.toLocation());
	}

}
