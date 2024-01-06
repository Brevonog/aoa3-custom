package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireborneSword extends BaseSword {
	public FireborneSword() {
		super(AoATiers.FIREBORNE);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, float baseDamage) {
		if (baseDamage > 0)
			target.setSecondsOnFire((int)(3 * baseDamage / getDamage()));

		return baseDamage;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
