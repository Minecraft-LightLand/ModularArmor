package dev.xkmc.modulararmor.init.registrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import dev.xkmc.modulararmor.init.ModularArmor;

public class MAModifiers {

	public static final RegistryEntry<ArmorModifier> GOLD = reg("shiny");
	public static final RegistryEntry<ArmorModifier> FIRE = reg("fire_resistance");
	public static final RegistryEntry<ArmorModifier> TURTLE = reg("water_mending");
	public static final RegistryEntry<ArmorModifier> BREATHING = reg("water_breathing");

	private static RegistryEntry<ArmorModifier> reg(String name) {
		return ModularArmor.REGISTRATE.simple(name, MATypes.MODIFIER.key(), ArmorModifier::new);
	}

	public static void register() {

	}

}
