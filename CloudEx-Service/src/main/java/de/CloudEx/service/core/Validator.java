package de.CloudEx.service.core;

import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.ERROR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Validator {

    public static boolean validateWrapper(String wrapperName, String wrapperToken) {
        try {
            File wrapperDir = new File("./local/wrapper");
            ArrayList<String> wrapperNames = new ArrayList<>();
            String validName = "";

            for (File file : wrapperDir.listFiles()) {
                if (file.isDirectory()) {
                    wrapperNames.add(file.getName());
                }
            }

            for (String name : wrapperNames) {
                if (wrapperName.equalsIgnoreCase(name)) {
                    validName = name;
                }
            }

            if (validName != "") {
                File wrapperToke = new File("./local/wrapper/" + validName + "/TOKEN.wrapper");
                BufferedReader reader = new BufferedReader(new FileReader(wrapperToke));
                if(reader.readLine() != null) {
                    if(reader.readLine().equalsIgnoreCase(wrapperToken)) {
                        return true;

                    } else {
                        return false;
                    }

                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            new Logger(ERROR.class, "Validator ran into an error while validating Wrapper: "+e);
        }
        return false;
    }
}
