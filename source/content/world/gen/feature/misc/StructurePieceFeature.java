package net.tslat.aoa3.content.world.gen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tslat.aoa3.content.world.gen.feature.config.StructureFeatureConfig;

public class StructurePieceFeature extends Feature<StructureFeatureConfig> {
	public StructurePieceFeature(Codec<StructureFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<StructureFeatureConfig> context) {
		BlockPos pos = context.origin();
		StructureFeatureConfig config = context.config();
		RandomSource rand = context.random();
		WorldGenLevel reader = context.level();

		if (!config.requireGround() || !reader.getBlockState(pos.below()).canBeReplaced())
			config.getTemplate(rand).placeInWorld(reader, pos, pos, config.getPlacementSettings(rand), rand, 2);

		return true;
	}
}
