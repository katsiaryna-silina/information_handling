package by.epam.silina.information_handling.exception;

import java.io.IOException;

public class TextUtilException extends IOException {
    public TextUtilException(String message) {
        super(message);
    }

    public TextUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextUtilException(Throwable cause) {
        super(cause);
    }
}
