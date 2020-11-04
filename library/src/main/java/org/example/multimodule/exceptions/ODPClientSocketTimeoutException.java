package org.example.multimodule.exceptions;

public class ODPClientSocketTimeoutException extends ODPClientException {
    public ODPClientSocketTimeoutException() {
    }

    public ODPClientSocketTimeoutException(String message) {
        super(message);
    }

    public ODPClientSocketTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public ODPClientSocketTimeoutException(Throwable cause) {
        super(cause);
    }

    public ODPClientSocketTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
