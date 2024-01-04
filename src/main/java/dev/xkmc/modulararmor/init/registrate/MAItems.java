package dev.xkmc.modulararmor.init.registrate;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.modulararmor.content.core.ModularArmorItem;
import dev.xkmc.modulararmor.init.ModularArmor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;

public class MAItems {

	public static final ItemEntry<ModularArmorItem> HELMET, CHESTPLATE, LEGGINGS, BOOTS;

	static {
		HELMET = ModularArmor.REGISTRATE.item("modular_helmet",
						p -> new ModularArmorItem(ArmorItem.Type.HELMET, p))
				.model(MAItems::model)
				.tag(Tags.Items.ARMORS_HELMETS)
				.register();
		CHESTPLATE = ModularArmor.REGISTRATE.item("modular_chestplate",
						p -> new ModularArmorItem(ArmorItem.Type.CHESTPLATE, p))
				.model(MAItems::model)
				.tag(Tags.Items.ARMORS_CHESTPLATES)
				.register();
		LEGGINGS = ModularArmor.REGISTRATE.item("modular_leggings",
						p -> new ModularArmorItem(ArmorItem.Type.LEGGINGS, p))
				.model(MAItems::model)
				.tag(Tags.Items.ARMORS_LEGGINGS)
				.register();
		BOOTS = ModularArmor.REGISTRATE.item("modular_boots",
						p -> new ModularArmorItem(ArmorItem.Type.BOOTS, p))
				.model(MAItems::model)
				.tag(Tags.Items.ARMORS_BOOTS)
				.register();
	}

	private static void model(DataGenContext<Item, ModularArmorItem> ctx, RegistrateItemModelProvider pvd) {
		pvd.getBuilder(ctx.getName())
				.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
				.texture("particle", "minecraft:block/clay");
	}

	public static void register() {
	}

}
