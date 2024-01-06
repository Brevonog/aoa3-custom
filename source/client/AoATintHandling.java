package net.tslat.aoa3.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.model.DynamicFluidContainerModel;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.mixin.client.accessor.BlockColorsAccessor;
import net.tslat.aoa3.mixin.client.accessor.ItemColorsAccessor;

import java.util.function.Function;

public final class AoATintHandling {
	public static void init() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		modBus.addListener(AoATintHandling::registerBlockColours);
		modBus.addListener(AoATintHandling::registerItemColours);
	}

	private static void registerBlockColours(final RegisterColorHandlersEvent.Block ev) {
		final Function<Block, BlockColor> vanillaColouring = block -> ((BlockColorsAccessor)ev.getBlockColors()).getBlockColors().get(ForgeRegistries.BLOCKS.getDelegateOrThrow(block));
		BlockColor grassColouring = vanillaColouring.apply(Blocks.GRASS_BLOCK);

		ev.register(grassColouring, AoABlocks.PRECASIAN_GRASS.get());
		ev.register(grassColouring, AoABlocks.CALAB_GRASS.get());
		ev.register(grassColouring, AoABlocks.CALAB_LONG_GRASS.get());
		ev.register(vanillaColouring.apply(Blocks.ACACIA_LEAVES), AoABlocks.BAOBAB_LEAVES.get(), AoABlocks.LUCALUS_LEAVES.get(), AoABlocks.STRANGLEWOOD_LEAVES.get(), AoABlocks.STRANGLEWOOD_LOG.get(), AoABlocks.STRANGLEWOOD.get());
		ev.register((state, level, pos, tintIndex) -> {
            if (level == null || pos == null)
                return FoliageColor.getEvergreenColor();

            final float desaturation = level.getRawBrightness(pos, 0) * 0.04f;

            return FoliageColor.get(0.1f + desaturation, 1.3f - desaturation * 2f);
        }, AoABlocks.ANCIENT_VINES.get());
	}

	private static void registerItemColours(final RegisterColorHandlersEvent.Item ev) {
		final Function<Item, ItemColor> vanillaColouring = item -> ((ItemColorsAccessor)ev.getItemColors()).getItemColors().get(ForgeRegistries.ITEMS.getDelegateOrThrow(item));
		ItemColor grassColouring = vanillaColouring.apply(Items.GRASS_BLOCK);

		ev.register(grassColouring, AoABlocks.PRECASIAN_GRASS.get());
		ev.register(grassColouring, AoABlocks.CALAB_GRASS.get());
		ev.register(grassColouring, AoABlocks.CALAB_LONG_GRASS.get());
		ev.register(vanillaColouring.apply(Items.ACACIA_LEAVES), AoABlocks.BAOBAB_LEAVES.get(), AoABlocks.LUCALUS_LEAVES.get(), AoABlocks.STRANGLEWOOD_LEAVES.get(), AoABlocks.STRANGLEWOOD_LOG.get(), AoABlocks.STRANGLEWOOD.get());

		ev.register(new DynamicFluidContainerModel.Colors(), AoABlocks.CANDIED_WATER.getBucket());
		ev.register(new DynamicFluidContainerModel.Colors(), AoABlocks.TOXIC_WASTE.getBucket());
		ev.register(new DynamicFluidContainerModel.Colors(), AoABlocks.TAR.getBucket());
	}
}
