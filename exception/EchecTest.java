package exception;


/**
 * Est appel� pour faire volontairement cracher les tests 
 */
public class EchecTest extends RuntimeException {

    public EchecTest(String msg) {
        super(msg);
    }
}
