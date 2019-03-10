package de.CloudEx.service.services.logging.level;

public class NETTY implements LEVEL {

    public void printStringLeveledMessage(Object loggedMessage) {
        System.out.println("[NETTY] " + loggedMessage);
    }
}
