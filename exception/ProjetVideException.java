package exception;

/**
 * est appel� lorsqu'un projet est vide
 * 
 */
public class ProjetVideException extends RuntimeException {
	
	public ProjetVideException(String msg) {
        super(msg);
    }
}
