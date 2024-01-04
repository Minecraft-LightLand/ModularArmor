package dev.xkmc.modulararmor.init;

import com.tterrag.registrate.providers.ProviderType;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.serial.config.ConfigTypeEntry;
import dev.xkmc.l2library.serial.config.PacketHandlerWithConfig;
import dev.xkmc.modulararmor.content.config.ArmorAttribute;
import dev.xkmc.modulararmor.content.config.ArmorType;
import dev.xkmc.modulararmor.content.config.MaterialData;
import dev.xkmc.modulararmor.init.data.*;
import dev.xkmc.modulararmor.init.registrate.MABlocks;
import dev.xkmc.modulararmor.init.registrate.MAItems;
import dev.xkmc.modulararmor.init.registrate.MAModifiers;
import dev.xkmc.modulararmor.init.registrate.MATypes;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModularArmor.MODID)
@Mod.EventBusSubscriber(modid = ModularArmor.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModularArmor {

	public static final String MODID = "modulararmor";
	public static final PacketHandlerWithConfig HANDLER = new PacketHandlerWithConfig(
			new ResourceLocation(MODID, "main"), 1
	);
	public static final Logger LOGGER = LogManager.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final ConfigTypeEntry<ArmorAttribute> ATTRIBUTE = new ConfigTypeEntry<>(HANDLER, "attribute", ArmorAttribute.class);
	public static final ConfigTypeEntry<ArmorType> TYPE = new ConfigTypeEntry<>(HANDLER, "type", ArmorType.class);
	public static final ConfigTypeEntry<MaterialData> MATERIAL = new ConfigTypeEntry<>(HANDLER, "material", MaterialData.class);

	public ModularArmor() {

		MABlocks.register();
		MAItems.register();
		MATypes.register();
		MAModifiers.register();
		MAConfig.init();

		REGISTRATE.addDataGenerator(ProviderType.LANG, MALangData::addTranslations);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, RecipeGen::genRecipe);
		REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, MATagGen::onBlockTagGen);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, MATagGen::onItemTagGen);
	}

	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {

		});
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		boolean server = event.includeServer();
		PackOutput output = event.getGenerator().getPackOutput();
		var pvd = event.getLookupProvider();
		var helper = event.getExistingFileHelper();
		var gen = event.getGenerator();
		gen.addProvider(server, new MAConfigGen(gen));
	}

}
