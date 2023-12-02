package org.example.repository;

public class DBException extends RuntimeException{
    public DBException(String message, Exception ex) {
        super(message, ex);
    }

    public DBException(Exception ex) {
        super(ex);
    }
}
