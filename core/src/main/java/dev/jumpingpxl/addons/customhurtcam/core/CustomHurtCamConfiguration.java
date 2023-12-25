package dev.jumpingpxl.addons.customhurtcam.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.VersionCompatibility;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
public class CustomHurtCamConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  @SettingSection("damageTilt")
  private final ConfigProperty<Boolean> showDamageTilt = new ConfigProperty<>(true);

  @SwitchSetting
  @VersionCompatibility("1.19.4<*")
  @SettingRequires("showDamageTilt")
  private final ConfigProperty<Boolean> directionalDamageTilt = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public boolean disabledDamageTilt() {
    return this.enabled.get() && !this.showDamageTilt.get();
  }

  public boolean enabledDirectionalDamageTilt() {
    return this.enabled.get() && this.directionalDamageTilt.get();
  }
}
