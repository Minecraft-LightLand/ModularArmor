package dev.xkmc.modulararmor.content.modifier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

public class WaterBreathingModifier extends ArmorModifier {

	@Override
	public void onArmorTick(Player player, ItemStack stack, int level) {
		if (!player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
			player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, false, true));
		}
	}

}
