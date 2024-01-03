package dev.xkmc.modulararmor.content.modifier;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class WaterMendingModifier extends ArmorModifier {

	@Override
	public void onArmorTick(Player player, ItemStack stack, int level) {
		if (player.isInWaterRainOrBubble()) {
			if (!player.level().isClientSide() && player.tickCount % 20 == 0) {
				if (stack.getDamageValue() > 0) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}
		}
	}

}
