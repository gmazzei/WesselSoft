package com.matematicaSuperior.calculadora;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.FuncionSinusoidal;
import domain.NumeroComplejo;
import domain.Primitiva;


public class Calculadora implements Serializable {

	private static final long serialVersionUID = 1L;

	public NumeroComplejo sumar(NumeroComplejo numeroA, NumeroComplejo numeroB) {
		NumeroComplejo resultado = new NumeroComplejo();
		resultado.setReal(numeroA.getReal() + numeroB.getReal());
		resultado.setImaginario(numeroA.getImaginario() + numeroB.getImaginario());
		return resultado;
	}
	
	public NumeroComplejo restar(NumeroComplejo numeroA, NumeroComplejo numeroB) {
		NumeroComplejo resultado = new NumeroComplejo();
		resultado.setReal(numeroA.getReal() - numeroB.getReal());
		resultado.setImaginario(numeroA.getImaginario() - numeroB.getImaginario());
		return resultado;
	}
	
	public NumeroComplejo multiplicar(NumeroComplejo numeroA, NumeroComplejo numeroB) {
		NumeroComplejo resultado = new NumeroComplejo();
		resultado.setModulo(numeroA.getModulo()*numeroB.getModulo());
		resultado.setArgumento(numeroA.getArgumento()+numeroB.getArgumento());
		return resultado;
	}
	
	public NumeroComplejo dividir(NumeroComplejo numeroA, NumeroComplejo numeroB) {
		double denominador = Math.pow(numeroB.getReal(), 2) + Math.pow(numeroB.getImaginario(), 2);
		double parteReal = (numeroA.getReal()*numeroB.getReal() + numeroA.getImaginario()*numeroB.getImaginario()) / denominador;
		double parteImaginaria = (numeroA.getImaginario()*numeroB.getReal() - numeroA.getReal()*numeroB.getImaginario()) / denominador;
		
		NumeroComplejo resultado = new NumeroComplejo();
		resultado.setReal(parteReal);
		resultado.setImaginario(parteImaginaria);
		return resultado;
	}
	
	public NumeroComplejo potencia(NumeroComplejo base, Integer exponente) {
		NumeroComplejo resultado = new NumeroComplejo();
		resultado.setModulo(Math.pow(base.getModulo(), exponente));
		resultado.setArgumento(base.getArgumento() * exponente);
		return resultado;
	}
	
	public List<NumeroComplejo> radicacion(NumeroComplejo numero, Integer orden) {
		List<NumeroComplejo> listaResultados = new ArrayList<NumeroComplejo>();
		
		for (int k = 0; k < orden; k++) {
			NumeroComplejo resultado = new NumeroComplejo();
			resultado.setModulo(Math.pow(numero.getModulo(), (1/(double)orden)));
			resultado.setArgumento((numero.getArgumento() + 2*k*Math.PI)/orden);
			
			listaResultados.add(resultado);
		}
		
		return listaResultados;
	}
	

	public List<Primitiva> primitivas(Integer orden) {
		List<Primitiva> listaResultado = new ArrayList<Primitiva>();
		Primitiva primitiva;
		NumeroComplejo resultado;
		Integer maximoComunDivisor;
		
		for (int i = 0; i < orden; i++) {
			maximoComunDivisor = this.obtenerMCD(i,orden);

			if (maximoComunDivisor == 1) {
				resultado = new NumeroComplejo();
				resultado.setModulo(1);
				resultado.setArgumento(2*i*Math.PI/orden);
				
				primitiva = new Primitiva(i, resultado);
				listaResultado.add(primitiva);
			}
			
		}
		
		return listaResultado;
	}

	
	
	private Integer obtenerMCD(Integer numeroA, Integer numeroB) {
		//Algoritmo de Euclides
		
		Integer numeroBAnterior = numeroB;
		while (numeroB != 0) {
			numeroB = numeroA % numeroB;
			numeroA = numeroBAnterior;
			numeroBAnterior = numeroB;
		}
		
		return numeroA;
	}
	
	//Para funciones con la misma frecuencia
	public FuncionSinusoidal sumarSinusoidales(FuncionSinusoidal funcionA, FuncionSinusoidal funcionB) {
		NumeroComplejo numeroA = new NumeroComplejo();
		NumeroComplejo numeroB = new NumeroComplejo();
		
		numeroA.setModulo(funcionA.getAmplitud());
		numeroA.setArgumento(funcionA.getFase());
		numeroB.setModulo(funcionB.getAmplitud());
		numeroB.setArgumento(funcionB.getFase());
		
		NumeroComplejo complejoResult = this.sumar(numeroA, numeroB);
		
		FuncionSinusoidal resultado = new FuncionSinusoidal("cos", complejoResult.getModulo(), funcionA.getFrecuencia(), complejoResult.getArgumento());
		
		return resultado;
	}
	
}
