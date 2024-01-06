package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.entity.PartEntity;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityHandles;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.content.entity.projectile.blaster.IroMinerShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class IroMiner extends BaseBlaster {
	public IroMiner(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_MOON_SHINER_FIRE.get();
	}

	@Override
	public void fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster) {
		shooter.level().addFreshEntity(new IroMinerShotEntity(shooter, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		ItemStack heldStack = shooter.getMainHandItem();
		float damageMod = 1;

		if (heldStack.getItem() == this) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(heldStack, null);
			UUID targetUUID = target instanceof PartEntity<?> partEntity ? partEntity.getParent().getUUID() : target.getUUID();

			if (cap.getObject() != null && targetUUID.equals(cap.getObject())) {
				damageMod = cap.getValue() + 0.02f;
				cap.setValue(damageMod);
			}
			else {
				cap.setObject(targetUUID);
				cap.setValue(1.0f);
			}
		}

		return DamageUtil.doEnergyProjectileAttack(shooter, shot, target, (float)getDamage() * damageMod);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		return new VolatileStackCapabilityProvider();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
