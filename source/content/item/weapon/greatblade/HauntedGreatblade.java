package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HauntedGreatblade extends BaseGreatblade {
	public HauntedGreatblade() {
		super(AoATiers.HAUNTED_GREATBLADE);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (!world.isClientSide && isSelected && entity instanceof Player && RandomUtil.oneInNChance(12000)) {
			MobEffect effect = MobEffect.byId(RandomUtil.randomNumberUpTo(ForgeRegistries.POTIONS.getValues().size()));

			while (effect == null) {
				effect = MobEffect.byId(RandomUtil.randomNumberUpTo(ForgeRegistries.POTIONS.getValues().size()));
			}

			((Player)entity).addEffect(new MobEffectInstance(effect, 600, 0, false, true));

			MutableComponent component = LocaleUtil.getLocaleMessage("item.aoa3.haunted_greatblade.message." + RandomUtil.randomNumberBetween(1, 16), ChatFormatting.DARK_PURPLE);

			component.getStyle().withItalic(true);
			entity.sendSystemMessage(component);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
