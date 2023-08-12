package net.tslat.aoa3.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.hud.toasts.AbilityUnlockToast;
import net.tslat.aoa3.client.gui.hud.toasts.LevelRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.ResourceRequirementToast;
import net.tslat.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import net.tslat.aoa3.client.particle.*;
import net.tslat.aoa3.client.render.entity.misc.OccultBlockRenderer;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.packet.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.content.item.misc.WornBook;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ClientOperations {
	public static final ClientPlayerDataManager CLIENT_PLAYER_DATA = new ClientPlayerDataManager();

	public static Level getWorld() {
		return Minecraft.getInstance().level;
	}

	public static Player getPlayer() {
		return Minecraft.getInstance().player;
	}

	public static void displayWornBookGui() {
		Player player = Minecraft.getInstance().player;
		ItemStack bookStack = player.getMainHandItem().getItem() == AoAItems.WORN_BOOK.get() ? player.getMainHandItem() : player.getOffhandItem();

		Minecraft.getInstance().setScreen(new BookViewScreen(new BookViewScreen.WrittenBookAccess(WornBook.getBook(bookStack))));
	}

	public static void displayBlankRealmstoneGui() {
		Minecraft.getInstance().setScreen(new BlankRealmstoneScreen());
	}

	public static void addRecoil(final float recoil, final int firingTime) {
		RecoilRenderer.addRecoil(recoil);
	}

	public static void addOccultBlocks(int renderUntil, List<OccultBlockRenderer.OccultBlock> blocks) {
		OccultBlockRenderer.addOccultBlocks(renderUntil, blocks);
	}

	public static void registerParticleFactories(RegisterParticleProvidersEvent ev) {
		ev.registerSpriteSet(AoAParticleTypes.PORTAL_FLOATER.get(), PortalFloaterParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.SPARKLER.get(), SparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.FLICKERING_SPARKLER.get(), FlickeringSparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.LINGERING_SPARKLER.get(), LingeringSparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.RAINBOW_SPARKLER.get(), RainbowSparklerParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.SWIRLY.get(), SwirlyParticle.Factory::new);
		ev.registerSpecial(AoAParticleTypes.FLOATING_ITEM_FRAGMENT.get(), new FloatingItemFragmentParticle.Factory());
		ev.registerSpriteSet(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), FreezingSnowflakeParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.BURNING_FLAME.get(), BurningFlameParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.SANDSTORM.get(), SandstormParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.ORB.get(), OrbParticle.Factory::new);
		ev.registerSpriteSet(AoAParticleTypes.FIRE_AURA.get(), FireAuraParticle.Factory::new);
	}

	public static void addToast(ToastPopupPacket.ToastPopupType type, Object subject, Object value) {
		switch (type) {
			case SKILL_REQUIREMENT -> {
				AoASkill skill = AoASkills.getSkill((ResourceLocation)subject);

				if (AoAConfigs.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new LevelRequirementToast(skill, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientLevels", ChatFormatting.RED, skill.getName(), LocaleUtil.numToComponent((Integer)value)));
				}
			}
			case RESOURCE_REQUIREMENT -> {
				AoAResource resource = AoAResources.getResource((ResourceLocation)subject);

				if (AoAConfigs.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new ResourceRequirementToast(resource, (Float)value));
				}
				else {
					Minecraft.getInstance().player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientResource", ChatFormatting.RED, resource.getName(), Component.literal(NumberUtil.roundToNthDecimalPlace((Float)value, 2))));
				}
			}
			case ABILITY_UNLOCK -> {
				AoASkill skill2 = AoASkills.getSkill((ResourceLocation)subject);
				AoAAbility ability = AoAAbilities.getAbility((ResourceLocation)value);

				if (AoAConfigs.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new AbilityUnlockToast(skill2, ability));
				}
				else {
					Minecraft.getInstance().player.sendSystemMessage(LocaleUtil.getLocaleMessage("message.feedback.abilityUnlocked", ChatFormatting.GREEN, skill2.getName(), ability.getName()));
				}
			}
		}
	}

	public static void doSilencerSilence(SilencerEntity silencer) {
		if (!SilencerEntity.isClientNearby) {
			Minecraft mc = Minecraft.getInstance();

			if (mc.getSoundManager().soundEngine.getVolume(SoundSource.MASTER) > 0) {
				if (silencer.distanceToSqr(mc.player) < 8 * 8) {
					SilencerEntity.isClientNearby = true;
					SilencerEntity.previousGain = mc.getSoundManager().soundEngine.listener.getGain();

					mc.getSoundManager().soundEngine.updateCategoryVolume(SoundSource.MASTER, 0);
				}
			}
		}
	}

	public static void syncPatchouliBooks(List<ResourceLocation> books) {
		AdventGuiTabLore.syncBooks(books);
	}

	public static void adjustPlayerMovement(@Nullable Float x, @Nullable Float y, @Nullable Float z, UpdateClientMovementPacket.Operation operation) {
		Player player = Minecraft.getInstance().player;
		Vec3 velocity = player.getDeltaMovement();

		switch (operation) {
			case SET -> player.setDeltaMovement(x != null ? x : velocity.x(), y != null ? y : velocity.y(), z != null ? z : velocity.z());
			case ADD -> player.setDeltaMovement(velocity.add(x != null ? x : 0, y != null ? y : 0, z != null ? z : 0));
			case MULTIPLY -> player.setDeltaMovement(velocity.multiply(x != null ? x : 1, y != null ? y : 1, z != null ? z : 1));
			case MAX -> player.setDeltaMovement(x != null ? Math.min(x, velocity.x()) : velocity.x(), y != null ? Math.min(y, velocity.y()) : velocity.y(), z != null ? Math.min(z, velocity.z()) : velocity.z());
			case MIN -> player.setDeltaMovement(x != null ? Math.max(x, velocity.x()) : velocity.x(), y != null ? Math.max(y, velocity.y()) : velocity.y(), z != null ? Math.max(z, velocity.z()) : velocity.z());
		}
	}

	public static boolean harvestAdditionalBlock(BlockPos breakPos) {
		if (Minecraft.getInstance().gameMode == null)
			return false;

		return Minecraft.getInstance().gameMode.destroyBlock(breakPos);
	}

	public static Component getPlayerName() {
		return Minecraft.getInstance().player.getDisplayName();
	}

	public static void addParticle(ParticleBuilder particleBuilder, @Nullable Consumer particleConsumer) {
		Minecraft mc = Minecraft.getInstance();

		if (mc == null || mc.particleEngine == null)
			return;

		Camera camera = mc.gameRenderer.getMainCamera();

		if (!camera.isInitialized())
			return;

		if (!particleBuilder.getShouldForce() && mc.levelRenderer.calculateParticleLevel(particleBuilder.getIsAmbient()) == ParticleStatus.MINIMAL)
			return;

		Supplier<Vec3> nextPos = particleBuilder.getPositionGenerator();
		double cutoffDist = particleBuilder.getShouldForce() ? -1 : Math.pow(particleBuilder.getCutoffDistance(), 2);
		Vec3 velocity = particleBuilder.getVelocity();

		for (int i = 0; i < particleBuilder.getCount(); i++) {
			Vec3 pos = nextPos.get();

			if (!particleBuilder.getShouldForce() && camera.getPosition().distanceToSqr(pos) > cutoffDist)
				continue;

			for (int j = 0; j < particleBuilder.getCountPerPosition(); j++) {
				Particle particle = mc.particleEngine.createParticle(particleBuilder.getParticle(), pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z);

				if (particle == null)
					continue;

				if (particleBuilder.getColourOverride() != null) {
					ColourUtil.Colour colour = particleBuilder.getColourOverride();

					particle.setColor(colour.red(), colour.green(), colour.blue());
					particle.setAlpha(colour.alpha());
				}

				if (particleBuilder.getLifespan() > 0)
					particle.setLifetime(particleBuilder.getLifespan());

				if (particleBuilder.getGravity() != Float.MAX_VALUE)
					particle.gravity = particleBuilder.getGravity();

				if (particleBuilder.getInertia() > 0)
					particle.friction = particleBuilder.getInertia();

				if (particleBuilder.getScaleMod() != 1)
					particle.scale(particleBuilder.getScaleMod());

				if (particleConsumer != null)
					particleConsumer.accept(particle);
			}
		}
	}

	public static GameType getGameMode() {
		return Minecraft.getInstance().gameMode.getPlayerMode();
	}

	public static boolean isPressingCrouchKey() {
		return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), Minecraft.getInstance().options.keyShift.getKey().getValue());
	}

	public static void playSoundFromBuilder(SoundBuilder soundBuilder) {
		Minecraft minecraft = Minecraft.getInstance();
		SoundInstance sound;
		double delay = soundBuilder.getScheduledDelay() + (soundBuilder.getApplyTimeDilation() ? Math.sqrt(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().distanceToSqr(soundBuilder.getLocation())) * 0.5d : 0);

		if (soundBuilder.getCategory() == SoundSource.MUSIC) {
			Music music = new Music(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundBuilder.getSound()), soundBuilder.getScheduledDelay(), soundBuilder.getScheduledDelay(), true);

			if (!minecraft.getMusicManager().isPlayingMusic(music)) {
				minecraft.getMusicManager().stopPlaying();
				minecraft.getMusicManager().startPlaying(music);
			}

			return;
		}
		else if (soundBuilder.getFollowingEntity() != null) {
			sound = new EntityBoundSoundInstance(soundBuilder.getSound(), soundBuilder.getCategory(), soundBuilder.getRadius() / 16f, soundBuilder.getPitch(), soundBuilder.getFollowingEntity(), soundBuilder.getSeed()) {
				@Override
				public boolean isLooping() {
					return soundBuilder.getIsLooping();
				}

				@Override
				public int getDelay() {
					return soundBuilder.getLoopDelay();
				}
			};
		}
		else {
			if (soundBuilder.getLocation() != null) {
				sound = new SimpleSoundInstance(soundBuilder.getSound().getLocation(), soundBuilder.getCategory(), soundBuilder.getRadius() / 16f, soundBuilder.getPitch(), RandomSource.create(soundBuilder.getSeed()), soundBuilder.getIsLooping(), (int)delay, soundBuilder.getIsInWorld() ? SoundInstance.Attenuation.LINEAR : SoundInstance.Attenuation.NONE, soundBuilder.getLocation().x(), soundBuilder.getLocation().y(), soundBuilder.getLocation().z(), false);
			}
			else {
				sound = new SimpleSoundInstance(soundBuilder.getSound().getLocation(), soundBuilder.getCategory(), soundBuilder.getRadius() / 16f, soundBuilder.getPitch(), RandomSource.create(soundBuilder.getSeed()), soundBuilder.getIsLooping(), (int)delay, soundBuilder.getIsInWorld() ? SoundInstance.Attenuation.LINEAR : SoundInstance.Attenuation.NONE, 0, 0, 0, false);
			}
		}

		if (delay > 0) {
			minecraft.getSoundManager().playDelayed(sound, (int)delay);
		}
		else {
			minecraft.getSoundManager().play(sound);
		}
	}

	public static void stopSoundFromBuilder(SoundBuilder soundBuilder) {
		if (soundBuilder.getCategory() == SoundSource.MUSIC) {
			Music music = new Music(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundBuilder.getSound()), soundBuilder.getScheduledDelay(), soundBuilder.getScheduledDelay(), true);
			MusicManager musicManager = Minecraft.getInstance().getMusicManager();

			if (musicManager.isPlayingMusic(music))
				musicManager.stopPlaying();
		}
		else {
			Minecraft.getInstance().getSoundManager().stop(soundBuilder.getSound().getLocation(), soundBuilder.getCategory());
		}
	}

	public static void applyFluidRenderType(LiquidBlock liquid) {
		ItemBlockRenderTypes.setRenderLayer(liquid.getFluid().getFlowing(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(liquid.getFluid().getSource(), RenderType.translucent());
	}
}
