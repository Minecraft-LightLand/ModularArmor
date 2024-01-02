package dev.xkmc.modulararmor.init.data;

import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.minecraft.data.DataGenerator;

public class MAConfigGen extends ConfigDataProvider {

	public MAConfigGen(DataGenerator generator) {
		super(generator, "Modular Armor Config");
	}

	@Override
	public void add(Collector collector) {
	}

}