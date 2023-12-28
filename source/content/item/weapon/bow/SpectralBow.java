package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpectralBow extends BaseBow {
	public SpectralBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	protected ItemStack findAmmo(Player shooter, ItemStack bowStack, boolean infiniteAmmo) {
		return new ItemStack(Items.ARROW);
	}

	@Override
	public CustomArrowEntity doArrowMods(CustomArrowEntity arrow, LivingEntity shooter, ItemStack ammoStack, int useTicksRemaining) {
		arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

		return arrow;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getDamage()))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BOW_DRAW_TIME, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((int)(72000 / getDrawSpeedMultiplier()) / 720) / (double)100))));
	}
}
