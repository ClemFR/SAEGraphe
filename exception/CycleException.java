package exception;

public class CycleException extends RuntimeException {

    public CycleException(String msg) {
        super(msg);
    }
}
