package dev.xkmc.modulararmor.content.block;

import dev.xkmc.modulararmor.content.config.ArmorType;
import dev.xkmc.modulararmor.content.core.ModularArmorItem;
import dev.xkmc.modulararmor.init.registrate.MAItems;
import net.minecraft.world.item.ItemStack;

public class ArmorSlotLayout {

	private ArmorType type = ArmorType.get(MAItems.HELMET.get());

	public boolean createSlot(int index, ItemStack stack) {
		var list = type.getTypes();
		if (index < 0 || index >= list.size()) return false;
		return true;
	}

	public boolean createOldSlot(int index, ItemStack stack) {
		return stack.getItem() instanceof ModularArmorItem item && ArmorType.get(item) == type;
	}
}
