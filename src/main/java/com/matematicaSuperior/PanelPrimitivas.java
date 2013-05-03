package com.matematicaSuperior;


import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.matematicaSuperior.calculadora.CalculadoraViewModel;

import domain.Primitiva;

public class PanelPrimitivas extends Panel {

	private static final long serialVersionUID = 1L;

	public Form<CalculadoraViewModel> form;
	public IModel<CalculadoraViewModel> calculadoraModel = new CompoundPropertyModel<CalculadoraViewModel>(new CalculadoraViewModel());
	public IModel<List<Primitiva>> lstPrimitivasModel;
	public WebMarkupContainer container;
	
	public PanelPrimitivas(String id) {
		super(id);
		
		
		this.lstPrimitivasModel = new LoadableDetachableModel<List<Primitiva>>() { 

			private static final long serialVersionUID = 1L;

			@Override
            protected List<Primitiva> load() {
            	return calculadoraModel.getObject().getListaPrimitivas();
            }
        };
		
		this.form = new Form<CalculadoraViewModel>("formOperacion", new Model<CalculadoraViewModel>(new CalculadoraViewModel()));
		this.form.setDefaultModel(this.calculadoraModel);
		this.form.getModelObject().setOperacion("primitivas");

		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
		
		TextField<Integer> orden = new TextField<Integer>("numeroA");
		
		
		PageableListView<Primitiva> primitivasListView = new PageableListView<Primitiva>("listaPrimitivas", this.lstPrimitivasModel, 6) {

			private static final long serialVersionUID = 1L;

			@Override
            protected void populateItem(ListItem<Primitiva> item) {
            	Primitiva primitiva = item.getModel().getObject();   
            	item.add(new Label("indice", new Model<Integer>(primitiva.getIndice())));
                item.add(new Label("numeroBinomica", new Model<String>(primitiva.getNumero().formaBinomica())));
                item.add(new Label("numeroPolar", new Model<String>(primitiva.getNumero().formaPolar())));
            }
        };
        

        this.container = new WebMarkupContainer("container");
        this.container.setOutputMarkupId(true);
        this.container.add(primitivasListView);
        this.container.add(new AjaxPagingNavigator("navigator", primitivasListView));
		
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
				target.add(PanelPrimitivas.this.container);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(form);
				target.add(PanelPrimitivas.this.container);
			}
			
		};
		
		
		form.add(orden);
		form.add(ajaxButton);
		form.add(feedbackPanel);
		
		add(form);
		add(container);
		
	}
	
}
