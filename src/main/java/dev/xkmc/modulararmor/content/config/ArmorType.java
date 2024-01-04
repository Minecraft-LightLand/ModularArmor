package dev.xkmc.modulararmor.content.config;

import dev.xkmc.l2library.serial.config.BaseConfig;
import dev.xkmc.l2serial.serialization.SerialClass;
import dev.xkmc.modulararmor.content.core.ArmorPartType;

import java.util.ArrayList;
import java.util.List;

@SerialClass
public class ArmorType extends BaseConfig {

	@SerialClass.SerialField
	public ArrayList<ArmorPartType> required = new ArrayList<>();

	@SerialClass.SerialField
	public ArrayList<ArmorPartType> optional = new ArrayList<>();

	public ArmorType requires(ArmorPartType... types) {
		required.addAll(List.of(types));
		return this;
	}

	public ArmorType optional(ArmorPartType... types) {
		optional.addAll(List.of(types));
		return this;
	}

}
