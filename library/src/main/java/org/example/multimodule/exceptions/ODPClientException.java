package org.example.multimodule.exceptions;

public class ODPClientException extends ODPConnectorException {
    public ODPClientException() {
    }

    public ODPClientException(String message) {
        super(message);
    }

    public ODPClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPClientException(Throwable cause) {
        super(cause);
    }

    public ODPClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
