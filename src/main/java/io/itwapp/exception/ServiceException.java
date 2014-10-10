package io.itwapp.exception;

public class ServiceException extends RuntimeException {
    public ServiceException()   {
        super();
    }

    public ServiceException(String s)   {
        super(s);
    }

    public ServiceException(Exception e)   {
        super(e);
    }

    public ServiceException(String s, Exception e)   {
        super(s, e);
    }
}
