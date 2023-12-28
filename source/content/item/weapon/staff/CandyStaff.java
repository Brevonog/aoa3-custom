package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class CandyStaff extends BaseStaff<List<LivingEntity>> {
	public CandyStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CANDY_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.KINETIC_RUNE.get(), 3);
		runes.put(AoAItems.STORM_RUNE.get(), 2);
	}

	@Override
	public List<LivingEntity> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> list = EntityRetrievalUtil.getEntities(caster, 10, entity -> entity instanceof LivingEntity livingEntity && entity instanceof Enemy);

		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, List<LivingEntity> args) {
		for (LivingEntity target : args) {
			EntityUtil.pullEntityIn(caster, target, 2.5f, true);
			target.setDeltaMovement(target.getDeltaMovement().multiply(1, 0.25f, 1));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
