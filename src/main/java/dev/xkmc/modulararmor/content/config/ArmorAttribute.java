package dev.xkmc.modulararmor.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulararmor.init.ModularArmor;
import dev.xkmc.modulararmor.init.registrate.MATypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

@SerialClass
public final class ArmorAttribute extends BaseConfig {

	@Nullable
	public static ArmorAttribute get(ResourceLocation id) {
		return ModularArmor.ATTRIBUTE.getEntry(id);
	}

	@SerialClass.SerialField
	public ResourceLocation attr;

	@SerialClass.SerialField
	public AttributeModifier.Operation op;

	public ArmorAttribute() {

	}

	public ArmorAttribute(ResourceLocation attr, AttributeModifier.Operation op) {
		this.attr = attr;
		this.op = op;
	}

	public boolean isValid() {
		return MATypes.ATTRIBUTE.get().containsKey(attr) || ForgeRegistries.ATTRIBUTES.containsKey(attr);
	}

}
