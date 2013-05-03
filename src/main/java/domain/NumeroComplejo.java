package domain;

import java.io.Serializable;
import java.text.DecimalFormat;

public class NumeroComplejo implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Double real;
	private Double imaginario;
	private Double modulo;
	private Double argumento;
	
	public NumeroComplejo() {}
	
	
	public String formaBinomica() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(5);
		return "(" + df.format(this.getReal()) + ";" + df.format(this.getImaginario()) +")";
	}
	
	public String formaPolar() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(5);
		return "[" + df.format(this.getModulo()) + ";" + (df.format(this.getArgumento()/Math.PI)) +"*PI]";
	}	
	
	
	//GETTERS & SETTERS
	
	@Override
	public String toString() {
		return this.formaBinomica();
	}

	public double getReal() {
		if (modulo != null && argumento != null) {
			this.real = this.modulo * Math.cos(this.argumento);
		}
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginario() {
		if(modulo != null && argumento != null) {
			this.imaginario = this.modulo * Math.sin(this.argumento);
		}
				
		return imaginario;
	}

	public void setImaginario(double imaginario) {
		this.imaginario = imaginario;
	}

	public double getModulo() {
		if (real != null && imaginario != null) {
			this.modulo = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginario, 2));
		}
		return modulo;
	}

	public void setModulo(double modulo) {
		this.modulo = modulo;
	}

	public double getArgumento() {
		if (real != null && imaginario != null) {
			this.argumento = Math.atan(this.imaginario/this.real);
			
			//Correccion del argumento segun cuadrante
			if (this.real < 0) {
				this.argumento += Math.PI;
			}
			if (this.real >= 0 && this.imaginario < 0) {
				this.argumento += 2*Math.PI;
			}
		}
		return argumento;
	}

	public void setArgumento(double argumento) {
		this.argumento = argumento;
	}
	
	
	
}
