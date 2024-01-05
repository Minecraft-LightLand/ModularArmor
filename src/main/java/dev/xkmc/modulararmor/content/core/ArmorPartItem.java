package dev.xkmc.modulararmor.content.core;

import net.minecraft.world.item.Item;

public class ArmorPartItem extends Item {

	private final ArmorPartType type;

	public ArmorPartItem(Properties properties, ArmorPartType type) {
		super(properties);
		this.type = type;
	}

	public ArmorPartType getType() {
		return type;
	}

}
