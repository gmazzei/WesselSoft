package com.matematicaSuperior.calculadora;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

import validacion.exception.ExponenteOrdenDebeSerNumeroNaturalException;
import validacion.exception.FormatoDesconocidoDeNumeroComplejoException;
import validacion.exception.HayValoresSinCompletarException;
import domain.NumeroComplejo;
import domain.Primitiva;

public class CalculadoraViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String operacion;
	public String numeroA;
	public String numeroB;
	public String resultado;
	public List<Primitiva> listaPrimitivas;
	public List<NumeroComplejo> listaRaices;
	public Calculadora domainModel = new Calculadora();
	
	public void ejecutar() {
		try {
			Method method = this.getClass().getDeclaredMethod(this.operacion);
			method.invoke(this);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
	
	private NumeroComplejo parsearComplejo(String cadena) {
		NumeroComplejo resultado = new NumeroComplejo();
		int posicionPuntoYComa;
		
		String PATRONBINOMICA = "^([(]{1}\\-?([0-9]*|[0-9]*,[0-9]*);\\-?([0-9]*|[0-9]*,[0-9]*)[)]{1})$";
		String PATRONPOLAR = "^((\\[)\\-?([0-9]*|[0-9]*,[0-9]*);\\-?([0-9]*|[0-9]*,[0-9]*)(\\]))$";
		
		Pattern patronBinomica = Pattern.compile(PATRONBINOMICA);
		Pattern patronPolar = Pattern.compile(PATRONPOLAR);
		
		if (patronBinomica.matcher(cadena).matches()) {
			String nuevaCadena = cadena.replaceAll(",", "."); 
			posicionPuntoYComa = nuevaCadena.indexOf(";");
			String strReal = nuevaCadena.substring(1, posicionPuntoYComa);
			String strImaginario = nuevaCadena.substring(posicionPuntoYComa+1, nuevaCadena.length()-1);
			
			resultado.setReal(Double.parseDouble(strReal));
			resultado.setImaginario(Double.parseDouble(strImaginario));
		} else if (patronPolar.matcher(cadena).matches()) {
			String nuevaCadena = cadena.replaceAll(",", "."); 
			posicionPuntoYComa = nuevaCadena.indexOf(";");
			String strModulo = nuevaCadena.substring(1, posicionPuntoYComa);
			String strArgumento = nuevaCadena.substring(posicionPuntoYComa+1, nuevaCadena.length()-1);
			
			resultado.setModulo(Double.parseDouble(strModulo));
			resultado.setArgumento(Double.parseDouble(strArgumento));
		} else {
			throw new FormatoDesconocidoDeNumeroComplejoException();
		}
		
		
		return resultado;
	}
	
	private Integer parsearEntero(String cadena) {
		Integer resultado = null;
		try {
			resultado = Integer.parseInt(cadena);
		} catch (Exception e) {
			throw new ExponenteOrdenDebeSerNumeroNaturalException();
		}
		
		if (resultado <= 0) {
			throw new ExponenteOrdenDebeSerNumeroNaturalException();
		}
		
		return resultado;
	}
	
	
	public void sumar() {
		this.validar();
		NumeroComplejo numA = this.parsearComplejo(this.numeroA);
		NumeroComplejo numB = this.parsearComplejo(this.numeroB);
		this.resultado = domainModel.sumar(numA, numB).toString();
	}
	
	public void restar() {
		this.validar();
		NumeroComplejo numA = this.parsearComplejo(this.numeroA);
		NumeroComplejo numB = this.parsearComplejo(this.numeroB);
		this.resultado = domainModel.restar(numA, numB).toString();
	}
	
	public void multiplicar() {
		this.validar();
		NumeroComplejo numA = this.parsearComplejo(this.numeroA);
		NumeroComplejo numB = this.parsearComplejo(this.numeroB);
		this.resultado = domainModel.multiplicar(numA, numB).toString();
	}
	
	public void dividir() {
		this.validar();
		NumeroComplejo numA = this.parsearComplejo(this.numeroA);
		NumeroComplejo numB = this.parsearComplejo(this.numeroB);
		this.resultado = domainModel.dividir(numA, numB).toString();
	}
	
	public void potencia() {
		this.validar();
		NumeroComplejo numA = this.parsearComplejo(this.numeroA);
		Integer numB = this.parsearEntero(this.numeroB);
		this.resultado = domainModel.potencia(numA, numB).toString();
	}
	
	public void radicacion() {
		this.validar();
		NumeroComplejo numA = this.parsearComplejo(this.numeroA);
		Integer numB = this.parsearEntero(this.numeroB);
		
		this.listaRaices = domainModel.radicacion(numA, numB);
	}
	

	public void primitivas() {
		Integer orden = this.parsearEntero(this.numeroA);
		this.listaPrimitivas = domainModel.primitivas(orden);
	}

	private void validar() {
		if (this.numeroA == null || this.numeroB == null) {
			throw new HayValoresSinCompletarException();
		}
	}
	
	
	//GETTERS & SETTERS
	
	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getNumeroA() {
		return numeroA;
	}

	public void setNumeroA(String numeroA) {
		this.numeroA = numeroA;
	}

	public String getNumeroB() {
		return numeroB;
	}

	public void setNumeroB(String numeroB) {
		this.numeroB = numeroB;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Calculadora getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(Calculadora domainModel) {
		this.domainModel = domainModel;
	}

	public List<Primitiva> getListaPrimitivas() {
		return listaPrimitivas;
	}

	public void setListaPrimitivas(List<Primitiva> listaPrimitivas) {
		this.listaPrimitivas = listaPrimitivas;
	}

	public List<NumeroComplejo> getListaRaices() {
		return listaRaices;
	}

	public void setListaRaices(List<NumeroComplejo> listaRaices) {
		this.listaRaices = listaRaices;
	}
	
	
	
	
}
