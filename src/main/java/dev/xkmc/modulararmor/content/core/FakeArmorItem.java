package dev.xkmc.modulararmor.content.core;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class FakeArmorItem extends ArmorItem {
	public FakeArmorItem(Type slot, Properties prop) {
		super(FakeMaterial.MATERIAL, slot, prop);
	}

	public boolean isValidRepairItem(ItemStack stack, ItemStack material) {
		return false;
	}

	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
		return super.getDefaultAttributeModifiers(slot);
	}

}
