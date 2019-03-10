package de.CloudEx.service.network.request;

public class RequestService {

    public static String request_auth() {
        return "request_auth";
    }

    public static String request_create() {
        return "request_create ";
    }

    public static String request_delete() {
        return "request_delete ";
    }

    public static String request_custom(String customRequest) {
        return customRequest;
    }
}
