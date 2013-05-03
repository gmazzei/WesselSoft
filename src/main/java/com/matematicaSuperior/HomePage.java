package com.matematicaSuperior;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public class HomePage extends WebPage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Form form;
	public Panel panel = new PanelSuma("panel");;
	
	public HomePage() {
		super();
		
		form = new Form("form");
		panel = new PanelSuma("panel");
		
		Label mensajeInicial = new Label("mensajeInicial", "Seleccione la operacion");
		
		AjaxButton botonSumar = new AjaxButton("botonSumar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelSuma("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonRestar = new AjaxButton("botonRestar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelResta("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonMultiplicar = new AjaxButton("botonMultiplicar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelMultiplicacion("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonDivision = new AjaxButton("botonDivision") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelDivision("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonPotencia = new AjaxButton("botonPotencia") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelPotencia("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonRadicacion = new AjaxButton("botonRadicacion") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelRadicacion("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonPrimitivas = new AjaxButton("botonPrimitivas") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelPrimitivas("panel"));
				target.add(HomePage.this);
			}
		};
		
		AjaxButton botonSumaSinusoidales = new AjaxButton("botonSumaSinusoidales") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				HomePage.this.remove("panel");
				HomePage.this.add(new PanelSumaSinusoidales("panel"));
				target.add(HomePage.this);
			}
		};
		
		form.add(mensajeInicial);
		form.add(botonSumar);
		form.add(botonRestar);
		form.add(botonMultiplicar);
		form.add(botonDivision);
		form.add(botonPotencia);
		form.add(botonRadicacion);
		form.add(botonPrimitivas);
		form.add(botonSumaSinusoidales);
		
		add(panel);
		add(form);
    }
}
