package net.tslat.aoa3.common.registration.entity;

import net.minecraft.SharedConstants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.misc.*;
import net.tslat.aoa3.content.entity.mob.misc.ThornyPlantSproutEntity;

import java.util.function.Consumer;

public final class AoAMiscEntities {
	public static void init() {}

	public static final RegistryObject<EntityType<GyrocopterEntity>> GYROCOPTER = registerMiscEntity("gyrocopter", GyrocopterEntity::new, 1.375f, 1.625f, EntityType.Builder::noSummon);
	public static final RegistryObject<EntityType<LottoTotemEntity>> LOTTO_TOTEM = registerMiscEntity("lotto_totem", LottoTotemEntity::new, 0.75f, 0.95f, EntityType.Builder::fireImmune);
	public static final RegistryObject<EntityType<SandGiantPitTrapEntity>> SAND_GIANT_PIT_TRAP = registerMiscEntity("sand_giant_pit_trap", SandGiantPitTrapEntity::new, 0.875f, 0.375f, builder -> builder.noSave().noSummon().updateInterval(1).fireImmune());
	public static final RegistryObject<EntityType<SandGiantSpikeTrapEntity>> SAND_GIANT_SPIKE_TRAP = registerMiscEntity("sand_giant_spike_trap", SandGiantSpikeTrapEntity::new, 1.1f, 0.875f, builder -> builder.noSave().noSummon().updateInterval(1).fireImmune());

	public static final RegistryObject<EntityType<ThornyPlantSproutEntity>> THORNY_PLANT_SPROUT = registerMiscEntity("thorny_plant_sprout", ThornyPlantSproutEntity::new, 0.5f, 1.5f, EntityType.Builder::noSummon);

	public static final RegistryObject<EntityType<HaulingFishingBobberEntity>> REINFORCED_BOBBER = registerMiscEntity("reinforced_bobber", EntityType.Builder.<HaulingFishingBobberEntity>of(HaulingFishingBobberEntity::new, MobCategory.MISC).noSave().noSummon().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5));
	public static final RegistryObject<EntityType<ThermalFishingBobberEntity>> THERMAL_BOBBER = registerMiscEntity("thermal_bobber", EntityType.Builder.<ThermalFishingBobberEntity>of(ThermalFishingBobberEntity::new, MobCategory.MISC).noSave().noSummon().sized(0.25f, 0.25f).clientTrackingRange(4).fireImmune().updateInterval(5));
	public static final RegistryObject<EntityType<GoldFishingBobberEntity>> GOLD_BOBBER = registerMiscEntity("gold_bobber", EntityType.Builder.<GoldFishingBobberEntity>of(GoldFishingBobberEntity::new, MobCategory.MISC).noSave().noSummon().sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(5));
	public static final RegistryObject<EntityType<FishingCageEntity>> FISHING_CAGE = registerMiscEntity("fishing_cage", FishingCageEntity::new, 0.65f, 0.63f, builder -> builder.noSummon().updateInterval(5));

	public static final RegistryObject<EntityType<CustomisableLightningBolt>> CUSTOMISABLE_LIGHTNING_BOLT = registerMiscEntity("customisable_lightning_bolt", CustomisableLightningBolt::new, 0, 0, builder -> builder.noSave().clientTrackingRange(16).updateInterval(Integer.MAX_VALUE));

	private static <T extends Entity> RegistryObject<EntityType<T>> registerMiscEntity(String registryName, EntityType.EntityFactory<T> factory, float width, float height) {
		return registerMiscEntity(registryName, factory, width, height, typeBuilder -> {});
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerMiscEntity(String registryName, EntityType.EntityFactory<T> factory, float width, float height, Consumer<EntityType.Builder<T>> typeBuilderConsumer) {
		EntityType.Builder<T> typeBuilder = EntityType.Builder.of(factory, MobCategory.MISC).sized(width, height).setTrackingRange(40).setUpdateInterval(1);

		typeBuilderConsumer.accept(typeBuilder);

		return registerMiscEntity(registryName, typeBuilder);
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerMiscEntity(String registryName, EntityType.Builder<T> typeBuilder) {
		return AoARegistries.ENTITIES.register(registryName, () -> {
			boolean dataFixers = SharedConstants.CHECK_DATA_FIXER_SCHEMA;
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;
			EntityType<T> entityType = typeBuilder.build(registryName);
			SharedConstants.CHECK_DATA_FIXER_SCHEMA = dataFixers;

			return entityType;
		});
	}
}
