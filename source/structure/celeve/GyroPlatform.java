package net.tslat.aoa3.structure.celeve;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.structure.AoAStructure;

import java.util.Random;

public class GyroPlatform extends AoAStructure { //StructureSize: 19x9x19
	private static final IBlockState greenCelevus = BlockRegister.leavesCelevusGreen.getDefaultState();
	private static final IBlockState celevusStem = BlockRegister.celeveStem.getDefaultState();
	private static final IBlockState whitewashBricks = BlockRegister.bricksWhitewash.getDefaultState();
	private static final IBlockState bloodstoneBricks = BlockRegister.bricksBloodstone.getDefaultState();
	private static final IBlockState toyBox = BlockRegister.toyBox.getDefaultState();

	public GyroPlatform() {
		super("GyroPlatform");
	}

	@Override
	protected boolean replacesBlocks() {
		return true;
	}

	@Override
	protected void build(World world, Random rand, BlockPos basePos) {
		addBlock(world, basePos, 0, 0, 0, whitewashBricks);
		addBlock(world, basePos, 0, 0, 1, whitewashBricks);
		addBlock(world, basePos, 0, 0, 2, whitewashBricks);
		addBlock(world, basePos, 0, 0, 3, whitewashBricks);
		addBlock(world, basePos, 0, 0, 4, whitewashBricks);
		addBlock(world, basePos, 0, 0, 5, whitewashBricks);
		addBlock(world, basePos, 0, 0, 6, whitewashBricks);
		addBlock(world, basePos, 0, 0, 7, whitewashBricks);
		addBlock(world, basePos, 0, 0, 8, whitewashBricks);
		addBlock(world, basePos, 0, 0, 9, whitewashBricks);
		addBlock(world, basePos, 0, 0, 10, whitewashBricks);
		addBlock(world, basePos, 0, 0, 11, whitewashBricks);
		addBlock(world, basePos, 0, 0, 12, whitewashBricks);
		addBlock(world, basePos, 0, 0, 13, whitewashBricks);
		addBlock(world, basePos, 0, 0, 14, whitewashBricks);
		addBlock(world, basePos, 0, 0, 15, whitewashBricks);
		addBlock(world, basePos, 0, 0, 16, whitewashBricks);
		addBlock(world, basePos, 0, 0, 17, whitewashBricks);
		addBlock(world, basePos, 0, 0, 18, whitewashBricks);
		addBlock(world, basePos, 1, 0, 0, whitewashBricks);
		addBlock(world, basePos, 1, 0, 1, whitewashBricks);
		addBlock(world, basePos, 1, 0, 2, whitewashBricks);
		addBlock(world, basePos, 1, 0, 3, whitewashBricks);
		addBlock(world, basePos, 1, 0, 4, whitewashBricks);
		addBlock(world, basePos, 1, 0, 5, whitewashBricks);
		addBlock(world, basePos, 1, 0, 6, whitewashBricks);
		addBlock(world, basePos, 1, 0, 7, whitewashBricks);
		addBlock(world, basePos, 1, 0, 8, whitewashBricks);
		addBlock(world, basePos, 1, 0, 9, whitewashBricks);
		addBlock(world, basePos, 1, 0, 10, whitewashBricks);
		addBlock(world, basePos, 1, 0, 11, whitewashBricks);
		addBlock(world, basePos, 1, 0, 12, whitewashBricks);
		addBlock(world, basePos, 1, 0, 13, whitewashBricks);
		addBlock(world, basePos, 1, 0, 14, whitewashBricks);
		addBlock(world, basePos, 1, 0, 15, whitewashBricks);
		addBlock(world, basePos, 1, 0, 16, whitewashBricks);
		addBlock(world, basePos, 1, 0, 17, whitewashBricks);
		addBlock(world, basePos, 1, 0, 18, whitewashBricks);
		addBlock(world, basePos, 2, 0, 0, whitewashBricks);
		addBlock(world, basePos, 2, 0, 1, whitewashBricks);
		addBlock(world, basePos, 2, 0, 2, whitewashBricks);
		addBlock(world, basePos, 2, 0, 3, whitewashBricks);
		addBlock(world, basePos, 2, 0, 4, whitewashBricks);
		addBlock(world, basePos, 2, 0, 5, whitewashBricks);
		addBlock(world, basePos, 2, 0, 6, whitewashBricks);
		addBlock(world, basePos, 2, 0, 7, whitewashBricks);
		addBlock(world, basePos, 2, 0, 8, whitewashBricks);
		addBlock(world, basePos, 2, 0, 9, whitewashBricks);
		addBlock(world, basePos, 2, 0, 10, whitewashBricks);
		addBlock(world, basePos, 2, 0, 11, whitewashBricks);
		addBlock(world, basePos, 2, 0, 12, whitewashBricks);
		addBlock(world, basePos, 2, 0, 13, whitewashBricks);
		addBlock(world, basePos, 2, 0, 14, whitewashBricks);
		addBlock(world, basePos, 2, 0, 15, whitewashBricks);
		addBlock(world, basePos, 2, 0, 16, whitewashBricks);
		addBlock(world, basePos, 2, 0, 17, whitewashBricks);
		addBlock(world, basePos, 2, 0, 18, whitewashBricks);
		addBlock(world, basePos, 3, 0, 0, whitewashBricks);
		addBlock(world, basePos, 3, 0, 1, whitewashBricks);
		addBlock(world, basePos, 3, 0, 2, whitewashBricks);
		addBlock(world, basePos, 3, 0, 3, whitewashBricks);
		addBlock(world, basePos, 3, 0, 4, whitewashBricks);
		addBlock(world, basePos, 3, 0, 5, whitewashBricks);
		addBlock(world, basePos, 3, 0, 6, whitewashBricks);
		addBlock(world, basePos, 3, 0, 7, whitewashBricks);
		addBlock(world, basePos, 3, 0, 8, whitewashBricks);
		addBlock(world, basePos, 3, 0, 9, whitewashBricks);
		addBlock(world, basePos, 3, 0, 10, whitewashBricks);
		addBlock(world, basePos, 3, 0, 11, whitewashBricks);
		addBlock(world, basePos, 3, 0, 12, whitewashBricks);
		addBlock(world, basePos, 3, 0, 13, whitewashBricks);
		addBlock(world, basePos, 3, 0, 14, whitewashBricks);
		addBlock(world, basePos, 3, 0, 15, whitewashBricks);
		addBlock(world, basePos, 3, 0, 16, whitewashBricks);
		addBlock(world, basePos, 3, 0, 17, whitewashBricks);
		addBlock(world, basePos, 3, 0, 18, whitewashBricks);
		addBlock(world, basePos, 4, 0, 0, whitewashBricks);
		addBlock(world, basePos, 4, 0, 1, whitewashBricks);
		addBlock(world, basePos, 4, 0, 2, whitewashBricks);
		addBlock(world, basePos, 4, 0, 3, whitewashBricks);
		addBlock(world, basePos, 4, 0, 4, whitewashBricks);
		addBlock(world, basePos, 4, 0, 5, whitewashBricks);
		addBlock(world, basePos, 4, 0, 6, whitewashBricks);
		addBlock(world, basePos, 4, 0, 7, whitewashBricks);
		addBlock(world, basePos, 4, 0, 8, whitewashBricks);
		addBlock(world, basePos, 4, 0, 9, whitewashBricks);
		addBlock(world, basePos, 4, 0, 10, whitewashBricks);
		addBlock(world, basePos, 4, 0, 11, whitewashBricks);
		addBlock(world, basePos, 4, 0, 12, whitewashBricks);
		addBlock(world, basePos, 4, 0, 13, whitewashBricks);
		addBlock(world, basePos, 4, 0, 14, whitewashBricks);
		addBlock(world, basePos, 4, 0, 15, whitewashBricks);
		addBlock(world, basePos, 4, 0, 16, whitewashBricks);
		addBlock(world, basePos, 4, 0, 17, whitewashBricks);
		addBlock(world, basePos, 4, 0, 18, whitewashBricks);
		addBlock(world, basePos, 5, 0, 0, whitewashBricks);
		addBlock(world, basePos, 5, 0, 1, whitewashBricks);
		addBlock(world, basePos, 5, 0, 2, whitewashBricks);
		addBlock(world, basePos, 5, 0, 3, whitewashBricks);
		addBlock(world, basePos, 5, 0, 4, whitewashBricks);
		addBlock(world, basePos, 5, 0, 5, whitewashBricks);
		addBlock(world, basePos, 5, 0, 6, whitewashBricks);
		addBlock(world, basePos, 5, 0, 7, whitewashBricks);
		addBlock(world, basePos, 5, 0, 8, whitewashBricks);
		addBlock(world, basePos, 5, 0, 9, whitewashBricks);
		addBlock(world, basePos, 5, 0, 10, whitewashBricks);
		addBlock(world, basePos, 5, 0, 11, whitewashBricks);
		addBlock(world, basePos, 5, 0, 12, whitewashBricks);
		addBlock(world, basePos, 5, 0, 13, whitewashBricks);
		addBlock(world, basePos, 5, 0, 14, whitewashBricks);
		addBlock(world, basePos, 5, 0, 15, whitewashBricks);
		addBlock(world, basePos, 5, 0, 16, whitewashBricks);
		addBlock(world, basePos, 5, 0, 17, whitewashBricks);
		addBlock(world, basePos, 5, 0, 18, whitewashBricks);
		addBlock(world, basePos, 6, 0, 0, whitewashBricks);
		addBlock(world, basePos, 6, 0, 1, whitewashBricks);
		addBlock(world, basePos, 6, 0, 2, whitewashBricks);
		addBlock(world, basePos, 6, 0, 3, whitewashBricks);
		addBlock(world, basePos, 6, 0, 4, whitewashBricks);
		addBlock(world, basePos, 6, 0, 5, whitewashBricks);
		addBlock(world, basePos, 6, 0, 6, whitewashBricks);
		addBlock(world, basePos, 6, 0, 7, whitewashBricks);
		addBlock(world, basePos, 6, 0, 8, whitewashBricks);
		addBlock(world, basePos, 6, 0, 9, whitewashBricks);
		addBlock(world, basePos, 6, 0, 10, whitewashBricks);
		addBlock(world, basePos, 6, 0, 11, whitewashBricks);
		addBlock(world, basePos, 6, 0, 12, whitewashBricks);
		addBlock(world, basePos, 6, 0, 13, whitewashBricks);
		addBlock(world, basePos, 6, 0, 14, whitewashBricks);
		addBlock(world, basePos, 6, 0, 15, whitewashBricks);
		addBlock(world, basePos, 6, 0, 16, whitewashBricks);
		addBlock(world, basePos, 6, 0, 17, whitewashBricks);
		addBlock(world, basePos, 6, 0, 18, whitewashBricks);
		addBlock(world, basePos, 7, 0, 0, whitewashBricks);
		addBlock(world, basePos, 7, 0, 1, whitewashBricks);
		addBlock(world, basePos, 7, 0, 2, whitewashBricks);
		addBlock(world, basePos, 7, 0, 3, whitewashBricks);
		addBlock(world, basePos, 7, 0, 4, whitewashBricks);
		addBlock(world, basePos, 7, 0, 5, whitewashBricks);
		addBlock(world, basePos, 7, 0, 6, whitewashBricks);
		addBlock(world, basePos, 7, 0, 7, whitewashBricks);
		addBlock(world, basePos, 7, 0, 8, whitewashBricks);
		addBlock(world, basePos, 7, 0, 9, whitewashBricks);
		addBlock(world, basePos, 7, 0, 10, whitewashBricks);
		addBlock(world, basePos, 7, 0, 11, whitewashBricks);
		addBlock(world, basePos, 7, 0, 12, whitewashBricks);
		addBlock(world, basePos, 7, 0, 13, whitewashBricks);
		addBlock(world, basePos, 7, 0, 14, whitewashBricks);
		addBlock(world, basePos, 7, 0, 15, whitewashBricks);
		addBlock(world, basePos, 7, 0, 16, whitewashBricks);
		addBlock(world, basePos, 7, 0, 17, whitewashBricks);
		addBlock(world, basePos, 7, 0, 18, whitewashBricks);
		addBlock(world, basePos, 8, 0, 0, whitewashBricks);
		addBlock(world, basePos, 8, 0, 1, whitewashBricks);
		addBlock(world, basePos, 8, 0, 2, whitewashBricks);
		addBlock(world, basePos, 8, 0, 3, whitewashBricks);
		addBlock(world, basePos, 8, 0, 4, whitewashBricks);
		addBlock(world, basePos, 8, 0, 5, whitewashBricks);
		addBlock(world, basePos, 8, 0, 6, whitewashBricks);
		addBlock(world, basePos, 8, 0, 7, whitewashBricks);
		addBlock(world, basePos, 8, 0, 8, whitewashBricks);
		addBlock(world, basePos, 8, 0, 9, whitewashBricks);
		addBlock(world, basePos, 8, 0, 10, whitewashBricks);
		addBlock(world, basePos, 8, 0, 11, whitewashBricks);
		addBlock(world, basePos, 8, 0, 12, whitewashBricks);
		addBlock(world, basePos, 8, 0, 13, whitewashBricks);
		addBlock(world, basePos, 8, 0, 14, whitewashBricks);
		addBlock(world, basePos, 8, 0, 15, whitewashBricks);
		addBlock(world, basePos, 8, 0, 16, whitewashBricks);
		addBlock(world, basePos, 8, 0, 17, whitewashBricks);
		addBlock(world, basePos, 8, 0, 18, whitewashBricks);
		addBlock(world, basePos, 9, 0, 0, whitewashBricks);
		addBlock(world, basePos, 9, 0, 1, whitewashBricks);
		addBlock(world, basePos, 9, 0, 2, whitewashBricks);
		addBlock(world, basePos, 9, 0, 3, whitewashBricks);
		addBlock(world, basePos, 9, 0, 4, whitewashBricks);
		addBlock(world, basePos, 9, 0, 5, whitewashBricks);
		addBlock(world, basePos, 9, 0, 6, whitewashBricks);
		addBlock(world, basePos, 9, 0, 7, whitewashBricks);
		addBlock(world, basePos, 9, 0, 8, whitewashBricks);
		addBlock(world, basePos, 9, 0, 9, whitewashBricks);
		addBlock(world, basePos, 9, 0, 10, whitewashBricks);
		addBlock(world, basePos, 9, 0, 11, whitewashBricks);
		addBlock(world, basePos, 9, 0, 12, whitewashBricks);
		addBlock(world, basePos, 9, 0, 13, whitewashBricks);
		addBlock(world, basePos, 9, 0, 14, whitewashBricks);
		addBlock(world, basePos, 9, 0, 15, whitewashBricks);
		addBlock(world, basePos, 9, 0, 16, whitewashBricks);
		addBlock(world, basePos, 9, 0, 17, whitewashBricks);
		addBlock(world, basePos, 9, 0, 18, whitewashBricks);
		addBlock(world, basePos, 10, 0, 0, whitewashBricks);
		addBlock(world, basePos, 10, 0, 1, whitewashBricks);
		addBlock(world, basePos, 10, 0, 2, whitewashBricks);
		addBlock(world, basePos, 10, 0, 3, whitewashBricks);
		addBlock(world, basePos, 10, 0, 4, whitewashBricks);
		addBlock(world, basePos, 10, 0, 5, whitewashBricks);
		addBlock(world, basePos, 10, 0, 6, whitewashBricks);
		addBlock(world, basePos, 10, 0, 7, whitewashBricks);
		addBlock(world, basePos, 10, 0, 8, whitewashBricks);
		addBlock(world, basePos, 10, 0, 9, whitewashBricks);
		addBlock(world, basePos, 10, 0, 10, whitewashBricks);
		addBlock(world, basePos, 10, 0, 11, whitewashBricks);
		addBlock(world, basePos, 10, 0, 12, whitewashBricks);
		addBlock(world, basePos, 10, 0, 13, whitewashBricks);
		addBlock(world, basePos, 10, 0, 14, whitewashBricks);
		addBlock(world, basePos, 10, 0, 15, whitewashBricks);
		addBlock(world, basePos, 10, 0, 16, whitewashBricks);
		addBlock(world, basePos, 10, 0, 17, whitewashBricks);
		addBlock(world, basePos, 10, 0, 18, whitewashBricks);
		addBlock(world, basePos, 11, 0, 0, whitewashBricks);
		addBlock(world, basePos, 11, 0, 1, whitewashBricks);
		addBlock(world, basePos, 11, 0, 2, whitewashBricks);
		addBlock(world, basePos, 11, 0, 3, whitewashBricks);
		addBlock(world, basePos, 11, 0, 4, whitewashBricks);
		addBlock(world, basePos, 11, 0, 5, whitewashBricks);
		addBlock(world, basePos, 11, 0, 6, whitewashBricks);
		addBlock(world, basePos, 11, 0, 7, whitewashBricks);
		addBlock(world, basePos, 11, 0, 8, whitewashBricks);
		addBlock(world, basePos, 11, 0, 9, whitewashBricks);
		addBlock(world, basePos, 11, 0, 10, whitewashBricks);
		addBlock(world, basePos, 11, 0, 11, whitewashBricks);
		addBlock(world, basePos, 11, 0, 12, whitewashBricks);
		addBlock(world, basePos, 11, 0, 13, whitewashBricks);
		addBlock(world, basePos, 11, 0, 14, whitewashBricks);
		addBlock(world, basePos, 11, 0, 15, whitewashBricks);
		addBlock(world, basePos, 11, 0, 16, whitewashBricks);
		addBlock(world, basePos, 11, 0, 17, whitewashBricks);
		addBlock(world, basePos, 11, 0, 18, whitewashBricks);
		addBlock(world, basePos, 12, 0, 0, whitewashBricks);
		addBlock(world, basePos, 12, 0, 1, whitewashBricks);
		addBlock(world, basePos, 12, 0, 2, whitewashBricks);
		addBlock(world, basePos, 12, 0, 3, whitewashBricks);
		addBlock(world, basePos, 12, 0, 4, whitewashBricks);
		addBlock(world, basePos, 12, 0, 5, whitewashBricks);
		addBlock(world, basePos, 12, 0, 6, whitewashBricks);
		addBlock(world, basePos, 12, 0, 7, whitewashBricks);
		addBlock(world, basePos, 12, 0, 8, whitewashBricks);
		addBlock(world, basePos, 12, 0, 9, whitewashBricks);
		addBlock(world, basePos, 12, 0, 10, whitewashBricks);
		addBlock(world, basePos, 12, 0, 11, whitewashBricks);
		addBlock(world, basePos, 12, 0, 12, whitewashBricks);
		addBlock(world, basePos, 12, 0, 13, whitewashBricks);
		addBlock(world, basePos, 12, 0, 14, whitewashBricks);
		addBlock(world, basePos, 12, 0, 15, whitewashBricks);
		addBlock(world, basePos, 12, 0, 16, whitewashBricks);
		addBlock(world, basePos, 12, 0, 17, whitewashBricks);
		addBlock(world, basePos, 12, 0, 18, whitewashBricks);
		addBlock(world, basePos, 13, 0, 0, whitewashBricks);
		addBlock(world, basePos, 13, 0, 1, whitewashBricks);
		addBlock(world, basePos, 13, 0, 2, whitewashBricks);
		addBlock(world, basePos, 13, 0, 3, whitewashBricks);
		addBlock(world, basePos, 13, 0, 4, whitewashBricks);
		addBlock(world, basePos, 13, 0, 5, whitewashBricks);
		addBlock(world, basePos, 13, 0, 6, whitewashBricks);
		addBlock(world, basePos, 13, 0, 7, whitewashBricks);
		addBlock(world, basePos, 13, 0, 8, whitewashBricks);
		addBlock(world, basePos, 13, 0, 9, whitewashBricks);
		addBlock(world, basePos, 13, 0, 10, whitewashBricks);
		addBlock(world, basePos, 13, 0, 11, whitewashBricks);
		addBlock(world, basePos, 13, 0, 12, whitewashBricks);
		addBlock(world, basePos, 13, 0, 13, whitewashBricks);
		addBlock(world, basePos, 13, 0, 14, whitewashBricks);
		addBlock(world, basePos, 13, 0, 15, whitewashBricks);
		addBlock(world, basePos, 13, 0, 16, whitewashBricks);
		addBlock(world, basePos, 13, 0, 17, whitewashBricks);
		addBlock(world, basePos, 13, 0, 18, whitewashBricks);
		addBlock(world, basePos, 14, 0, 0, whitewashBricks);
		addBlock(world, basePos, 14, 0, 1, whitewashBricks);
		addBlock(world, basePos, 14, 0, 2, whitewashBricks);
		addBlock(world, basePos, 14, 0, 3, whitewashBricks);
		addBlock(world, basePos, 14, 0, 4, whitewashBricks);
		addBlock(world, basePos, 14, 0, 5, whitewashBricks);
		addBlock(world, basePos, 14, 0, 6, whitewashBricks);
		addBlock(world, basePos, 14, 0, 7, whitewashBricks);
		addBlock(world, basePos, 14, 0, 8, whitewashBricks);
		addBlock(world, basePos, 14, 0, 9, whitewashBricks);
		addBlock(world, basePos, 14, 0, 10, whitewashBricks);
		addBlock(world, basePos, 14, 0, 11, whitewashBricks);
		addBlock(world, basePos, 14, 0, 12, whitewashBricks);
		addBlock(world, basePos, 14, 0, 13, whitewashBricks);
		addBlock(world, basePos, 14, 0, 14, whitewashBricks);
		addBlock(world, basePos, 14, 0, 15, whitewashBricks);
		addBlock(world, basePos, 14, 0, 16, whitewashBricks);
		addBlock(world, basePos, 14, 0, 17, whitewashBricks);
		addBlock(world, basePos, 14, 0, 18, whitewashBricks);
		addBlock(world, basePos, 15, 0, 0, whitewashBricks);
		addBlock(world, basePos, 15, 0, 1, whitewashBricks);
		addBlock(world, basePos, 15, 0, 2, whitewashBricks);
		addBlock(world, basePos, 15, 0, 3, whitewashBricks);
		addBlock(world, basePos, 15, 0, 4, whitewashBricks);
		addBlock(world, basePos, 15, 0, 5, whitewashBricks);
		addBlock(world, basePos, 15, 0, 6, whitewashBricks);
		addBlock(world, basePos, 15, 0, 7, whitewashBricks);
		addBlock(world, basePos, 15, 0, 8, whitewashBricks);
		addBlock(world, basePos, 15, 0, 9, whitewashBricks);
		addBlock(world, basePos, 15, 0, 10, whitewashBricks);
		addBlock(world, basePos, 15, 0, 11, whitewashBricks);
		addBlock(world, basePos, 15, 0, 12, whitewashBricks);
		addBlock(world, basePos, 15, 0, 13, whitewashBricks);
		addBlock(world, basePos, 15, 0, 14, whitewashBricks);
		addBlock(world, basePos, 15, 0, 15, whitewashBricks);
		addBlock(world, basePos, 15, 0, 16, whitewashBricks);
		addBlock(world, basePos, 15, 0, 17, whitewashBricks);
		addBlock(world, basePos, 15, 0, 18, whitewashBricks);
		addBlock(world, basePos, 16, 0, 0, whitewashBricks);
		addBlock(world, basePos, 16, 0, 1, whitewashBricks);
		addBlock(world, basePos, 16, 0, 2, whitewashBricks);
		addBlock(world, basePos, 16, 0, 3, whitewashBricks);
		addBlock(world, basePos, 16, 0, 4, whitewashBricks);
		addBlock(world, basePos, 16, 0, 5, whitewashBricks);
		addBlock(world, basePos, 16, 0, 6, whitewashBricks);
		addBlock(world, basePos, 16, 0, 7, whitewashBricks);
		addBlock(world, basePos, 16, 0, 8, whitewashBricks);
		addBlock(world, basePos, 16, 0, 9, whitewashBricks);
		addBlock(world, basePos, 16, 0, 10, whitewashBricks);
		addBlock(world, basePos, 16, 0, 11, whitewashBricks);
		addBlock(world, basePos, 16, 0, 12, whitewashBricks);
		addBlock(world, basePos, 16, 0, 13, whitewashBricks);
		addBlock(world, basePos, 16, 0, 14, whitewashBricks);
		addBlock(world, basePos, 16, 0, 15, whitewashBricks);
		addBlock(world, basePos, 16, 0, 16, whitewashBricks);
		addBlock(world, basePos, 16, 0, 17, whitewashBricks);
		addBlock(world, basePos, 16, 0, 18, whitewashBricks);
		addBlock(world, basePos, 17, 0, 0, whitewashBricks);
		addBlock(world, basePos, 17, 0, 1, whitewashBricks);
		addBlock(world, basePos, 17, 0, 2, whitewashBricks);
		addBlock(world, basePos, 17, 0, 3, whitewashBricks);
		addBlock(world, basePos, 17, 0, 4, whitewashBricks);
		addBlock(world, basePos, 17, 0, 5, whitewashBricks);
		addBlock(world, basePos, 17, 0, 6, whitewashBricks);
		addBlock(world, basePos, 17, 0, 7, whitewashBricks);
		addBlock(world, basePos, 17, 0, 8, whitewashBricks);
		addBlock(world, basePos, 17, 0, 9, whitewashBricks);
		addBlock(world, basePos, 17, 0, 10, whitewashBricks);
		addBlock(world, basePos, 17, 0, 11, whitewashBricks);
		addBlock(world, basePos, 17, 0, 12, whitewashBricks);
		addBlock(world, basePos, 17, 0, 13, whitewashBricks);
		addBlock(world, basePos, 17, 0, 14, whitewashBricks);
		addBlock(world, basePos, 17, 0, 15, whitewashBricks);
		addBlock(world, basePos, 17, 0, 16, whitewashBricks);
		addBlock(world, basePos, 17, 0, 17, whitewashBricks);
		addBlock(world, basePos, 17, 0, 18, whitewashBricks);
		addBlock(world, basePos, 18, 0, 0, whitewashBricks);
		addBlock(world, basePos, 18, 0, 1, whitewashBricks);
		addBlock(world, basePos, 18, 0, 2, whitewashBricks);
		addBlock(world, basePos, 18, 0, 3, whitewashBricks);
		addBlock(world, basePos, 18, 0, 4, whitewashBricks);
		addBlock(world, basePos, 18, 0, 5, whitewashBricks);
		addBlock(world, basePos, 18, 0, 6, whitewashBricks);
		addBlock(world, basePos, 18, 0, 7, whitewashBricks);
		addBlock(world, basePos, 18, 0, 8, whitewashBricks);
		addBlock(world, basePos, 18, 0, 9, whitewashBricks);
		addBlock(world, basePos, 18, 0, 10, whitewashBricks);
		addBlock(world, basePos, 18, 0, 11, whitewashBricks);
		addBlock(world, basePos, 18, 0, 12, whitewashBricks);
		addBlock(world, basePos, 18, 0, 13, whitewashBricks);
		addBlock(world, basePos, 18, 0, 14, whitewashBricks);
		addBlock(world, basePos, 18, 0, 15, whitewashBricks);
		addBlock(world, basePos, 18, 0, 16, whitewashBricks);
		addBlock(world, basePos, 18, 0, 17, whitewashBricks);
		addBlock(world, basePos, 18, 0, 18, whitewashBricks);
		addBlock(world, basePos, 0, 1, 0, whitewashBricks);
		addBlock(world, basePos, 0, 1, 1, whitewashBricks);
		addBlock(world, basePos, 0, 1, 2, whitewashBricks);
		addBlock(world, basePos, 0, 1, 4, whitewashBricks);
		addBlock(world, basePos, 0, 1, 14, whitewashBricks);
		addBlock(world, basePos, 0, 1, 16, whitewashBricks);
		addBlock(world, basePos, 0, 1, 17, whitewashBricks);
		addBlock(world, basePos, 0, 1, 18, whitewashBricks);
		addBlock(world, basePos, 1, 1, 0, whitewashBricks);
		addBlock(world, basePos, 1, 1, 1, celevusStem);
		addBlock(world, basePos, 1, 1, 2, whitewashBricks);
		addBlock(world, basePos, 1, 1, 16, whitewashBricks);
		addBlock(world, basePos, 1, 1, 17, celevusStem);
		addBlock(world, basePos, 1, 1, 18, whitewashBricks);
		addBlock(world, basePos, 2, 1, 0, whitewashBricks);
		addBlock(world, basePos, 2, 1, 1, whitewashBricks);
		addBlock(world, basePos, 2, 1, 2, whitewashBricks);
		addBlock(world, basePos, 2, 1, 16, whitewashBricks);
		addBlock(world, basePos, 2, 1, 17, whitewashBricks);
		addBlock(world, basePos, 2, 1, 18, whitewashBricks);
		addBlock(world, basePos, 4, 1, 0, whitewashBricks);
		addBlock(world, basePos, 4, 1, 18, whitewashBricks);
		addBlock(world, basePos, 7, 1, 7, bloodstoneBricks);
		addBlock(world, basePos, 7, 1, 8, bloodstoneBricks);
		addBlock(world, basePos, 7, 1, 9, bloodstoneBricks);
		addBlock(world, basePos, 7, 1, 10, bloodstoneBricks);
		addBlock(world, basePos, 7, 1, 11, bloodstoneBricks);
		addBlock(world, basePos, 8, 1, 7, bloodstoneBricks);
		addBlock(world, basePos, 8, 1, 8, whitewashBricks);
		addBlock(world, basePos, 8, 1, 9, whitewashBricks);
		addBlock(world, basePos, 8, 1, 10, whitewashBricks);
		addBlock(world, basePos, 8, 1, 11, bloodstoneBricks);
		addBlock(world, basePos, 9, 1, 7, bloodstoneBricks);
		addBlock(world, basePos, 9, 1, 8, whitewashBricks);
		addBlock(world, basePos, 9, 1, 9, whitewashBricks);
		addBlock(world, basePos, 9, 1, 10, whitewashBricks);
		addBlock(world, basePos, 9, 1, 11, bloodstoneBricks);
		addBlock(world, basePos, 10, 1, 7, bloodstoneBricks);
		addBlock(world, basePos, 10, 1, 8, whitewashBricks);
		addBlock(world, basePos, 10, 1, 9, whitewashBricks);
		addBlock(world, basePos, 10, 1, 10, whitewashBricks);
		addBlock(world, basePos, 10, 1, 11, bloodstoneBricks);
		addBlock(world, basePos, 11, 1, 7, bloodstoneBricks);
		addBlock(world, basePos, 11, 1, 8, bloodstoneBricks);
		addBlock(world, basePos, 11, 1, 9, bloodstoneBricks);
		addBlock(world, basePos, 11, 1, 10, bloodstoneBricks);
		addBlock(world, basePos, 11, 1, 11, bloodstoneBricks);
		addBlock(world, basePos, 14, 1, 0, whitewashBricks);
		addBlock(world, basePos, 14, 1, 18, whitewashBricks);
		addBlock(world, basePos, 16, 1, 0, whitewashBricks);
		addBlock(world, basePos, 16, 1, 1, whitewashBricks);
		addBlock(world, basePos, 16, 1, 2, whitewashBricks);
		addBlock(world, basePos, 16, 1, 16, whitewashBricks);
		addBlock(world, basePos, 16, 1, 17, whitewashBricks);
		addBlock(world, basePos, 16, 1, 18, whitewashBricks);
		addBlock(world, basePos, 17, 1, 0, whitewashBricks);
		addBlock(world, basePos, 17, 1, 1, celevusStem);
		addBlock(world, basePos, 17, 1, 2, whitewashBricks);
		addBlock(world, basePos, 17, 1, 16, whitewashBricks);
		addBlock(world, basePos, 17, 1, 17, celevusStem);
		addBlock(world, basePos, 17, 1, 18, whitewashBricks);
		addBlock(world, basePos, 18, 1, 0, whitewashBricks);
		addBlock(world, basePos, 18, 1, 1, whitewashBricks);
		addBlock(world, basePos, 18, 1, 2, whitewashBricks);
		addBlock(world, basePos, 18, 1, 4, whitewashBricks);
		addBlock(world, basePos, 18, 1, 14, whitewashBricks);
		addBlock(world, basePos, 18, 1, 16, whitewashBricks);
		addBlock(world, basePos, 18, 1, 17, whitewashBricks);
		addBlock(world, basePos, 18, 1, 18, whitewashBricks);
		addBlock(world, basePos, 0, 2, 4, whitewashBricks);
		addBlock(world, basePos, 0, 2, 14, whitewashBricks);
		addBlock(world, basePos, 1, 2, 1, celevusStem);
		addBlock(world, basePos, 1, 2, 17, celevusStem);
		addBlock(world, basePos, 4, 2, 0, whitewashBricks);
		addBlock(world, basePos, 4, 2, 18, whitewashBricks);
		//addBlock(world, basePos, 9, 2, 9, toyBox);
		addBlock(world, basePos, 14, 2, 0, whitewashBricks);
		addBlock(world, basePos, 14, 2, 18, whitewashBricks);
		addBlock(world, basePos, 17, 2, 1, celevusStem);
		addBlock(world, basePos, 17, 2, 17, celevusStem);
		addBlock(world, basePos, 18, 2, 4, whitewashBricks);
		addBlock(world, basePos, 18, 2, 14, whitewashBricks);
		addBlock(world, basePos, 0, 3, 4, whitewashBricks);
		addBlock(world, basePos, 0, 3, 14, whitewashBricks);
		addBlock(world, basePos, 1, 3, 1, celevusStem);
		addBlock(world, basePos, 1, 3, 17, celevusStem);
		addBlock(world, basePos, 4, 3, 0, whitewashBricks);
		addBlock(world, basePos, 4, 3, 18, whitewashBricks);
		addBlock(world, basePos, 14, 3, 0, whitewashBricks);
		addBlock(world, basePos, 14, 3, 18, whitewashBricks);
		addBlock(world, basePos, 17, 3, 1, celevusStem);
		addBlock(world, basePos, 17, 3, 17, celevusStem);
		addBlock(world, basePos, 18, 3, 4, whitewashBricks);
		addBlock(world, basePos, 18, 3, 14, whitewashBricks);
		addBlock(world, basePos, 0, 4, 4, whitewashBricks);
		addBlock(world, basePos, 0, 4, 14, whitewashBricks);
		addBlock(world, basePos, 1, 4, 1, celevusStem);
		addBlock(world, basePos, 1, 4, 17, celevusStem);
		addBlock(world, basePos, 4, 4, 0, whitewashBricks);
		addBlock(world, basePos, 4, 4, 18, whitewashBricks);
		addBlock(world, basePos, 14, 4, 0, whitewashBricks);
		addBlock(world, basePos, 14, 4, 18, whitewashBricks);
		addBlock(world, basePos, 17, 4, 1, celevusStem);
		addBlock(world, basePos, 17, 4, 17, celevusStem);
		addBlock(world, basePos, 18, 4, 4, whitewashBricks);
		addBlock(world, basePos, 18, 4, 14, whitewashBricks);
		addBlock(world, basePos, 0, 5, 1, greenCelevus);
		addBlock(world, basePos, 0, 5, 4, whitewashBricks);
		addBlock(world, basePos, 0, 5, 5, whitewashBricks);
		addBlock(world, basePos, 0, 5, 6, whitewashBricks);
		addBlock(world, basePos, 0, 5, 8, whitewashBricks);
		addBlock(world, basePos, 0, 5, 9, whitewashBricks);
		addBlock(world, basePos, 0, 5, 10, whitewashBricks);
		addBlock(world, basePos, 0, 5, 12, whitewashBricks);
		addBlock(world, basePos, 0, 5, 13, whitewashBricks);
		addBlock(world, basePos, 0, 5, 14, whitewashBricks);
		addBlock(world, basePos, 0, 5, 17, greenCelevus);
		addBlock(world, basePos, 1, 5, 0, greenCelevus);
		addBlock(world, basePos, 1, 5, 1, celevusStem);
		addBlock(world, basePos, 1, 5, 2, greenCelevus);
		addBlock(world, basePos, 1, 5, 16, greenCelevus);
		addBlock(world, basePos, 1, 5, 17, celevusStem);
		addBlock(world, basePos, 1, 5, 18, greenCelevus);
		addBlock(world, basePos, 2, 5, 1, greenCelevus);
		addBlock(world, basePos, 2, 5, 17, greenCelevus);
		addBlock(world, basePos, 4, 5, 0, whitewashBricks);
		addBlock(world, basePos, 4, 5, 18, whitewashBricks);
		addBlock(world, basePos, 5, 5, 0, whitewashBricks);
		addBlock(world, basePos, 5, 5, 18, whitewashBricks);
		addBlock(world, basePos, 6, 5, 0, whitewashBricks);
		addBlock(world, basePos, 6, 5, 18, whitewashBricks);
		addBlock(world, basePos, 8, 5, 0, whitewashBricks);
		addBlock(world, basePos, 8, 5, 18, whitewashBricks);
		addBlock(world, basePos, 9, 5, 0, whitewashBricks);
		addBlock(world, basePos, 9, 5, 18, whitewashBricks);
		addBlock(world, basePos, 10, 5, 0, whitewashBricks);
		addBlock(world, basePos, 10, 5, 18, whitewashBricks);
		addBlock(world, basePos, 12, 5, 0, whitewashBricks);
		addBlock(world, basePos, 12, 5, 18, whitewashBricks);
		addBlock(world, basePos, 13, 5, 0, whitewashBricks);
		addBlock(world, basePos, 13, 5, 18, whitewashBricks);
		addBlock(world, basePos, 14, 5, 0, whitewashBricks);
		addBlock(world, basePos, 14, 5, 18, whitewashBricks);
		addBlock(world, basePos, 16, 5, 1, greenCelevus);
		addBlock(world, basePos, 16, 5, 17, greenCelevus);
		addBlock(world, basePos, 17, 5, 0, greenCelevus);
		addBlock(world, basePos, 17, 5, 1, celevusStem);
		addBlock(world, basePos, 17, 5, 2, greenCelevus);
		addBlock(world, basePos, 17, 5, 16, greenCelevus);
		addBlock(world, basePos, 17, 5, 17, celevusStem);
		addBlock(world, basePos, 17, 5, 18, greenCelevus);
		addBlock(world, basePos, 18, 5, 1, greenCelevus);
		addBlock(world, basePos, 18, 5, 4, whitewashBricks);
		addBlock(world, basePos, 18, 5, 5, whitewashBricks);
		addBlock(world, basePos, 18, 5, 6, whitewashBricks);
		addBlock(world, basePos, 18, 5, 8, whitewashBricks);
		addBlock(world, basePos, 18, 5, 9, whitewashBricks);
		addBlock(world, basePos, 18, 5, 10, whitewashBricks);
		addBlock(world, basePos, 18, 5, 12, whitewashBricks);
		addBlock(world, basePos, 18, 5, 13, whitewashBricks);
		addBlock(world, basePos, 18, 5, 14, whitewashBricks);
		addBlock(world, basePos, 18, 5, 17, greenCelevus);
		addBlock(world, basePos, 0, 6, 6, whitewashBricks);
		addBlock(world, basePos, 0, 6, 8, whitewashBricks);
		addBlock(world, basePos, 0, 6, 9, bloodstoneBricks);
		addBlock(world, basePos, 0, 6, 10, whitewashBricks);
		addBlock(world, basePos, 0, 6, 12, whitewashBricks);
		addBlock(world, basePos, 1, 6, 1, celevusStem);
		addBlock(world, basePos, 1, 6, 17, celevusStem);
		addBlock(world, basePos, 6, 6, 0, whitewashBricks);
		addBlock(world, basePos, 6, 6, 18, whitewashBricks);
		addBlock(world, basePos, 7, 6, 18, whitewashBricks);
		addBlock(world, basePos, 8, 6, 0, whitewashBricks);
		addBlock(world, basePos, 8, 6, 18, whitewashBricks);
		addBlock(world, basePos, 9, 6, 0, bloodstoneBricks);
		addBlock(world, basePos, 9, 6, 18, bloodstoneBricks);
		addBlock(world, basePos, 10, 6, 0, whitewashBricks);
		addBlock(world, basePos, 10, 6, 18, whitewashBricks);
		addBlock(world, basePos, 11, 6, 18, whitewashBricks);
		addBlock(world, basePos, 12, 6, 0, whitewashBricks);
		addBlock(world, basePos, 12, 6, 18, whitewashBricks);
		addBlock(world, basePos, 17, 6, 1, celevusStem);
		addBlock(world, basePos, 17, 6, 17, celevusStem);
		addBlock(world, basePos, 18, 6, 6, whitewashBricks);
		addBlock(world, basePos, 18, 6, 8, whitewashBricks);
		addBlock(world, basePos, 18, 6, 9, bloodstoneBricks);
		addBlock(world, basePos, 18, 6, 10, whitewashBricks);
		addBlock(world, basePos, 18, 6, 12, whitewashBricks);
		addBlock(world, basePos, 0, 7, 1, greenCelevus);
		addBlock(world, basePos, 0, 7, 6, whitewashBricks);
		addBlock(world, basePos, 0, 7, 7, whitewashBricks);
		addBlock(world, basePos, 0, 7, 8, whitewashBricks);
		addBlock(world, basePos, 0, 7, 9, whitewashBricks);
		addBlock(world, basePos, 0, 7, 10, whitewashBricks);
		addBlock(world, basePos, 0, 7, 11, whitewashBricks);
		addBlock(world, basePos, 0, 7, 12, whitewashBricks);
		addBlock(world, basePos, 0, 7, 17, greenCelevus);
		addBlock(world, basePos, 1, 7, 0, greenCelevus); 
		addBlock(world, basePos, 1, 7, 1, celevusStem);
		addBlock(world, basePos, 1, 7, 2, greenCelevus);
		addBlock(world, basePos, 1, 7, 16, greenCelevus);
		addBlock(world, basePos, 1, 7, 17, celevusStem);
		addBlock(world, basePos, 1, 7, 18, greenCelevus);
		addBlock(world, basePos, 2, 7, 1, greenCelevus);
		addBlock(world, basePos, 2, 7, 17, greenCelevus);
		addBlock(world, basePos, 6, 7, 0, whitewashBricks);
		addBlock(world, basePos, 6, 7, 18, whitewashBricks);
		addBlock(world, basePos, 7, 7, 0, whitewashBricks);
		addBlock(world, basePos, 7, 7, 18, whitewashBricks);
		addBlock(world, basePos, 8, 7, 0, whitewashBricks);
		addBlock(world, basePos, 8, 7, 18, whitewashBricks);
		addBlock(world, basePos, 9, 7, 0, whitewashBricks);
		addBlock(world, basePos, 9, 7, 18, whitewashBricks);
		addBlock(world, basePos, 10, 7, 0, whitewashBricks);
		addBlock(world, basePos, 10, 7, 18, whitewashBricks);
		addBlock(world, basePos, 11, 7, 0, whitewashBricks);
		addBlock(world, basePos, 11, 7, 18, whitewashBricks);
		addBlock(world, basePos, 12, 7, 0, whitewashBricks);
		addBlock(world, basePos, 12, 7, 18, whitewashBricks);
		addBlock(world, basePos, 16, 7, 1, greenCelevus);
		addBlock(world, basePos, 16, 7, 17, greenCelevus);
		addBlock(world, basePos, 17, 7, 0, greenCelevus); 
		addBlock(world, basePos, 17, 7, 1, celevusStem);
		addBlock(world, basePos, 17, 7, 2, greenCelevus);
		addBlock(world, basePos, 17, 7, 16, greenCelevus);
		addBlock(world, basePos, 17, 7, 17, celevusStem);
		addBlock(world, basePos, 17, 7, 18, greenCelevus);
		addBlock(world, basePos, 18, 7, 1, greenCelevus); 
		addBlock(world, basePos, 18, 7, 6, whitewashBricks);
		addBlock(world, basePos, 18, 7, 7, whitewashBricks);
		addBlock(world, basePos, 18, 7, 8, whitewashBricks);
		addBlock(world, basePos, 18, 7, 9, whitewashBricks);
		addBlock(world, basePos, 18, 7, 10, whitewashBricks);
		addBlock(world, basePos, 18, 7, 11, whitewashBricks);
		addBlock(world, basePos, 18, 7, 12, whitewashBricks);
		addBlock(world, basePos, 18, 7, 17, greenCelevus);
		addBlock(world, basePos, 1, 8, 1, greenCelevus);
		addBlock(world, basePos, 1, 8, 17, greenCelevus);
		addBlock(world, basePos, 17, 8, 1, greenCelevus);
		addBlock(world, basePos, 17, 8, 17, greenCelevus);
	}
}
