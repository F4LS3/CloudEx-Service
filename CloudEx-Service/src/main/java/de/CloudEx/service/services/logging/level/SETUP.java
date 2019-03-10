package de.CloudEx.service.services.logging.level;

public class SETUP implements LEVEL {

    public void printStringLeveledMessage(Object loggedMessage) {
        System.out.println("[SETUP] " + loggedMessage);
    }
}
