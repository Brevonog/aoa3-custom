package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class EvermightStaff extends BaseStaff<Float> {
	public EvermightStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_EMBER_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 2);
		runes.put(AoAItems.POWER_RUNE.get(), 4);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Float args) {
		EntityUtil.applyPotions(caster, new EffectBuilder(MobEffects.DAMAGE_BOOST, (int)(1200f * (1 - args))).level(args < 0.1 ? 3 : args < 0.5 ? 2 : 1));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
