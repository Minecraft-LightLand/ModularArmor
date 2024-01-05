package dev.xkmc.modulararmor.content.block;

import dev.xkmc.l2library.base.menu.base.BaseContainerMenu;
import dev.xkmc.l2library.base.menu.base.SpriteManager;
import dev.xkmc.modulararmor.init.ModularArmor;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public class ArmorForgeMenu extends BaseContainerMenu<ArmorForgeMenu> {

	public static final SpriteManager MANAGER = new SpriteManager(ModularArmor.MODID, "armor_forge");

	private final ArmorSlotLayout layout = new ArmorSlotLayout();

	protected ArmorForgeMenu(MenuType<?> type, int wid, Inventory plInv) {
		super(type, wid, plInv, MANAGER, e -> new BaseContainer<>(8, e), true);
		addSlot("materials", layout::createSlot);
		addSlot("old", layout::createOldSlot);
		addSlot("result", e -> false);
	}

	@Override
	protected void securedServerSlotChange(Container cont) {
		super.securedServerSlotChange(cont);
	}

	protected boolean shouldClear(Container container, int slot) {
		return slot < 7;
	}


}
