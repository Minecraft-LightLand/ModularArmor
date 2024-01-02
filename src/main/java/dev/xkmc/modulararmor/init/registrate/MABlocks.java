package dev.xkmc.modulararmor.init.registrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.modulararmor.init.ModularArmor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class MABlocks {

	public static final RegistryEntry<CreativeModeTab> TAB = ModularArmor.REGISTRATE.buildL2CreativeTab(
			"modulararmor", "Modular Armor", e -> e.icon(Items.LEATHER_CHESTPLATE::getDefaultInstance));


	static {
	}

	public static void register() {

	}

}
