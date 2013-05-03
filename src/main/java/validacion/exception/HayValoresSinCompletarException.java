package validacion.exception;

public class HayValoresSinCompletarException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HayValoresSinCompletarException(){
		super("Hay valores sin completar");
	}
	
}
