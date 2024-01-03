package dev.xkmc.modulararmor.content.core;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.Map;

public record FakeArmorCapability() implements IArmorCapability {
	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public int getDurability() {
		return 1000000;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
		return HashMultimap.create();
	}

	@Override
	public int getModifierLevel(ArmorModifier modifier) {
		return 0;
	}

	@Override
	public Map<ArmorModifier, Integer> getModifiers() {
		return Map.of();
	}

}
