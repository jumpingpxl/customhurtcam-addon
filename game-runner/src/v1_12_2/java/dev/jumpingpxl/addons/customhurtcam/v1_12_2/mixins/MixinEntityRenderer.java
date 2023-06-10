package dev.jumpingpxl.addons.customhurtcam.v1_12_2.mixins;

import dev.jumpingpxl.addons.customhurtcam.core.CustomHurtCam;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

  @Inject(
      method = "hurtCameraEffect",
      at = @At(
          value = "FIELD",
          target = "Lnet/minecraft/entity/EntityLivingBase;maxHurtTime:I"
      ),
      cancellable = true
  )
  public void customHurtCam$disableHurtCameraEffect(float lvt_1_1_, CallbackInfo ci) {
    if (CustomHurtCam.get().configuration().disabledDamageTilt()) {
      ci.cancel();
    }
  }
}
