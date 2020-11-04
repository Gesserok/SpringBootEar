package org.example.multimodule.exceptions;

public class ODPConnectorException extends RuntimeException {
    public ODPConnectorException() {
    }

    public ODPConnectorException(String message) {
        super(message);
    }

    public ODPConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPConnectorException(Throwable cause) {
        super(cause);
    }

    public ODPConnectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
