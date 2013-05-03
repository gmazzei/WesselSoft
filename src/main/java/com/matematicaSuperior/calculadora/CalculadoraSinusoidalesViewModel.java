package com.matematicaSuperior.calculadora;

import java.io.Serializable;
import java.lang.reflect.Method;

import validacion.exception.HayValoresSinCompletarException;

import domain.FuncionSinusoidal;

public class CalculadoraSinusoidalesViewModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String operacion;
	public String tipoA;
	public String tipoB;
	public Double amplitudA;
	public Double amplitudB;
	public Double frecuenciaA;
	public Double frecuenciaB;
	public Double faseA;
	public Double faseB;
	public String resultadoCoseno;
	public String resultadoSeno;
	public Calculadora domainModel = new Calculadora();
	
	
	public void ejecutar() {
		try {
			Method method = this.getClass().getDeclaredMethod(this.operacion);
			method.invoke(this);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
	public void sumarSinusoidales() {
		this.validar();
		FuncionSinusoidal funcionA = this.parsearFuncion(this.tipoA, this.amplitudA, this.frecuenciaA, this.faseA);
		FuncionSinusoidal funcionB = this.parsearFuncion(this.tipoB, this.amplitudB, this.frecuenciaB, this.faseB);
		
		if (!funcionA.getFrecuencia().equals(funcionB.getFrecuencia())) {
			throw new RuntimeException("Las funciones deben tener igual frecuencia");
		}
		
		FuncionSinusoidal resultado = domainModel.sumarSinusoidales(funcionA, funcionB);
		
		this.resultadoCoseno = resultado.formaCoseno();
		this.resultadoSeno = resultado.formaSeno();
		
	}

	private FuncionSinusoidal parsearFuncion(String tipo, Double amplitud, Double frecuencia, Double fase) {
		FuncionSinusoidal resultado = new FuncionSinusoidal(tipo, amplitud, frecuencia, fase);

		if (tipo.equals("sen")) {
			resultado.setTipo("cos");
			Double faseAnterior = resultado.getFase();
			resultado.setFase(faseAnterior + Math.PI/2);
		}
		
		return resultado;
	}

	
	private void validar() {
		if (tipoA == null || tipoB == null || amplitudA == null || amplitudB == null || frecuenciaA == null || frecuenciaB == null || faseA == null || faseB == null) {
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

	public String getTipoA() {
		return tipoA;
	}

	public void setTipoA(String tipoA) {
		this.tipoA = tipoA;
	}

	public String getTipoB() {
		return tipoB;
	}

	public void setTipoB(String tipoB) {
		this.tipoB = tipoB;
	}

	public Double getAmplitudA() {
		return amplitudA;
	}

	public void setAmplitudA(Double amplitudA) {
		this.amplitudA = amplitudA;
	}

	public Double getAmplitudB() {
		return amplitudB;
	}

	public void setAmplitudB(Double amplitudB) {
		this.amplitudB = amplitudB;
	}

	public Double getFrecuenciaA() {
		return frecuenciaA;
	}

	public void setFrecuenciaA(Double frecuenciaA) {
		this.frecuenciaA = frecuenciaA;
	}

	public Double getFrecuenciaB() {
		return frecuenciaB;
	}

	public void setFrecuenciaB(Double frecuenciaB) {
		this.frecuenciaB = frecuenciaB;
	}

	public Double getFaseA() {
		return faseA;
	}

	public void setFaseA(Double faseA) {
		this.faseA = faseA;
	}

	public Double getFaseB() {
		return faseB;
	}

	public void setFaseB(Double faseB) {
		this.faseB = faseB;
	}
	
	public String getResultadoCoseno() {
		return resultadoCoseno;
	}
	public void setResultadoCoseno(String resultadoCoseno) {
		this.resultadoCoseno = resultadoCoseno;
	}
	public String getResultadoSeno() {
		return resultadoSeno;
	}
	public void setResultadoSeno(String resultadoSeno) {
		this.resultadoSeno = resultadoSeno;
	}
	public Calculadora getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(Calculadora domainModel) {
		this.domainModel = domainModel;
	}
	
	
	
}
