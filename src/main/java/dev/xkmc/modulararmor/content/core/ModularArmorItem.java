package dev.xkmc.modulararmor.content.core;

import com.google.common.collect.Multimap;
import dev.xkmc.modulararmor.content.config.MaterialData;
import dev.xkmc.modulararmor.init.registrate.MAModifiers;
import dev.xkmc.modulararmor.init.registrate.MATypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ModularArmorItem extends FakeArmorItem {

	public static final String MATERIALS = "ModularArmorMaterials";

	public static Map<ArmorPartType, ResourceLocation> getMaterials(ItemStack stack) {
		var ans = new LinkedHashMap<ArmorPartType, ResourceLocation>();
		var tag = stack.getTag();
		if (tag == null) return ans;
		var root = tag.getCompound(MATERIALS);
		for (var key : root.getAllKeys()) {
			var data = new ResourceLocation(root.getString(key));
			var part = MATypes.TYPE.get().getValue(new ResourceLocation(key));
			if (part == null) continue;
			var mat = MaterialData.get(data, part);
			if (mat == null) continue;
			ans.put(part, data);
		}
		return ans;
	}

	public ModularArmorItem(Type slot, Properties prop) {
		super(slot, prop);
	}

	public IArmorCapability getCap(ItemStack stack) {
		return stack.getCapability(ArmorCapability.CAPABILITY).resolve().orElse(IArmorCapability.FAKE);
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return getCap(stack).getEnchantability();
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return getCap(stack).getDurability();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		return slot == getEquipmentSlot() ? getCap(stack).getAttributeModifiers() : super.getAttributeModifiers(slot, stack);
	}

	@Override
	public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
		return getCap(stack).getModifierLevel(MAModifiers.GOLD.get()) > 0;
	}

	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		getCap(stack).getModifiers().forEach((k, v) -> k.onArmorTick(player, stack, v));
		super.onArmorTick(stack, level, player);
	}

	@Override
	public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		return new MACapPvd(stack);
	}
}
