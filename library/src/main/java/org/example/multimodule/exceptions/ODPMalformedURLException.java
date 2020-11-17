package org.example.multimodule.exceptions;

public class ODPMalformedURLException extends ODPConnectorException {
    public ODPMalformedURLException() {
    }

    public ODPMalformedURLException(String message) {
        super(message);
    }

    public ODPMalformedURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPMalformedURLException(Throwable cause) {
        super(cause);
    }

    public ODPMalformedURLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
