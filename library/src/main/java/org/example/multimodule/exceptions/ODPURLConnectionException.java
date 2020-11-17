package org.example.multimodule.exceptions;

public class ODPURLConnectionException extends ODPConnectorException {
    public ODPURLConnectionException() {
    }

    public ODPURLConnectionException(String message) {
        super(message);
    }

    public ODPURLConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPURLConnectionException(Throwable cause) {
        super(cause);
    }

    public ODPURLConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
