package de.pheru.fx.mvp.exceptions;

public class ViewInitializationException extends RuntimeException {

    public ViewInitializationException() {
    }

    public ViewInitializationException(String message) {
        super(message);
    }

    public ViewInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewInitializationException(Throwable cause) {
        super(cause);
    }
}
