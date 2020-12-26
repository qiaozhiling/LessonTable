package com.jacky.lession.err;

public class NoClassException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 3733695171213533341L;

    public NoClassException() {
        super("本节无课!");
    }
}
