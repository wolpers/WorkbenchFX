package com.dlsc.workbenchfx.view.controls.module;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.module.Module;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the standard control used to display {@link Module}s as tabs in the toolbar.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public class Tab extends Control {
  private static final Logger LOGGER = LogManager.getLogger(Tab.class.getName());

  private final Workbench workbench;
  private final ObservableList<Module> modules;

  private final ObjectProperty<Module> module;
  private final StringProperty name;
  private final ObjectProperty<Node> icon;

  /**
   * Constructs a new {@link Tab}.
   *
   * @param workbench which created this {@link Tab}
   */
  public Tab(Workbench workbench) {
    this.workbench = workbench;
    this.modules = workbench.getModules();
    module = new SimpleObjectProperty<>();
    name = new SimpleStringProperty();
    icon = new SimpleObjectProperty<>();
    initModuleListeners();
  }

  private void initModuleListeners() {
    module.addListener(observable -> {
      Module current = getModule();
      name.setValue(current.getName());
      icon.setValue(current.getIcon());
    });
  }

  /**
   * Defines the {@code module} which is being represented by this {@link Tab}.
   *
   * @param module to be represented by this {@link Tab}
   */
  public final void setModule(Module module) {
    LOGGER.trace("Setting reference to module");
    this.module.set(module);
  }

  public Module getModule() {
    return module.get();
  }

  public ReadOnlyObjectProperty<Module> moduleProperty() {
    return module;
  }

  public String getName() {
    return name.get();
  }

  public ReadOnlyStringProperty nameProperty() {
    return name;
  }

  public Node getIcon() {
    return icon.get();
  }

  public ReadOnlyObjectProperty<Node> iconProperty() {
    return icon;
  }

  public Workbench getWorkbench() {
    return workbench;
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new TabSkin(this);
  }
}
