package dev.xkmc.modulararmor.content.core;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2library.util.math.MathHelper;
import dev.xkmc.modulararmor.content.config.MaterialData;
import dev.xkmc.modulararmor.content.modifier.ArmorModifier;
import dev.xkmc.modulararmor.init.ModularArmor;
import dev.xkmc.modulararmor.init.registrate.MATypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ArmorCapability implements IArmorCapability {

	public static final Capability<IArmorCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	private static final UUID[][] UUIDS;

	static {
		UUIDS = new UUID[EquipmentSlot.values().length][AttributeModifier.Operation.values().length];
		for (var e : EquipmentSlot.values()) {
			for (var op : AttributeModifier.Operation.values()) {
				UUIDS[e.ordinal()][op.ordinal()] = MathHelper.getUUIDFromString(ModularArmor.MODID + ":" + e.getName() + "_" + op.name());
			}
		}
	}

	private final ItemStack stack;
	private final EquipmentSlot slot;
	private final Map<ArmorPartType, ResourceLocation> data;

	private Data cache;

	public ArmorCapability(ItemStack stack) {
		this.stack = stack;
		slot = ((Equipable) stack.getItem()).getEquipmentSlot();
		data = ModularArmorItem.getMaterials(stack);
		check();
	}

	private void check() {
		int hash = L2DamageTracker.ARMOR.getMerged().hashCode();
		if (cache != null && cache.hash == hash) return;
		Map<ArmorModifier, Integer> mod = new LinkedHashMap<>();
		Map<ResourceLocation, Accumulator> attr = new LinkedHashMap<>();
		for (var e : data.entrySet()) {
			var data = MaterialData.get(e.getValue(), e.getKey());
			if (data == null) continue;
			for (var ent : data.modifiers.entrySet()) {
				mod.compute(ent.getKey(), (k, v) -> (v == null ? 0 : v) + ent.getValue());
			}
			for (var ent : data.getAttributes().entrySet()) {
				attr.computeIfAbsent(ent.getKey().attr, k -> new Accumulator()).accept(ent.getKey().op, ent.getValue());
			}
		}
		int dur = (int) Math.round(attr.getOrDefault(MATypes.DURABILITY.getId(), new Accumulator()).getValue());
		int ench = (int) Math.round(attr.getOrDefault(MATypes.ENCHANTABILITY.getId(), new Accumulator()).getValue());
		HashMultimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
		for (var ent : attr.entrySet()) {
			if (!ForgeRegistries.ATTRIBUTES.containsKey(ent.getKey())) continue;
			var attribute = ForgeRegistries.ATTRIBUTES.getValue(ent.getKey());
			assert attribute != null;
			ent.getValue().formulate(attributes, slot, attribute);
		}
		cache = new Data(hash, dur, ench, mod, attributes);
	}

	@Override
	public int getEnchantability() {
		check();
		return cache.enchantability();
	}

	@Override
	public int getDurability() {
		check();
		return cache.durability();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
		check();
		return cache.attributes();
	}

	@Override
	public int getModifierLevel(ArmorModifier modifier) {
		check();
		return cache.modifiers.getOrDefault(modifier, 0);
	}

	public Map<ArmorModifier, Integer> getModifiers(){
		check();
		return cache.modifiers;
	}

	private record Data(int hash, int durability, int enchantability,
						Map<ArmorModifier, Integer> modifiers,
						Multimap<Attribute, AttributeModifier> attributes) {

	}

	private static class Accumulator {

		private double add = 0, base = 0, total = 1;

		public void accept(AttributeModifier.Operation op, double value) {
			switch (op) {
				case ADDITION -> add += value;
				case MULTIPLY_BASE -> base += value;
				case MULTIPLY_TOTAL -> total *= 1 + value;
			}
		}

		public double getValue() {
			return add * (1 + base) * total;
		}


		public void formulate(HashMultimap<Attribute, AttributeModifier> attributes, EquipmentSlot slot, Attribute attribute) {
			if (add != 0)
				attributes.put(attribute, formulate(slot, AttributeModifier.Operation.ADDITION, add));
			if (base != 0)
				attributes.put(attribute, formulate(slot, AttributeModifier.Operation.MULTIPLY_BASE, base));
			if (total != 1)
				attributes.put(attribute, formulate(slot, AttributeModifier.Operation.MULTIPLY_TOTAL, total - 1));
		}

		private AttributeModifier formulate(EquipmentSlot slot, AttributeModifier.Operation op, double val) {
			String name = ModularArmor.MODID + ":" + slot.name().toLowerCase(Locale.ROOT) + "_" + op.name().toLowerCase(Locale.ROOT);
			return new AttributeModifier(UUIDS[slot.ordinal()][op.ordinal()], name, val, op);
		}
	}

}
