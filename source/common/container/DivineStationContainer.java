package net.tslat.aoa3.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.library.object.MutableSupplier;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DivineStationContainer extends AbstractContainerMenu { // TODO Look at RecipeBookContainer extensions
	public DivineStationInventory inputs = new DivineStationInventory(this);
	public ResultContainer output = new ResultContainer();
	private final ContainerLevelAccess functionCaller;
	private final Player player;

	public DivineStationContainer(int id, Inventory inventory) {
		this(id, inventory, ContainerLevelAccess.NULL);
	}

	public DivineStationContainer(int screenId, Inventory plInventory, ContainerLevelAccess functionCaller) {
		super(AoAContainers.DIVINE_STATION.get(), screenId);

		this.functionCaller = functionCaller;
		this.player = plInventory.player;

		addSlot(new CustomResultSlot<>(player, inputs, output, AoARecipes.UPGRADE_KIT.type().get(), 0, 134, 23));
		addSlot(new Slot(inputs, 0, 27, 23));
		addSlot(new Slot(inputs, 1, 76, 23));

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(plInventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 60 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(plInventory, hotbarSlot, 8 + hotbarSlot * 18, 118));
		}
	}

	@Override
	public void slotsChanged(Container inventory) {
		functionCaller.execute((world, pos) -> slotChangedCraftingGrid(world, player, inputs, output));
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(functionCaller, player, AoABlocks.DIVINE_STATION.get());
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		functionCaller.execute((world, pos) -> clearContainer(player, inputs));
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != output && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index == 0) {
				functionCaller.execute((world, pos) -> slotStack.getItem().onCraftedBy(slotStack, player.level(), player));

				if (!moveItemStackTo(slotStack, 3, 39, true))
					return ItemStack.EMPTY;

				slot.onQuickCraft(slotStack, stack);
			}
			else if (index > 2) {
				if (index < 39 && !moveItemStackTo(slotStack, 1, 3, false))
					return ItemStack.EMPTY;
			}
			else if (!moveItemStackTo(slotStack, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else {
				slot.setChanged();
			}

			if (slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, slotStack);

			if (index == 0)
				player.drop(slotStack, false);
		}

		return stack;
	}

	protected void slotChangedCraftingGrid(Level world, Player player, DivineStationInventory inv, ResultContainer craftResult) {
		if (player instanceof ServerPlayer pl) {
			MutableSupplier<ItemStack> resultStack = new MutableSupplier<>(() -> ItemStack.EMPTY);
			Optional<RecipeHolder<UpgradeKitRecipe>> recipeMatch = world.getServer().getRecipeManager().getRecipeFor(AoARecipes.UPGRADE_KIT.type().get(), inv, world);

			recipeMatch.ifPresent(holder -> {
				UpgradeKitRecipe recipe = holder.value();

				if (recipe.isSpecial() || !world.getGameRules().getBoolean(GameRules.RULE_LIMITED_CRAFTING) || pl.getRecipeBook().contains(holder)) {
					craftResult.setRecipeUsed(holder);

					resultStack.update(() -> recipe.assemble(inv, world.registryAccess()));
				}
			});

			craftResult.setItem(0, resultStack.get());
			pl.connection.send(new ClientboundContainerSetSlotPacket(this.containerId, incrementStateId(), 0, resultStack.get()));
		}
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		player.openMenu(new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return Component.translatable("container.aoa3.divine_station");
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
				return new DivineStationContainer(windowId, inv, ContainerLevelAccess.create(player.level(), pos));
			}
		}, pos);
	}

	public static class DivineStationInventory extends TransientCraftingContainer {
		private final NonNullList<ItemStack> stackList;
		private final AbstractContainerMenu eventListener;

		public DivineStationInventory(AbstractContainerMenu container) {
			super(container, 0, 0);

			this.stackList = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
			this.eventListener = container;
		}

		@Override
		public int getContainerSize() {
			return 2;
		}

		@Override
		public boolean isEmpty() {
			for (ItemStack stack : stackList) {
				if (!stack.isEmpty())
					return false;
			}

			return true;
		}

		@Override
		public ItemStack getItem(int index) {
			return index >= getContainerSize() || index < 0 ? ItemStack.EMPTY : stackList.get(index);
		}

		@Override
		public ItemStack removeItemNoUpdate(int index) {
			return ContainerHelper.takeItem(stackList, index);
		}

		@Override
		public ItemStack removeItem(int index, int count) {
			ItemStack stack = ContainerHelper.removeItem(stackList, index, count);

			if (!stack.isEmpty())
				eventListener.slotsChanged(this);

			return stack;
		}

		@Override
		public void setItem(int index, ItemStack stack) {
			stackList.set(index, stack);
			eventListener.slotsChanged(this);
		}

		@Override
		public void clearContent() {
			stackList.clear();
		}

		@Override
		public void fillStackedContents(StackedContents recipeItemHelper) {
			for (ItemStack stack : stackList) {
				recipeItemHelper.accountStack(stack);
			}
		}
	}
}
