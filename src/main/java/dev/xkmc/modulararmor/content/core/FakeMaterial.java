package dev.xkmc.modulararmor.content.core;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class FakeMaterial implements ArmorMaterial {

	public static final FakeMaterial MATERIAL = new FakeMaterial();

	@Override
	public int getDurabilityForType(ArmorItem.Type type) {
		return 1000000;
	}

	@Override
	public int getDefenseForType(ArmorItem.Type type) {
		return 0;
	}

	@Override
	public int getEnchantmentValue() {
		return 1;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_LEATHER;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.EMPTY;
	}

	@Override
	public String getName() {
		return "modulararmor:modulararmor";
	}

	@Override
	public float getToughness() {
		return 0;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}
}
