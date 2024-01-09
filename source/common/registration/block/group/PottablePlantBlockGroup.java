package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;

import java.util.function.Consumer;

public final class PottablePlantBlockGroup {
	public final DeferredHolder<Block, Block> plant;
	public final DeferredHolder<Block, FlowerPotBlock> pottedPlant;

	public PottablePlantBlockGroup(String baseId, BlockRegistrarFactory registry, Consumer<BlockRegistrar<Block>> baseBlockRegistrar) {
		this.plant = registry.register(baseId, baseBlockRegistrar);
		this.pottedPlant = registry.register("potted_" + baseId, registrar -> registrar.basePottedPlant(this.plant));
	}

	public Block plant() {
		return this.plant.get();
	}

	public FlowerPotBlock potted() {
		return this.pottedPlant.get();
	}
}
