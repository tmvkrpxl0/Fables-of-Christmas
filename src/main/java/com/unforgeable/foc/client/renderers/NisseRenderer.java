package com.unforgeable.foc.client.renderers;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.client.models.NisseModel;
import com.unforgeable.foc.entities.Nisse;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NisseRenderer<U extends Nisse> extends MobRenderer<U, NisseModel<U>> {
    private static final float shadowRadius = 0.5F;
    private final ResourceLocation texture = new ResourceLocation(Foc.MODID, "textures/entity/nisse.png");

    public NisseRenderer(EntityRendererProvider.Context context) {
        super(context, new NisseModel<>(context.bakeLayer(NisseModel.LAYER_LOCATION)), shadowRadius);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Nisse entity) {
        return texture;
    }
}
