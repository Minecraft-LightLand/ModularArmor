package dev.xkmc.modulararmor.content.core;

import com.google.common.collect.Multimap;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.Map;

public interface IArmorCapability {
	IArmorCapability FAKE = new FakeArmorCapability();

	int getEnchantability();

	int getDurability();

	Multimap<Attribute, AttributeModifier> getAttributeModifiers();

	int getModifierLevel(ArmorModifier modifier);

	Map<ArmorModifier, Integer> getModifiers();
}
