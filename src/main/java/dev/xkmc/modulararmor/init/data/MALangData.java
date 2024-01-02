package dev.xkmc.modulararmor.init.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.xkmc.modulararmor.init.ModularArmor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Locale;

public enum MALangData {
	;

	final String id, def;
	final int count;

	MALangData(String id, String def, int count) {
		this.id = id;
		this.def = def;
		this.count = count;
	}

	public MutableComponent get(Object... objs) {
		if (objs.length != count)
			throw new IllegalArgumentException("for " + name() + ": expect " + count + " parameters, got " + objs.length);
		return translate(ModularArmor.MODID + "." + id, objs);
	}


	public static void addTranslations(RegistrateLangProvider pvd) {
		for (MALangData id : MALangData.values()) {
			String[] strs = id.id.split("\\.");
			String str = strs[strs.length - 1];
			pvd.add(ModularArmor.MODID + "." + id.id, id.def);
		}
	}

	public static String asId(String name) {
		return name.toLowerCase(Locale.ROOT);
	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

}
