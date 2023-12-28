package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LunarGreatblade extends BaseGreatblade {
	private final double maxDmg;

	public LunarGreatblade() {
		super(AoATiers.LUNAR_GREATBLADE);

		this.maxDmg = getTier().getAttackDamageBonus() * 3;
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, float baseDamage) {
		return super.getDamageForAttack(target, attacker, swordStack, baseDamage + (float)RandomUtil.randomValueUpTo(maxDmg - Math.min(baseDamage, maxDmg)) * getSwingEffectiveness(swordStack));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getDamage() + 1)), Component.literal(Double.toString(maxDmg))));
	}
}
