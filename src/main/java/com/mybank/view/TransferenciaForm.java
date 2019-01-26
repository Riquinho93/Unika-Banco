package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mybank.HomePage;
import com.mybank.model.Transferencia;

public class TransferenciaForm extends HomePage {

	private static final long serialVersionUID = 1L;

	public TransferenciaForm() {
		
		Transferencia transferencia = new Transferencia();
		Form<Transferencia> form = new Form<Transferencia>("form", new CompoundPropertyModel<Transferencia>(transferencia));

		NumberTextField<Integer> numeroConta = new NumberTextField<>("numeroConta");
		NumberTextField<Integer> cpf =new NumberTextField<>("cpf");
		PasswordTextField senha = new PasswordTextField("senha");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				target.add(numeroConta, cpf,senha, valor);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(numeroConta, cpf,senha, valor);
		form.add(ajaxButton);
		add(form);



	}

}
