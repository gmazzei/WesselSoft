package domain;

import java.io.Serializable;

public class Primitiva implements Serializable {
	
	private static final long serialVersionUID = 1L;


	public Primitiva(Integer indice, NumeroComplejo numero) {
		super();
		this.indice = indice;
		this.numero = numero;
	}
	
	
	public Integer indice;
	public NumeroComplejo numero;
	
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public NumeroComplejo getNumero() {
		return numero;
	}
	public void setNumero(NumeroComplejo numero) {
		this.numero = numero;
	}

	
}
