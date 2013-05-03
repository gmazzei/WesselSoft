package com.matematicaSuperior;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.matematicaSuperior.calculadora.CalculadoraSinusoidalesViewModel;

public class PanelSumaSinusoidales extends Panel {

	private static final long serialVersionUID = 1L;

	public Form<CalculadoraSinusoidalesViewModel> form;
	public IModel<CalculadoraSinusoidalesViewModel> calculadoraModel = new CompoundPropertyModel<CalculadoraSinusoidalesViewModel>(new CalculadoraSinusoidalesViewModel());

	
	public PanelSumaSinusoidales(String id) {
		super(id);
		
		form = new Form<CalculadoraSinusoidalesViewModel>("formOperacion");
		form.setDefaultModel(this.calculadoraModel);
		form.getModelObject().setOperacion("sumarSinusoidales");
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
		feedbackPanel.setOutputMarkupId(true);

		List<String> opciones = new ArrayList<String>();
		opciones.add("cos");
		opciones.add("sen");
		
		DropDownChoice<String> tipoA = new DropDownChoice<String>("tipoA", opciones);
		TextField<Double> amplitudA = new TextField<Double>("amplitudA");
		TextField<Double> frecuenciaA = new TextField<Double>("frecuenciaA");
		TextField<Double> faseA = new TextField<Double>("faseA");
		
		DropDownChoice<String> tipoB = new DropDownChoice<String>("tipoB", opciones);
		TextField<Double> amplitudB = new TextField<Double>("amplitudB");
		TextField<Double> frecuenciaB = new TextField<Double>("frecuenciaB");
		TextField<Double> faseB = new TextField<Double>("faseB");
		
		
		Label resultadoCoseno = new Label("resultadoCoseno");
		resultadoCoseno.setOutputMarkupId(true);
		
		Label resultadoSeno = new Label("resultadoSeno");
		resultadoSeno.setOutputMarkupId(true);
		
		AjaxButton ajaxButton = new AjaxButton("submit") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				try {
					CalculadoraSinusoidalesViewModel modelObject = (CalculadoraSinusoidalesViewModel) form.getModelObject();
					modelObject.ejecutar();					
				} catch (Exception e) {
					int pos = e.getMessage().indexOf(":");
					if (pos != -1) {
						String mensaje = e.getMessage().substring(pos+1);
						error(mensaje);
					} else {
						error(e.getMessage());						
					}
				}

				target.add(form);					
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(form);
			}
			
		};
		
		
		form.add(feedbackPanel);
		form.add(tipoA);
		form.add(tipoB);
		form.add(amplitudA);
		form.add(amplitudB);
		form.add(frecuenciaA);
		form.add(frecuenciaB);
		form.add(faseA);
		form.add(faseB);
		form.add(resultadoCoseno);
		form.add(resultadoSeno);
		form.add(ajaxButton);
		
		add(form);
		
	}
	
}
