package web.com.spring.rest.exceptions;

public class NoPersonFoundException extends Exception{

    public NoPersonFoundException(String message){
        super(message);
    }
}
