package exception;


/**
 * est appelé lorsqu'un cycle est détecté
 * 
 */
public class CycleException extends RuntimeException {

    public CycleException(String msg) {
        super(msg);
    }
}
