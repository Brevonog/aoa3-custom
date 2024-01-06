package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaronArmour extends AdventArmour {
	public BaronArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:baron", 150, new int[] {4, 6, 9, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public Type getSetType() {
		return Type.BARON;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.baron_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
