package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

public class TransacaoSucesso extends Panel{
	
	private static final long serialVersionUID = 1L;

	public TransacaoSucesso(String id) {
		super(id);
		
		add(telaPrincipal());

	}
	
	AjaxLink<TelaPrincipal> telaPrincipal(){
		AjaxLink<TelaPrincipal> ajaxLink = new AjaxLink<TelaPrincipal>("telaPrincipal") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(TelaPrincipal.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

}
