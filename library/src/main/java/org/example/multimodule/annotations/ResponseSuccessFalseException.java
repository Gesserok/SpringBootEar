package org.example.multimodule.annotations;

import org.example.multimodule.exceptions.ODPConnectorException;

public class ResponseSuccessFalseException extends ODPConnectorException {
    public ResponseSuccessFalseException() {
    }

    public ResponseSuccessFalseException(String message) {
        super(message);
    }

    public ResponseSuccessFalseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseSuccessFalseException(Throwable cause) {
        super(cause);
    }

    public ResponseSuccessFalseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
