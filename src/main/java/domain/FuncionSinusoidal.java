package domain;

import java.io.Serializable;
import java.text.DecimalFormat;

public class FuncionSinusoidal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String tipo;
	public Double amplitud;
	public Double frecuencia;
	public Double fase;
	
	
	
	public FuncionSinusoidal(String tipo, Double amplitud, Double frecuencia, Double fase) {
		super();
		this.tipo = tipo;
		this.amplitud = amplitud;
		this.frecuencia = frecuencia;
		this.fase = fase;
	}
	
	public String formaCoseno() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(5);
		
		String nuevaAmplitud = df.format(this.amplitud);
		String nuevaFrecuencia = df.format(this.frecuencia);
		String nuevaFase = df.format(Math.abs(this.fase));
		
		String signo;
		if (this.fase >= 0) {
			signo = "+";
		} else {
			signo = "-";
		}
		
		return nuevaAmplitud + tipo + "(" + nuevaFrecuencia + "t" + signo + nuevaFase + ")";
	}
	
	public String formaSeno() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(5);
		
		String nuevoTipo = "sen";
		String nuevaAmplitud = df.format(this.amplitud);
		String nuevaFrecuencia = df.format(this.frecuencia);
		String nuevaFase = df.format(Math.abs(this.fase + (Math.PI/2)));
		
		String signo;
		if (this.fase >= 0) {
			signo = "+";
		} else {
			signo = "-";
		}
		
		return nuevaAmplitud + nuevoTipo + "(" + nuevaFrecuencia + "t" + signo + nuevaFase + ")";
	}
	
	@Override
	public String toString() {
		return this.formaCoseno();
	}




	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getAmplitud() {
		return amplitud;
	}
	public void setAmplitud(Double amplitud) {
		this.amplitud = amplitud;
	}
	public Double getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(Double frecuencia) {
		this.frecuencia = frecuencia;
	}
	public Double getFase() {
		return fase;
	}
	public void setFase(Double fase) {
		this.fase = fase;
	}
	
	
}
