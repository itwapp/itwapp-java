package io.itwapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException()   {
        super();
    }

    public ResourceNotFoundException(String s)   {
        super(s);
    }

    public ResourceNotFoundException(Exception e)   {
        super(e);
    }

    public ResourceNotFoundException(String s, Exception e)   {
        super(s, e);
    }
}
