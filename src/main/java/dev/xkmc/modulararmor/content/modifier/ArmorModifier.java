package dev.xkmc.modulararmor.content.modifier;

import dev.xkmc.l2library.base.NamedEntry;
import dev.xkmc.modulararmor.init.registrate.MATypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ArmorModifier extends NamedEntry<ArmorModifier> {
	public ArmorModifier() {
		super(MATypes.MODIFIER);
	}

	public void onArmorTick(Player player, ItemStack stack, int level) {
	}

}
