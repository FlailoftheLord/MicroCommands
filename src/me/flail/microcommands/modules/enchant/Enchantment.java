package me.flail.microcommands.modules.enchant;

import org.bukkit.inventory.ItemStack;

public interface Enchantment {

	ItemStack get();

	void addEnchant(Enchantment type);

	Enchant enchants();

	/**
	 * Contains all enchantment types.
	 * Custom enchants are under 'custom'
	 * 
	 * @author FlailoftheLord
	 */
	class Enchant {
		/**
		 * Armor enchants.
		 * 
		 * @author FlailoftheLord
		 */
		public enum armor {
			aqua_affinity,
			respiration,
			protection,
			thorns,
			mending,
			projectile_protection,
			fire_protection,
			blast_protection,
			depth_strider,
			frost_walker,
			unbreaking
		}

		/**
		 * Enchants that go on weapons, (swords, axes & bows)
		 * 
		 * @author FlailoftheLord
		 */
		public enum weapon {
			smite,
			bane_of_arthropods,
			sharpness,
			mending,
			knockback,
			sweeping_edge,
			unbreaking,
			fire_aspect,
			looting,
			punch,
			power,
			flame,
			infinity
		}

		/**
		 * Non-weapon enchants.
		 * 
		 * @author FlailoftheLord
		 */
		public enum tool {
			mending,
			unbreaking,
			efficiency,
			fortune,
			silk_touch
		}

		/**
		 * Includes fising enchants... cause idk why
		 * 
		 * @author FlailoftheLord
		 */
		public enum miscelanious {
			lure,
			luck_of_the_sea
		}

		/**
		 * A full list of custom enchants, built right into MicroCommands!
		 * 
		 * @author FlailoftheLord
		 */
		public enum custom {
			critical,
			life_steal,
			stealth,
			flash,
			piercing
		}

	}


}
