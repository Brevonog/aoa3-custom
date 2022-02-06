package net.tslat.aoa3.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public final class BlockUtil {
	public static final float UNBREAKABLE_HARDNESS = -1f;
	public static final float UNBREAKABLE_RESISTANCE = 999999999f;

	public static class CompactProperties {
		private final AbstractBlock.Properties properties;

		public CompactProperties(Material material, Function<BlockState, MaterialColor> mapColours) {
			this.properties = AbstractBlock.Properties.of(material, mapColours).sound(approximateSound(material));
		}

		public CompactProperties(Material material, MaterialColor mapColour) {
			this(material, state -> mapColour);
		}

		public CompactProperties harvestTool(ToolType tool, int harvestLevel) {
			harvestTool(tool);
			this.properties.harvestLevel(harvestLevel);

			return this;
		}

		public CompactProperties harvestTool(ToolType tool) {
			tool(tool);
			this.properties.requiresCorrectToolForDrops();

			return this;
		}

		public CompactProperties tool(ToolType tool) {
			this.properties.harvestTool(tool);

			return this;
		}

		public CompactProperties harvestLevel(int level) {
			this.properties.harvestLevel(level);

			return this;
		}

		public CompactProperties sound(SoundType sound) {
			this.properties.sound(sound);

			return this;
		}

		public CompactProperties stats(float hardness) {
			return stats(hardness, hardness);
		}

		public CompactProperties stats(float hardness, float resistance) {
			this.properties.strength(hardness, resistance);

			return this;
		}

		public CompactProperties unbreakable() {
			noDrops();

			return stats(UNBREAKABLE_HARDNESS, UNBREAKABLE_RESISTANCE);
		}

		public CompactProperties light(int light) {
			return light(state -> light);
		}

		public CompactProperties light(ToIntFunction<BlockState> light) {
			this.properties.lightLevel(light);

			return this;
		}

		public CompactProperties randomTicks() {
			this.properties.randomTicks();

			return this;
		}

		public CompactProperties isAir() {
			this.properties.air();

			return this;
		}

		public CompactProperties dynamicShape() {
			this.properties.dynamicShape();

			return this;
		}

		public CompactProperties slippery(float slipMod) {
			this.properties.friction(slipMod);

			return this;
		}

		public CompactProperties emissive() {
			return emissive((state, world, pos) -> true);
		}

		public CompactProperties emissive(AbstractBlock.IPositionPredicate when) {
			this.properties.emissiveRendering(when);

			return this;
		}

		public CompactProperties renderAdjust() {
			return renderAdjust((state, world, pos) -> true);
		}

		public CompactProperties renderAdjust(AbstractBlock.IPositionPredicate when) {
			this.properties.hasPostProcess(when);

			return this;
		}

		public CompactProperties moveSpeed(float speedMod) {
			this.properties.speedFactor(speedMod);

			return this;
		}

		public CompactProperties bouncy(float jumpFactor) {
			this.properties.jumpFactor(jumpFactor);

			return this;
		}

		public CompactProperties noDrops() {
			this.properties.noDrops();

			return this;
		}

		public CompactProperties noOcclusion() {
			this.properties.noOcclusion();

			return this;
		}

		public CompactProperties noClip() {
			this.properties.noCollission();
			coversScreen((state, world, pos) -> false);

			return this;
		}

		public CompactProperties coversScreen(AbstractBlock.IPositionPredicate when) {
			this.properties.isViewBlocking(when);

			return this;
		}

		public CompactProperties noRedstone() {
			return conductRedstone((state, world, pos) -> false);
		}

		public CompactProperties conductRedstone(AbstractBlock.IPositionPredicate when) {
			this.properties.isRedstoneConductor(when);

			return this;
		}

		public CompactProperties noSpawns() {
			return specialSpawns((state, world, pos, entityType) -> false);
		}

		public CompactProperties specialSpawns(AbstractBlock.IExtendedPositionPredicate<EntityType<?>> when) {
			this.properties.isValidSpawn(when);

			return this;
		}

		public CompactProperties breathable() {
			return suffocate((state, world, pos) -> false);
		}

		public CompactProperties suffocate(AbstractBlock.IPositionPredicate when) {
			this.properties.isSuffocating(when);

			return this;
		}

		public AbstractBlock.Properties get() {
			return this.properties;
		}

		private static SoundType approximateSound(Material material) {
			if (material == Material.WOOD) {
				return SoundType.WOOD;
			}
			else if (material == Material.GLASS) {
				return SoundType.GLASS;
			}
			else if (material == Material.DIRT) {
				return SoundType.GRAVEL;
			}
			else if (material == Material.PLANT || material == Material.GRASS) {
				return SoundType.GRASS;
			}
			else if (material == Material.TOP_SNOW || material == Material.SNOW) {
				return SoundType.SNOW;
			}
			else if (material == Material.SAND) {
				return SoundType.SAND;
			}
			else if (material == Material.WOOL) {
				return SoundType.WOOL;
			}
			else if (material == Material.METAL || material == Material.HEAVY_METAL) {
				return SoundType.METAL;
			}
			else {
				return SoundType.STONE;
			}
		}
	}

	public static Vector3d posToVec(BlockPos pos) {
		return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
	}

	public static boolean isBlockTaggedAs(Block block, ITag<Block>... tags) {
		for (ITag<Block> tag : tags) {
			if (block.is(tag))
				return true;
		}

		return false;
	}

	public static boolean isAirBlock(BlockState state) {
		return state.getMaterial() == Material.AIR || state.getMaterial() == Material.STRUCTURAL_AIR;
	}

	public static class SpawnerBuilder {
		private short initialSpawnDelay = 20;
		private short minSpawnDelay = 200;
		private short maxSpawnDelay = 800;

		private short spawnsPerGroup = 4;
		private short maxNearbyEntities = 6;
		private short requiredPlayerRange = 16;
		private short spawnRange = 4;

		private WeightedSpawnerEntity nextSpawn = null;
		private final ArrayList<WeightedSpawnerEntity> mobs = new ArrayList<WeightedSpawnerEntity>(1);

		public SpawnerBuilder initialSpawnDelay(int ticks) {
			this.initialSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder minSpawnDelay(int ticks) {
			this.minSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder maxSpawnDelay(int ticks) {
			this.maxSpawnDelay = (short)ticks;

			return this;
		}

		public SpawnerBuilder spawnsPerGroup(int amount) {
			this.spawnsPerGroup = (short)amount;

			return this;
		}

		public SpawnerBuilder maxNearbyEntities(int amount) {
			this.maxNearbyEntities = (short)amount;

			return this;
		}

		public SpawnerBuilder whenPlayerWithin(int distance) {
			this.requiredPlayerRange = (short)distance;

			return this;
		}

		public SpawnerBuilder spawnWithinXBlocks(int distance) {
			this.spawnRange = (short)distance;

			return this;
		}

		public SpawnerBuilder withSpawns(EntityType<?>... entities) {
			for (EntityType<?> entity : entities) {
				withSpawn(1, entity);
			}

			return this;
		}

		public SpawnerBuilder withSpawns(RegistryObject<EntityType<?>>... entities) {
			for (RegistryObject<EntityType<?>> registryEntry : entities) {
				withSpawn(1, registryEntry.get());
			}

			return this;
		}

		public SpawnerBuilder withSpawn(int weight, EntityType<?> entity) {
			CompoundNBT tag = new CompoundNBT();

			tag.putString("id", entity.getRegistryName().toString());

			return withSpawn(weight, tag);
		}

		public SpawnerBuilder withSpawn(int weight, CompoundNBT nbt) {
			if (this.nextSpawn == null) {
				this.nextSpawn = new WeightedSpawnerEntity(weight, nbt);

				return this;
			}

			this.mobs.add(new WeightedSpawnerEntity(weight, nbt));

			return this;
		}

		public CompoundNBT build() {
			CompoundNBT nbt = new CompoundNBT();

			if (nextSpawn == null)
				throw new IllegalStateException("Attempted to create spawner data with no mobs listed.");

			nbt.putShort("Delay", this.initialSpawnDelay);
			nbt.putShort("MinSpawnDelay", this.minSpawnDelay);
			nbt.putShort("MaxSpawnDelay", this.maxSpawnDelay);
			nbt.putShort("SpawnCount", this.spawnsPerGroup);
			nbt.putShort("MaxNearbyEntities", this.maxNearbyEntities);
			nbt.putShort("RequiredPlayerRange", this.requiredPlayerRange);
			nbt.putShort("SpawnRange", this.spawnRange);
			nbt.put("SpawnData", this.nextSpawn.getTag().copy());

			ListNBT mobs = new ListNBT();

			if (this.mobs.isEmpty()) {
				mobs.add(this.nextSpawn.save());
			}
			else {
				for (WeightedSpawnerEntity entry : this.mobs) {
					mobs.add(entry.save());
				}
			}
			nbt.put("SpawnPotentials", mobs);

			return nbt;
		}
	}

	public static VoxelShape pixelBasedCube(int minPixelX, int minPixelY, int minPixelZ, int maxPixelX, int maxPixelY, int maxPixelZ) {
		return VoxelShapes.create(new AxisAlignedBB(minPixelX / 16d, minPixelY / 16d, minPixelZ / 16d, maxPixelX / 16d, maxPixelY / 16d, maxPixelZ / 16d));
	}

	public static boolean canPlayerHarvest(BlockState state, PlayerEntity player, IBlockReader world, BlockPos pos) {
		if (!state.requiresCorrectToolForDrops())
			return true;

		ItemStack toolStack = player.getMainHandItem();
		ToolType harvestTool = state.getHarvestTool();
		int toolLevel = toolStack.getHarvestLevel(harvestTool, player, state);

		if (toolStack.isEmpty() || harvestTool == null || toolLevel < 0)
			return player.inventory.getSelected().isCorrectToolForDrops(state);

		return toolLevel >= state.getHarvestLevel();
	}
}
