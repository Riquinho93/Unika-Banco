package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mybank.HomePage;
import com.mybank.model.Saque;

public class SaqueForm extends HomePage {

	private static final long serialVersionUID = 1L;

	public SaqueForm() {
		Saque saque = new Saque();
		Form<Saque> form = new Form<Saque>("form", new CompoundPropertyModel<Saque>(saque));

		NumberTextField<Integer> numeroConta = new NumberTextField<>("numeroConta");
		PasswordTextField senha = new PasswordTextField("senha");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				target.add(numeroConta, senha, valor);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(numeroConta, senha, valor);
		form.add(ajaxButton);
		add(form);

	}

}
