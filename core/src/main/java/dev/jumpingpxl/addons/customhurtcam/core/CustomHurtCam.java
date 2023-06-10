package dev.jumpingpxl.addons.customhurtcam.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class CustomHurtCam extends LabyAddon<CustomHurtCamConfiguration> {

  private static CustomHurtCam instance;

  public static CustomHurtCam get() {
    return instance;
  }

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected void load() {
    instance = this.addonInstance();
  }

  @Override
  protected Class<CustomHurtCamConfiguration> configurationClass() {
    return CustomHurtCamConfiguration.class;
  }
}
