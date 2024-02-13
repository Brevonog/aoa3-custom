package net.tslat.aoa3.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.block.blockentity.TrophyBlockEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

public class TrophyRenderer implements BlockEntityRenderer<TrophyBlockEntity> {
	public TrophyRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(TrophyBlockEntity blockEntity, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Entity entity = blockEntity.getCachedEntity();

		if (entity != null) {
			matrix.pushPose();
			matrix.translate(0.5f, 0, 0.5f);

			float scale = 0.53125F;
			float maxScale = Math.max(entity.getBbWidth(), entity.getBbHeight());
			double hover = (Mth.sin((float)blockEntity.hoverStep + partialTicks) / 30d);
			blockEntity.hoverStep += hover * 0.01f;

			if (maxScale > 1.0D)
				scale /= maxScale;

			matrix.translate(0, -0.1, 0);
			matrix.scale(scale, scale, scale);
			matrix.translate(0, (1 / scale), 0);

			if (blockEntity.isOriginal() && partialTicks > 0.95f)
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.005f, 10, ColourUtil.RGB(255, 200, 0)), blockEntity.getBlockPos().getX() + 0.5f, blockEntity.getBlockPos().getY() + 0.9 + ((entity.getBbHeight() / 2f) * scale), blockEntity.getBlockPos().getZ() + 0.5f, RandomUtil.randomGaussianValue() * 0.5f, RandomUtil.randomGaussianValue() * 0.5f, RandomUtil.randomGaussianValue() * 0.5f);

			if (AoAConfigs.CLIENT.rotatingTrophies.get())
				matrix.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, blockEntity.getPrevMobRotation(), blockEntity.getMobRotation()) * 30.0F));

			Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0, 0, 0, 0, 0, matrix, buffer, combinedLight);
			matrix.popPose();
		}
	}
}