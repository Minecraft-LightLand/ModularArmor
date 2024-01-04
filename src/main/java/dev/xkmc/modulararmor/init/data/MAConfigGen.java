package dev.xkmc.modulararmor.init.data;

import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import dev.xkmc.modulararmor.content.config.ArmorAttribute;
import dev.xkmc.modulararmor.content.config.ArmorType;
import dev.xkmc.modulararmor.content.config.MaterialData;
import dev.xkmc.modulararmor.content.core.ArmorPartType;
import dev.xkmc.modulararmor.content.core.ArmorSpecialAttribute;
import dev.xkmc.modulararmor.init.ModularArmor;
import dev.xkmc.modulararmor.init.registrate.MAItems;
import dev.xkmc.modulararmor.init.registrate.MAModifiers;
import dev.xkmc.modulararmor.init.registrate.MATypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MAConfigGen extends ConfigDataProvider {

	public final AttrTypeGen DMG_DEC = AttrTypeGen.of("damage_reduction",
			MATypes.DAMAGE_REDUCTION.get(), AttributeModifier.Operation.ADDITION);
	public final AttrTypeGen DUR_BASE = AttrTypeGen.of("durability_base",
			MATypes.DURABILITY.get(), AttributeModifier.Operation.ADDITION);
	public final AttrTypeGen DUR_MULT = AttrTypeGen.of("durability_multiplier",
			MATypes.DURABILITY.get(), AttributeModifier.Operation.MULTIPLY_BASE);
	public final AttrTypeGen ENCH = AttrTypeGen.of("enchantability",
			MATypes.ENCHANTABILITY.get(), AttributeModifier.Operation.ADDITION);
	public final AttrTypeGen ARMOR = AttrTypeGen.of("armor",
			Attributes.ARMOR, AttributeModifier.Operation.ADDITION);
	public final AttrTypeGen TOUGH = AttrTypeGen.of("toughness",
			Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADDITION);
	public final AttrTypeGen KBRES = AttrTypeGen.of("knockback_resistance",
			Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADDITION);

	public MAConfigGen(DataGenerator generator) {
		super(generator, "Modular Armor Config");
	}

	@Override
	public void add(Collector collector) {

		for (var e : AttrTypeGen.LIST) {
			collector.add(ModularArmor.ATTRIBUTE, e.id(), e.val());
		}

		MatTypeGen wool = MatTypeGen.of("wool");
		MatTypeGen leather = MatTypeGen.of(Items.LEATHER);
		MatTypeGen rabbit = MatTypeGen.of(Items.RABBIT_HIDE);
		MatTypeGen vine = MatTypeGen.of(Items.VINE);
		MatTypeGen nether_vine = MatTypeGen.of("nether_vine");
		MatTypeGen chain = MatTypeGen.of("chainmail");
		MatTypeGen copper = MatTypeGen.of("copper");
		MatTypeGen iron = MatTypeGen.of("iron");
		MatTypeGen turtle = MatTypeGen.of("turtle");
		MatTypeGen gold = MatTypeGen.of("gold");
		MatTypeGen diamond = MatTypeGen.of("diamond");
		MatTypeGen netherite = MatTypeGen.of("netherite");
		MatTypeGen lapis = MatTypeGen.of(Items.LAPIS_BLOCK);
		MatTypeGen amethyst = MatTypeGen.of(Items.AMETHYST_SHARD);

		collector.add(ModularArmor.ATTRIBUTE, DMG_DEC.id(), DMG_DEC.val);

		addMat(collector, wool, MATypes.LINING.get(), new MaterialData());
		addMat(collector, leather, MATypes.LINING.get(), new MaterialData().withAttr(DMG_DEC, 0.15));
		addMat(collector, rabbit, MATypes.LINING.get(), new MaterialData().withAttr(DMG_DEC, 0.25));

		addMat(collector, wool, MATypes.VEST.get(), new MaterialData());
		addMat(collector, vine, MATypes.VEST.get(), new MaterialData().withAttr(DUR_MULT, 0.5));
		addMat(collector, leather, MATypes.VEST.get(), new MaterialData()
				.withAttr(DUR_MULT, 1).withAttr(ARMOR, 0.5));
		addMat(collector, nether_vine, MATypes.VEST.get(), new MaterialData()
				.withAttr(DUR_MULT, 1).withAttr(ARMOR, 1));
		addMat(collector, chain, MATypes.VEST.get(), new MaterialData()
				.withAttr(DUR_MULT, 2).withAttr(ARMOR, 2));

		addMat(collector, netherite, MATypes.TRIM.get(), new MaterialData()
				.withAttr(TOUGH, 1).withAttr(DUR_MULT, 0.2).withAttr(KBRES, 0.1).withAttr(ENCH, 5));
		addMat(collector, gold, MATypes.TRIM.get(), new MaterialData()
				.withModifier(MAModifiers.GOLD.get(), 1).withAttr(ENCH, 15));

		addMat(collector, turtle, MATypes.MASK.get(), new MaterialData()
				.withModifier(MAModifiers.BREATHING.get(), 1));

		addArmor(collector, leather, 5, 15, 1, 3, 2, 1, e -> e);
		addArmor(collector, copper, 10, 6, 1.5, 5, 3, 1.5, e -> e);
		addArmor(collector, iron, 15, 9, 2, 6, 5, 2, e -> e);
		addArmor(collector, turtle, 25, 9, 2, 6, 5, 2,
				e -> e.withModifier(MAModifiers.TURTLE.get(), 1));
		addArmor(collector, gold, 7, 25, 2, 5, 3, 1,
				e -> e.withModifier(MAModifiers.GOLD.get(), 1));
		addArmor(collector, diamond, 33, 10, 3, 8, 6, 3,
				e -> e.withAttr(TOUGH, 2));

		collector.add(ModularArmor.TYPE, MAItems.HELMET.getId(), new ArmorType()
				.requires(MATypes.LINING.get(), MATypes.VEST.get(), MATypes.HELMET.get())
				.optional(MATypes.TRIM.get(), MATypes.DECO.get(), MATypes.MASK.get()));

		collector.add(ModularArmor.TYPE, MAItems.CHESTPLATE.getId(), new ArmorType()
				.requires(MATypes.LINING.get(), MATypes.VEST.get(), MATypes.CHESTPLATE.get())
				.optional(MATypes.TRIM.get(), MATypes.DECO.get()));

		collector.add(ModularArmor.TYPE, MAItems.LEGGINGS.getId(), new ArmorType()
				.requires(MATypes.LINING.get(), MATypes.VEST.get(), MATypes.LEGGINGS.get())
				.optional(MATypes.TRIM.get(), MATypes.DECO.get()));

		collector.add(ModularArmor.TYPE, MAItems.BOOTS.getId(), new ArmorType()
				.requires(MATypes.LINING.get(), MATypes.VEST.get(), MATypes.BOOTS.get())
				.optional(MATypes.TRIM.get(), MATypes.DECO.get(), MATypes.SOLE.get()));

	}

	private void addArmor(Collector collector, MatTypeGen mat, int dur, int ench,
						  double helmet, double chestplate, double leggings, double boots, Function<MaterialData, MaterialData> cons) {
		addMat(collector, mat, MATypes.HELMET.get(), cons.apply(new MaterialData()
				.withAttr(DUR_BASE, dur * 11).withAttr(ENCH, ench).withAttr(ARMOR, helmet)));
		addMat(collector, mat, MATypes.CHESTPLATE.get(), cons.apply(new MaterialData()
				.withAttr(DUR_BASE, dur * 16).withAttr(ENCH, ench).withAttr(ARMOR, chestplate)));
		addMat(collector, mat, MATypes.LEGGINGS.get(), cons.apply(new MaterialData()
				.withAttr(DUR_BASE, dur * 15).withAttr(ENCH, ench).withAttr(ARMOR, leggings)));
		addMat(collector, mat, MATypes.BOOTS.get(), cons.apply(new MaterialData()
				.withAttr(DUR_BASE, dur * 13).withAttr(ENCH, ench).withAttr(ARMOR, boots)));
	}

	private static void addMat(Collector collector, MatTypeGen mat, ArmorPartType type, MaterialData data) {
		collector.add(ModularArmor.MATERIAL, mat.id.withPrefix(type.getRegistryName().getPath() + "/"), data);
	}

	private record MatTypeGen(ResourceLocation id) {

		public static MatTypeGen of(String id) {
			return new MatTypeGen(new ResourceLocation(ModularArmor.MODID, id));
		}

		public static MatTypeGen of(Item item) {
			ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
			assert id != null;
			String namespace = id.getNamespace();
			if (namespace.equals("minecraft")) {
				namespace = ModularArmor.MODID;
			}
			return new MatTypeGen(new ResourceLocation(namespace, id.getPath()));
		}

	}

	public record AttrTypeGen(ResourceLocation id, ArmorAttribute val) {

		private static final List<AttrTypeGen> LIST = new ArrayList<>();

		private static AttrTypeGen rec(AttrTypeGen gen) {
			LIST.add(gen);
			return gen;
		}

		public static AttrTypeGen of(String id, Attribute attr, AttributeModifier.Operation op) {
			ResourceLocation rl = ForgeRegistries.ATTRIBUTES.getKey(attr);
			assert rl != null;
			return rec(new AttrTypeGen(new ResourceLocation(ModularArmor.MODID, id),
					new ArmorAttribute(rl, op)));
		}

		public static AttrTypeGen of(String id, ArmorSpecialAttribute attr, AttributeModifier.Operation op) {
			ResourceLocation rl = MATypes.ATTRIBUTE.get().getKey(attr);
			assert rl != null;
			return rec(new AttrTypeGen(new ResourceLocation(ModularArmor.MODID, id),
					new ArmorAttribute(rl, op)));
		}

	}

}