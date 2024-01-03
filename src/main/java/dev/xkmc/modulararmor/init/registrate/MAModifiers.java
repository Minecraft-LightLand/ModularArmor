package dev.xkmc.modulararmor.init.registrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import dev.xkmc.modulararmor.content.modifier.WaterBreathingModifier;
import dev.xkmc.modulararmor.content.modifier.WaterMendingModifier;
import dev.xkmc.modulararmor.init.ModularArmor;

public class MAModifiers {

	public static final RegistryEntry<ArmorModifier> GOLD = reg("shiny", ArmorModifier::new);
	public static final RegistryEntry<WaterMendingModifier> TURTLE = reg("water_mending", WaterMendingModifier::new);
	public static final RegistryEntry<WaterBreathingModifier> BREATHING = reg("water_breathing", WaterBreathingModifier::new);

	private static <T extends ArmorModifier> RegistryEntry<T> reg(String name, NonNullSupplier<T> sup) {
		return ModularArmor.REGISTRATE.simple(name, MATypes.MODIFIER.key(), sup);
	}

	public static void register() {

	}

}
