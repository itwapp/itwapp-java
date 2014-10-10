package io.itwapp.exception;

public class APIException extends RuntimeException {

    public APIException()   {
        super();
    }

    public APIException(String s)   {
        super(s);
    }

    public APIException(Exception e)   {
        super(e);
    }

    public APIException(String s, Exception e)   {
        super(s, e);
    }

}
