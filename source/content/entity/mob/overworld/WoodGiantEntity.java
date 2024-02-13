package net.tslat.aoa3.content.entity.mob.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.library.constant.ScreenImageEffect;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;
import java.util.UUID;

public class WoodGiantEntity extends AoAMeleeMob<WoodGiantEntity> {
	public static final EntityDataHolder<Integer> STAGE = EntityDataHolder.register(WoodGiantEntity.class, EntityDataSerializers.INT, 0, WoodGiantEntity::getStage, WoodGiantEntity::setStage);
	private final AttributeModifier STAGE_ARMOUR_MOD = new AttributeModifier(UUID.fromString("076a790a-a765-4313-b517-527b758e839f"), "StageArmourModifier", 1, AttributeModifier.Operation.ADDITION) {
		@Override
		public double getAmount() {
			return 35 - (Math.max(0, getStage() + 1) * 10);
		}
	};
	private final AttributeModifier STAGE_TOUGHNESS_MOD = new AttributeModifier(UUID.fromString("3977cd08-2bbe-48ed-b66e-557ada8565b9"), "StageToughnessModifier", 1, AttributeModifier.Operation.ADDITION) {
		@Override
		public double getAmount() {
			return 50 - (Math.max(0, getStage() + 1) * 15);
		}
	};

	private int lastMeleeHit = 0;
	private int stage = 0;

	public WoodGiantEntity(EntityType<? extends WoodGiantEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected Brain.Provider<WoodGiantEntity> brainProvider() { // TODO
		return Brain.provider(List.of(MemoryModuleType.ATTACK_TARGET), ImmutableList.of());
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new TelegraphedMeleeAttackGoal<>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(STAGE);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		EntityUtil.applyAttributeModifierSafely(this, Attributes.ARMOR, STAGE_ARMOUR_MOD, false);
		EntityUtil.applyAttributeModifierSafely(this, Attributes.ARMOR_TOUGHNESS, STAGE_TOUGHNESS_MOD, false);

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 2.875f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.TREE_FALL.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.WOODY_HIT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.WOODY_THUMP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	protected void onHurt(DamageSource source, float amount) {
		if (level() instanceof ServerLevel level && DamageUtil.isMeleeDamage(source)) {
			lastMeleeHit = tickCount;

			if (source.getEntity() instanceof LivingEntity attacker) {
				TELParticlePacket particlePacket = new TELParticlePacket();
				ItemStack weapon = attacker.getItemInHand(InteractionHand.MAIN_HAND);

				if (weapon.isCorrectToolForDrops(Blocks.OAK_LOG.defaultBlockState())) {
					lastMeleeHit += 100;

					particlePacket.particle(ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_LOG.defaultBlockState()), this).spawnNTimes(10));

					if (getHealth() <= 0 && attacker instanceof ServerPlayer pl)
						pl.getAdvancements().award(AdvancementUtil.getAdvancement(level, AdventOfAscension.id("i_axed_you_a_question")), "tool_kill");

				}

				if (getStage() < 3) {
					STAGE.set(this, getStage() + 1);
					particlePacket.particle(ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_LOG.defaultBlockState()), this).spawnNTimes(5));

					if (!(attacker instanceof ServerPlayer pl) || !pl.isCreative()) {
						if (attacker instanceof ServerPlayer pl)
							new ScreenImageEffect(ScreenImageEffect.Type.BLOOD).duration(80).randomScale().coloured(255, 0, 0, 127).sendToPlayer(pl);

						attacker.addEffect(new EffectBuilder(AoAMobEffects.BLEEDING.get(), 600).hideParticles().build(), this);
					}

					new SoundBuilder(AoASounds.HEAVY_WOOD_SHATTER.get()).followEntity(this).isMonster().execute();
				}

				particlePacket.sendToAllNearbyPlayers((ServerLevel)level(), position(), 20);
			}
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("WoodStage"))
			STAGE.set(this, compound.getInt("WoodStage"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putInt("WoodStage", STAGE.get(this));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (tickCount - lastMeleeHit > 600) {
			int stage = getStage();
			lastMeleeHit = tickCount;

			if (stage > 0 && stage <= 3) {
				STAGE.set(this, stage - 1);
				heal(20);
			}
		}
	}

	private void setStage(int stage) {
		this.stage = stage;

		if (!level().isClientSide()) {
			int oldStage = getStage();

			EntityUtil.reapplyAttributeModifier(this, Attributes.ARMOR, STAGE_ARMOUR_MOD, false);
			EntityUtil.reapplyAttributeModifier(this, Attributes.ARMOR_TOUGHNESS, STAGE_TOUGHNESS_MOD, false);

			if (oldStage < stage) {
				ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_LOG.defaultBlockState()), this)
						.spawnNTimes(10)
						.sendToAllNearbyPlayers((ServerLevel)level(), position(), 20);
			}
		}
	}

	public int getStage() {
		return this.stage;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SLAM));
	}
}
