package io.itwapp.exception;

public class InvalidRequestError extends RuntimeException {

    public InvalidRequestError()   {
        super();
    }

    public InvalidRequestError(String s)   {
        super(s);
    }

    public InvalidRequestError(Exception e)   {
        super(e);
    }

    public InvalidRequestError(String s, Exception e)   {
        super(s, e);
    }

}
