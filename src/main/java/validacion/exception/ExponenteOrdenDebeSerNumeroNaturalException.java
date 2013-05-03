package validacion.exception;

public class ExponenteOrdenDebeSerNumeroNaturalException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExponenteOrdenDebeSerNumeroNaturalException() {
		super("El exponente/orden debe ser un numero natural");
	}
}
