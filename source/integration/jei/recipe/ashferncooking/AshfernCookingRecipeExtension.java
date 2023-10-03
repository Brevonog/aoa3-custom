package net.tslat.aoa3.integration.jei.recipe.ashferncooking;

import it.unimi.dsi.fastutil.Pair;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.recipe.AshfernCookingRecipe;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class AshfernCookingRecipeExtension implements ICraftingCategoryExtension {
	private final AshfernCookingRecipe recipe;

	public AshfernCookingRecipeExtension(AshfernCookingRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICraftingGridHelper craftingGridHelper, IFocusGroup focuses) {
		final Level level = Minecraft.getInstance().level;

		if (level == null)
			return;

		final RegistryAccess registryAccess = level.registryAccess();

		final List<Pair<ItemStack, ItemStack>> recipes = BuiltInRegistries.ITEM.stream()
				.filter(Item::isEdible)
				.map(item -> {
					final ItemStack stack = item.getDefaultInstance();
					final Optional<ItemStack> smelted = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level)
							.map(recipe -> recipe.getResultItem(registryAccess));

					return smelted.map(itemStack -> Pair.of(stack, itemStack));
				})
				.filter(Optional::isPresent)
				.map(Optional::get)
				.toList();

		final IRecipeSlotBuilder resultSlot = craftingGridHelper.createAndSetOutputs(builder, recipes.stream().map(Pair::second).toList());
		final List<IRecipeSlotBuilder> ingredientSlots = craftingGridHelper.createAndSetInputs(builder, List.of(recipes.stream().map(Pair::first).toList(), List.of(AoAItems.ASHFERN.get().getDefaultInstance())), 0, 0);

		builder.createFocusLink(ingredientSlots.get(0), resultSlot);
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getId();
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}
}
