package com.jacky.lession.err;

public class BadLoginStatuteException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -4635629026063489819L;

    public BadLoginStatuteException(String s) {
        super(s);
    }
}
