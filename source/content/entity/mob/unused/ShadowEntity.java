package net.tslat.aoa3.content.entity.mob.unused;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

public class ShadowEntity extends AoAMeleeMob<ShadowEntity> {
	public ShadowEntity(EntityType<? extends ShadowEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.5f;
	}/*

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_SHADOW_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SHADOW_HURT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SHADOW_HURT.get();
	}*/

	@Override
	public void die(DamageSource source) {
		super.die(source);
		transform();
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	private void transform() {
		/*if (!level().isClientSide) {
			ShadeEntity shade = new ShadeEntity(AoAMobs.SHADE.get(), level());

			shade.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
			level().addFreshEntity(shade);
		}

		this.discard();*/
	}

}
