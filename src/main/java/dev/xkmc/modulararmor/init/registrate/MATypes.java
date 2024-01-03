package dev.xkmc.modulararmor.init.registrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.modulararmor.content.attribute.ArmorSpecialAttribute;
import dev.xkmc.modulararmor.content.core.ArmorPartType;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import dev.xkmc.modulararmor.init.ModularArmor;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.ForgeRegistries;

public class MATypes {

	public static final L2Registrate.RegistryInstance<ArmorPartType> TYPE = ModularArmor.REGISTRATE.newRegistry("type", ArmorPartType.class);
	public static final L2Registrate.RegistryInstance<ArmorModifier> MODIFIER = ModularArmor.REGISTRATE.newRegistry("modifier", ArmorModifier.class);
	public static final L2Registrate.RegistryInstance<ArmorSpecialAttribute> ATTRIBUTE = ModularArmor.REGISTRATE.newRegistry("attribute", ArmorSpecialAttribute.class);

	public static final RegistryEntry<ArmorPartType> LINING = regType("lining");
	public static final RegistryEntry<ArmorPartType> VEST = regType("vest");
	public static final RegistryEntry<ArmorPartType> HELMET = regType("helmet");
	public static final RegistryEntry<ArmorPartType> CHESTPLATE = regType("chestplate");
	public static final RegistryEntry<ArmorPartType> LEGGINGS = regType("leggings");
	public static final RegistryEntry<ArmorPartType> BOOTS = regType("boots");
	public static final RegistryEntry<ArmorPartType> TRIM = regType("trim");
	public static final RegistryEntry<ArmorPartType> DECO = regType("decoration");
	public static final RegistryEntry<ArmorPartType> MASK = regType("mask");
	public static final RegistryEntry<ArmorPartType> SOLE = regType("sole");

	public static final RegistryEntry<ArmorSpecialAttribute> DURABILITY = regAttr("durability");
	public static final RegistryEntry<Attribute> DAMAGE_REDUCTION = regAttr("damage_reduction", 0, 10000000, "Damage Reduction");

	private static RegistryEntry<ArmorPartType> regType(String name) {
		return ModularArmor.REGISTRATE.simple(name, TYPE.key(), ArmorPartType::new);
	}

	private static RegistryEntry<ArmorSpecialAttribute> regAttr(String name) {
		return ModularArmor.REGISTRATE.simple(name, ATTRIBUTE.key(), ArmorSpecialAttribute::new);
	}

	private static RegistryEntry<Attribute> regAttr(String id, double def, double max, String name) {
		ModularArmor.REGISTRATE.addRawLang("attribute.name." + id, name);
		return ModularArmor.REGISTRATE.simple(id, ForgeRegistries.ATTRIBUTES.getRegistryKey(), () ->
				new RangedAttribute("attribute.name." + id, def, 0.0, max).setSyncable(true));
	}

	public static void register() {

	}

}
