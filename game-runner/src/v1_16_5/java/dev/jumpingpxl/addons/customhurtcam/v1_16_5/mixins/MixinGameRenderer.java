package dev.jumpingpxl.addons.customhurtcam.v1_16_5.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.jumpingpxl.addons.customhurtcam.core.CustomHurtCam;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

  @Inject(
      method = "bobHurt",
      at = @At(
          value = "FIELD",
          target = "Lnet/minecraft/world/entity/LivingEntity;hurtDuration:I"
      ),
      cancellable = true
  )
  public void customHurtCam$disableHurtCameraEffect(PoseStack lvt_1_1_, float lvt_2_1_,
      CallbackInfo ci) {
    if (CustomHurtCam.get().configuration().disabledDamageTilt()) {
      ci.cancel();
    }
  }
}
