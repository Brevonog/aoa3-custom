package net.tslat.aoa3.content.world.genold.feature.features.trees;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.plant.AoASaplingBlock;
import net.tslat.aoa3.content.world.genold.feature.placement.config.BlockStatePlacementConfig;

import java.util.function.Supplier;

public class GreenCelevusTreeFeature extends CelevusTreeFeature {
	public GreenCelevusTreeFeature(Codec<BlockStatePlacementConfig> codec, Supplier<? extends AoASaplingBlock> saplingBlock) {
		super(codec, saplingBlock);
	}

	@Override
	protected BlockState getLeaf() {
		return AoABlocks.GREEN_CELEVUS_LEAVES.get().defaultBlockState();
	}
}
