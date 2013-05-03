package com.matematicaSuperior;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.matematicaSuperior.calculadora.CalculadoraViewModel;

public abstract class AbstractPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public Form<CalculadoraViewModel> form;
	public IModel<CalculadoraViewModel> calculadoraModel = new CompoundPropertyModel<CalculadoraViewModel>(new CalculadoraViewModel());

	
	public AbstractPanel(String id, String operacion) {
		super(id);
		
		form = new Form<CalculadoraViewModel>("formOperacion", new Model<CalculadoraViewModel>(new CalculadoraViewModel()));
		form.setDefaultModel(this.calculadoraModel);
		
		form.getModelObject().setOperacion(operacion);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
		feedbackPanel.setOutputMarkupId(true);
		
		TextField<String> numeroA = new TextField<String>("numeroA");
		TextField<String> numeroB = new TextField<String>("numeroB");
		
		Label resultado = new Label("resultado");
		resultado.setOutputMarkupId(true);
		
		AjaxButton ajaxButton = new AjaxButton("submit") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				try {
					CalculadoraViewModel modelObject = (CalculadoraViewModel) form.getModelObject();
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
		form.add(numeroA);
		form.add(numeroB);
		form.add(resultado);
		form.add(ajaxButton);
		
		add(form);
		
	}
	
}
