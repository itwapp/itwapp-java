package io.itwapp.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException()   {
        super();
    }

    public UnauthorizedException(String s)   {
        super(s);
    }

    public UnauthorizedException(Exception e)   {
        super(e);
    }

    public UnauthorizedException(String s, Exception e)   {
        super(s, e);
    }
}
