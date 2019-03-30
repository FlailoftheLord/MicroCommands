package me.flail.microcommands.mcc.entity.MobTags;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.flail.microcommands.mcc.MicroCommands;

@SuppressWarnings("unused")
public class InvisyTag implements Listener {

	private MicroCommands plugin = MicroCommands.getPlugin(MicroCommands.class);

	@EventHandler(priority = EventPriority.LOWEST)
	public void mobTagEvent(PlayerInteractEntityEvent event) {

		Player player = event.getPlayer();

		Entity subject = event.getRightClicked();

		ItemStack eventItem = player.getInventory().getItemInMainHand();

		if (eventItem.getType().equals(Material.NAME_TAG)) {

			if (eventItem.hasItemMeta()) {

				String itemName = ChatColor.stripColor(eventItem.getItemMeta().getDisplayName());

				if (itemName.equalsIgnoreCase("Invisy")) {

					subject.setCustomName("Invisy");
					subject.setCustomNameVisible(false);
					subject.setSilent(true);

					if (subject instanceof LivingEntity) {

						LivingEntity entity = (LivingEntity) subject;

						EntityEquipment armor = entity.getEquipment();

						ItemStack opHoe = new ItemStack(Material.GOLDEN_SWORD);
						ItemMeta opHoeMeta = opHoe.getItemMeta();

						opHoeMeta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
						opHoeMeta.setUnbreakable(true);

						opHoeMeta.setDisplayName("Brands True Hoe");

						opHoe.setItemMeta(opHoeMeta);

						ItemStack swiftBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
						ItemMeta swiftBootMeta = swiftBoots.getItemMeta();

						swiftBootMeta.setDisplayName("Ez Booties");
						swiftBootMeta.setUnbreakable(true);

						AttributeModifier atb = new AttributeModifier("Ez Booties", 0.5, Operation.ADD_NUMBER);
						swiftBootMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, atb);
						swiftBootMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						swiftBootMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

						swiftBoots.setItemMeta(swiftBootMeta);

						armor.setItemInMainHand(opHoe);
						armor.setItemInMainHandDropChance(1.0F);
						armor.setBoots(swiftBoots);
						armor.setBootsDropChance(0.1F);

						entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 9, true, true));

					}

				}

			}

		}

	}

}
