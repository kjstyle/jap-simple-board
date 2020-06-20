package com.kjstyle.jpaboard.exceptions;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
        super("존재하지 않는 회원입니다.");
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserException(Throwable cause) {
        super(cause);
    }
}
