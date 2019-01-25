package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mybank.model.Conta;

public class ContaPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ContaPanel(String id) {
		this(id, new Conta());
	}

	public ContaPanel(String id, Conta conta) {
		super(id);

		Form<Conta> form = new Form<Conta>("form", new CompoundPropertyModel<Conta>(conta));

		NumberTextField<Integer> numeroConta = new NumberTextField<>("numeroConta");
		NumberTextField<Double> limite = new NumberTextField<>("limite");

		numeroConta.setOutputMarkupId(true);
		limite.setOutputMarkupId(true).setVisible(false);
		

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, conta);
				target.add(numeroConta);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(ajaxButton);
		form.add(numeroConta);
		add(form);
	}

	public void executarAoSalvar(AjaxRequestTarget target, Conta conta) {

	}

}
