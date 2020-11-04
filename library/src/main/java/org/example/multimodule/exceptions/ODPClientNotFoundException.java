package org.example.multimodule.exceptions;

public class ODPClientNotFoundException extends ODPClientException {
    public ODPClientNotFoundException() {
    }

    public ODPClientNotFoundException(String message) {
        super(message);
    }

    public ODPClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPClientNotFoundException(Throwable cause) {
        super(cause);
    }

    public ODPClientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
