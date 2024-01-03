package dev.xkmc.modulararmor.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2library.serial.config.CollectType;
import dev.xkmc.l2library.serial.config.ConfigCollect;
import dev.xkmc.l2library.util.annotation.DataGenOnly;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulararmor.content.core.ArmorPartType;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import dev.xkmc.modulararmor.init.ModularArmor;
import dev.xkmc.modulararmor.init.data.MAConfigGen;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

@SerialClass
public class MaterialData extends BaseConfig {

	@Nullable
	public static MaterialData get(ResourceLocation material, ArmorPartType type) {
		return ModularArmor.MATERIAL.getEntry(material.withPrefix(type.getRegistryName().getPath() + "/"));
	}

	@SerialClass.SerialField
	@ConfigCollect(CollectType.MAP_OVERWRITE)
	public LinkedHashMap<ResourceLocation, Double> attributes = new LinkedHashMap<>();

	@SerialClass.SerialField
	@ConfigCollect(CollectType.MAP_OVERWRITE)
	public LinkedHashMap<ArmorModifier, Integer> modifiers = new LinkedHashMap<>();

	public Map<ArmorAttribute, Double> getAttributes() {
		LinkedHashMap<ArmorAttribute, Double> ans = new LinkedHashMap<>();
		for (var e : attributes.entrySet()) {
			ArmorAttribute attr = ArmorAttribute.get(e.getKey());
			if (attr != null && attr.isValid()) {
				ans.put(attr, e.getValue());
			}
		}
		return ans;
	}

	// ------ DATAGEN ------

	@DataGenOnly
	public MaterialData withAttr(MAConfigGen.AttrTypeGen attr, double val) {
		attributes.put(attr.id(), val);
		return this;
	}

	@DataGenOnly
	public MaterialData withModifier(ArmorModifier mod, int val) {
		modifiers.put(mod, val);
		return this;
	}

}
