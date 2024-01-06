package net.tslat.aoa3.library.constant;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.CombatEntry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public enum BossDropsScheme {
	ON_GROUND(BossDropsScheme::onGroundDistributor),
	LAST_PLAYER_TO_HIT(BossDropsScheme::lastPlayerToHitDistributor),
	MOST_DAMAGING_PLAYER(BossDropsScheme::mostDamagingPlayerDistributor),
	SPLIT_BETWEEN_PLAYERS(BossDropsScheme::splitBetweenPlayersDistributor);

	private final Consumer<LivingDropsEvent> dropsEventConsumer;

	BossDropsScheme(Consumer<LivingDropsEvent> dropsEventConsumer) {
		this.dropsEventConsumer = dropsEventConsumer;
	}

	public void handleDrops(LivingDropsEvent event) {
		this.dropsEventConsumer.accept(event);
	}

	private static void onGroundDistributor(final LivingDropsEvent ev) {}

	private static void lastPlayerToHitDistributor(final LivingDropsEvent ev) {
		List<CombatEntry> combatEntries = ev.getEntity().getCombatTracker().entries;

		if (combatEntries.isEmpty()) {
			Entity attacker = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity());

			if (attacker instanceof Player player) {
				giveDropsToPlayer(player, ev.getDrops().iterator());

				return;
			}
		}

		for (int i = combatEntries.size() - 1; i >= 0; i--) {
			Entity attacker = combatEntries.get(i).source().getEntity();

			if (attacker instanceof Player player) {
				giveDropsToPlayer(player, ev.getDrops().iterator());

				return;
			}
		}
	}

	private static void mostDamagingPlayerDistributor(final LivingDropsEvent ev) {
		LivingEntity killer = ev.getEntity().getKillCredit();

		if (killer == null)
			killer = PlayerUtil.getPlayerOrOwnerIfApplicable(ev.getSource().getEntity());

		if (killer instanceof Player player)
			giveDropsToPlayer(player, ev.getDrops().iterator());
	}

	private static void splitBetweenPlayersDistributor(final LivingDropsEvent ev) {
		Set<Player> players = new ObjectOpenHashSet<>();

		for (Entity attacker : EntityUtil.getAttackersForMob(ev.getEntity(), null)) {
			Player pl = PlayerUtil.getPlayerOrOwnerIfApplicable(attacker);

			if (pl != null)
				players.add(pl);
		}

		if (players.size() == 0) {
			ev.setCanceled(true);

			return;
		}

		if (players.size() == 1) {
			giveDropsToPlayer(players.iterator().next(), ev.getDrops().iterator());

			return;
		}

		List<ItemStack> drops = new ObjectArrayList<>(ev.getDrops().size());
		int totalItems = 0;

		for (ItemEntity entity : ev.getDrops()) {
			if (!entity.getItem().isEmpty())
				drops.add(entity.getItem());
		}

		Collections.shuffle(drops);
		ev.getDrops().clear();

		for (ItemStack stack : drops) {
			totalItems += stack.getCount();
		}

		Object2IntOpenHashMap<Component> messages = new Object2IntOpenHashMap<>();
		int perPlayerDrops = Math.max(1, totalItems / players.size());
		int playerDropCount = 0;
		Random random = ThreadLocalRandom.current();

		while (!drops.isEmpty() && !players.isEmpty()) {
			int dropIndex = random.nextInt(drops.size());
			ItemStack drop = drops.get(dropIndex);
			Player player = players.iterator().next();
			int amount = 1 + random.nextInt(Math.min(drop.getCount(), perPlayerDrops - playerDropCount));
			playerDropCount += amount;
			Component displayName = drop.getHoverName();

			if (amount == drop.getCount()) {
				if (!player.getInventory().add(drop)) {
					players.remove(player);
					playerDropCount = 0;

					for (Object2IntMap.Entry<Component> entry : messages.object2IntEntrySet()) {
						player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.drops"), ChatFormatting.GREEN, Component.literal(String.valueOf(entry.getIntValue())), entry.getKey()));
					}

					continue;
				}
				else {
					messages.compute(displayName, (name, count) -> count != null ? count + amount : amount);
					drops.remove(dropIndex);
				}
			}
			else {
				ItemStack newStack = drop.copy();

				drop.setCount(drop.getCount() - amount);
				newStack.setCount(amount);

				if (!player.getInventory().add(newStack)) {
					players.remove(player);
					playerDropCount = 0;

					for (Object2IntMap.Entry<Component> entry : messages.object2IntEntrySet()) {
						player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.drops"), ChatFormatting.GREEN, Component.literal(String.valueOf(entry.getIntValue())), entry.getKey()));
					}

					continue;
				}
				else {
					messages.compute(displayName, (name, count) -> count != null ? count + amount : amount);
				}
			}

			if (playerDropCount >= perPlayerDrops) {
				players.remove(player);
				playerDropCount = 0;
			}
		}

		if (!players.isEmpty()) {
			Player player = players.iterator().next();

			for (Object2IntMap.Entry<Component> entry : messages.object2IntEntrySet()) {
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.drops"), ChatFormatting.GREEN, Component.literal(String.valueOf(entry.getIntValue())), entry.getKey()));
			}
		}

		LivingEntity entity = ev.getEntity();

		for (ItemStack stack : drops) {
			ev.getDrops().add(new ItemEntity(entity.level(), entity.getX(), entity.getY() + 0.5, entity.getZ(), stack));
		}
	}

	private static void giveDropsToPlayer(Player player, Iterator<ItemEntity> iterator) {
		Object2IntOpenHashMap<Component> messages = new Object2IntOpenHashMap<>();

		while (iterator.hasNext()) {
			ItemStack item = iterator.next().getItem();
			int amount = item.getCount();
			Component displayName = item.getHoverName();

			if (player.getInventory().add(item)) {
				messages.compute(displayName, (name, count) -> count != null ? count + amount : amount);
				iterator.remove();
			}
			else {
				break;
			}
		}

		for (Object2IntMap.Entry<Component> entry : messages.object2IntEntrySet()) {
			player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("nowhere.boss.drops"), ChatFormatting.GREEN, Component.literal(String.valueOf(entry.getIntValue())), entry.getKey()));
		}
	}
}
