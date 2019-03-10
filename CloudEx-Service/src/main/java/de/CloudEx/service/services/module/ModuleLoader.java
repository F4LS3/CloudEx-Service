package de.CloudEx.service.services.module;

import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;
import de.CloudEx.service.services.logging.level.MODULE;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ModuleLoader implements ModuleManager {

    private List<CloudModule> loadedModules = new ArrayList<>();

    public void start() {
        new Logger(MODULE.class, "Initializing the ModuleLoader...");
        File[] files = new File("./modules/").listFiles();
        for (File f : files) {
            loadModule(f);
        }
        for (CloudModule module : loadedModules) {
            module.onEnable();
        }
        new Logger(MODULE.class, "Started ModuleLoder!");
    }

    public void stop() {
        for(CloudModule module : loadedModules)
            module.onDisable();
    }

    public void loadModule(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            Manifest manifest = jarFile.getManifest();
            Attributes attrib = manifest.getMainAttributes();
            String main = attrib.getValue(Attributes.Name.MAIN_CLASS);

            Class cl = new URLClassLoader(new URL[]{new File(file.getPath()).toURL()}).loadClass(main);
            Class[] interfaces = cl.getInterfaces();
            boolean isPlugin = false;
            for (int i = 0; i < interfaces.length && !isPlugin; i++) {
                if(interfaces[i].getName().equalsIgnoreCase("de.CloudEx.service.services.module.CloudModule")) {
                    isPlugin = true;
                }
            }
            if(isPlugin) {
                CloudModule cloudModule = (CloudModule) cl.newInstance();
                loadedModules.add(cloudModule);
                new Logger(MODULE.class, "Das Modul '" + file.getName().replaceAll(".jar", "") + "' wurde erfolgreich geladen!");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addModule(CloudModule cloudModule) {

    }

    public void removeModule(CloudModule cloudModule) {

    }
}
