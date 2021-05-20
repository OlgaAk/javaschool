package io.github.olgaak.exception;

public class ActionNotAllowedException extends Exception {
    public ActionNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
