package exception;


/**
 * est appel� lorsqu'un cycle est d�tect�
 * 
 */
public class CycleException extends RuntimeException {

    public CycleException(String msg) {
        super(msg);
    }
}
