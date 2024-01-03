package dev.xkmc.modulararmor.content.core;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record MACapPvd(ItemStack stack) implements ICapabilityProvider {

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
		if (capability == ArmorCapability.CAPABILITY) {
			return LazyOptional.of(() -> new ArmorCapability(stack)).cast();
		}
		return LazyOptional.empty();
	}

}
