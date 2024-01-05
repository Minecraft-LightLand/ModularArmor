package dev.xkmc.modulararmor.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulararmor.content.core.ArmorPartType;
import dev.xkmc.modulararmor.content.core.ModularArmorItem;
import dev.xkmc.modulararmor.init.ModularArmor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class ArmorType extends BaseConfig {

	public static ArmorType get(ModularArmorItem item) {
		var rl = ForgeRegistries.ITEMS.getKey(item);
		assert rl != null;
		return ModularArmor.TYPE.getEntry(rl);
	}

	@SerialClass.SerialField
	public ArrayList<ArmorPartType> required = new ArrayList<>();

	@SerialClass.SerialField
	public ArrayList<ArmorPartType> optional = new ArrayList<>();

	public List<ArmorPartType> getTypes() {
		var ans = new ArrayList<ArmorPartType>();
		ans.addAll(required);
		ans.addAll(optional);
		return ans;
	}

	public ArmorType requires(ArmorPartType... types) {
		required.addAll(List.of(types));
		return this;
	}

	public ArmorType optional(ArmorPartType... types) {
		optional.addAll(List.of(types));
		return this;
	}

}
