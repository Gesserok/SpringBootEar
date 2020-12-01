package org.example.multimodule.exceptions;

public class ODPResponseSuccessFalseException extends ODPConnectorException {
    public ODPResponseSuccessFalseException() {
    }

    public ODPResponseSuccessFalseException(String message) {
        super(message);
    }

    public ODPResponseSuccessFalseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPResponseSuccessFalseException(Throwable cause) {
        super(cause);
    }

    public ODPResponseSuccessFalseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
