package de.CloudEx.service.services.module;

public interface CloudModule {

    boolean onEnable();
    boolean onDisable();
    void setPluginManager(ModuleManager manager);

}
