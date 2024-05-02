package dev.jumpingpxl.addons.customhurtcam.v1_20_5.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.jumpingpxl.addons.customhurtcam.core.CustomHurtCam;
import net.labymod.api.volt.annotation.Insert;
import net.labymod.api.volt.callback.InsertInfo;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

  @Insert(
      method = "bobHurt",
      at = @At(
          value = "FIELD",
          target = "Lnet/minecraft/world/entity/LivingEntity;hurtDuration:I"
      ),
      cancellable = true
  )
  public void customHurtCam$disableHurtCameraEffect(PoseStack $$0, float $$1, InsertInfo ci) {
    if (CustomHurtCam.get().configuration().disabledDamageTilt()) {
      ci.cancel();
    }
  }

  @Redirect(
      method = "bobHurt",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/world/entity/LivingEntity;getHurtDir()F"
      )
  )
  public float customHurtCam$getDamageTilt(LivingEntity livingEntity) {
    if (CustomHurtCam.get().configuration().disabledDirectionalDamageTilt()) {
      return 0F;
    }

    return livingEntity.getHurtDir();
  }
}
