package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.mybank.model.Contato;

public class DeletContato extends Panel {

	private static final long serialVersionUID = 1L;
	
	private Contato user = new Contato();

	public DeletContato(String id) {
		this(id, new Contato());
	}

	public DeletContato(String id, Contato answer) {
		super(id);
		
		this.user = answer;
		Form<Contato> form = new Form<>("resposta");

		add(new Label("msg", "Deseja realmente excluir este Contato?"));

		// Se a resposta == sim
		AjaxButton yesButton = new AjaxButton("sim") {

			private static final long serialVersionUID = 963978570032062983L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (target != null) {
					user.setAnswer(true);
					executarAoExcluir(target, user);
				}
			}

		};

		// Se resposta == nao
		AjaxButton naoButton = new AjaxButton("nao") {

			private static final long serialVersionUID = -4614172469024292429L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (target != null) {
					user.setAnswer(false);
					executarAoExcluir(target, user);
				}

			}
		};
		add(form);
		yesButton.setOutputMarkupId(true);
		naoButton.setOutputMarkupId(true);

		form.add(yesButton);
		form.add(naoButton);

	}
	
	public void executarAoExcluir(AjaxRequestTarget target, Contato contato) {

	}


}
