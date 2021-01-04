package org.example.multimodule.exceptions;

public class FieldNameConstantsException extends ODPConnectorException {
    public FieldNameConstantsException() {
    }

    public FieldNameConstantsException(String message) {
        super(message);
    }

    public FieldNameConstantsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldNameConstantsException(Throwable cause) {
        super(cause);
    }

    public FieldNameConstantsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
