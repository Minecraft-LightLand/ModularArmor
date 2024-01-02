package dev.xkmc.modulararmor.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ModularArmor.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModularArmorClient {

	@SubscribeEvent
	public static void client(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
		});
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
	}


	@SubscribeEvent
	public static void onResourceReload(RegisterClientReloadListenersEvent event) {
	}

	@SubscribeEvent
	public static void registerOverlay(RegisterGuiOverlaysEvent event) {
	}

}
