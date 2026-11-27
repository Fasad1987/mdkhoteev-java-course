package ru.mdkhoteev.exception;

public class RepositoryException extends RuntimeException {

    public static final int HTTP_CODE = 500;

    public static final String CODE = "database.error";

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(String message) {
        super(message);
    }
}