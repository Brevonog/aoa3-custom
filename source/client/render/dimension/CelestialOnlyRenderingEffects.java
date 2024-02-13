package net.tslat.aoa3.client.render.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;

public class CelestialOnlyRenderingEffects extends DimensionSpecialEffects {
    public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "celestial_only");

    public CelestialOnlyRenderingEffects() {
        super(Float.NaN, true, SkyType.NORMAL, false, false);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
        return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
    }

    @Override // Show fog at X/Z
    public boolean isFoggyAt(int posX, int posZ) {
        return false;
    }
}