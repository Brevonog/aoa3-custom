package net.tslat.aoa3.object.block.functional.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import java.util.Random;
import java.util.function.Supplier;

public abstract class HangingCropBlock extends CropBlock {
	public HangingCropBlock(MaterialColor colour, Supplier<Item> seedItem) {
		super(colour, seedItem);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
		return state.is(BlockTags.LEAVES);
	}

	@Override
	public abstract VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context);

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!world.isAreaLoaded(pos, 1))
			return;

		if (world.getRawBrightness(pos, 0) >= 9) {
			int age = this.getAge(state);

			if (age < this.getMaxAge()) {
				float growthMod = getGrowthMod(this, world, pos);

				if (ForgeHooks.onCropsGrowPre(world, pos, state, random.nextInt((int)(25.0F / growthMod) + 1) == 0)) {
					world.setBlock(pos, this.getStateForAge(age + 1), 2);
					ForgeHooks.onCropsGrowPost(world, pos, state);
				}
			}
		}
	}

	public float getGrowthMod(Block block, IBlockReader level, BlockPos pos) {
		float modifier = 1;
		BlockPos.Mutable testPos = new BlockPos.Mutable();

		for(int x = -1; x <= 1; ++x) {
			for(int z = -1; z <= 1; ++z) {
				float adjacentMod = 0;
				BlockState blockstate = level.getBlockState(testPos.set(pos.getX() + x, pos.getY() + 1, pos.getZ() + z));

				if (blockstate.canSustainPlant(level, testPos, Direction.DOWN, (IPlantable)block)) {
					adjacentMod = 1.0F;

					if (blockstate.isFertile(level, testPos.move(Direction.DOWN)))
						adjacentMod = 3;
				}

				if (x != 0 || z != 0)
					adjacentMod /= 4f;

				modifier += adjacentMod;
			}
		}

		BlockPos eastPos = pos.west();
		BlockPos westPos = pos.east();
		boolean latitudeAdjacent = block == level.getBlockState(eastPos).getBlock() || block == level.getBlockState(westPos).getBlock();
		boolean longitudeAdjacent = block == level.getBlockState(pos.north()).getBlock() || block == level.getBlockState(pos.south()).getBlock();

		if (latitudeAdjacent && longitudeAdjacent) {
			modifier /= 2f;
		}
		else if (block == level.getBlockState(eastPos.north()).getBlock() || block == level.getBlockState(westPos.north()).getBlock() || block == level.getBlockState(westPos.south()).getBlock() || block == level.getBlockState(eastPos.south()).getBlock()) {
			modifier /= 2f;
		}

		return modifier;
	}

	@Override
	public boolean canSurvive(BlockState pState, IWorldReader world, BlockPos pos) {
		BlockPos leafPos = pos.above();

		return this.mayPlaceOn(world.getBlockState(leafPos), world, leafPos);
	}
}
