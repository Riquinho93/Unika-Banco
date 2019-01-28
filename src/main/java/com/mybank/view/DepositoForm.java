package com.mybank.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mybank.HomePage;
import com.mybank.model.Deposito;

public class DepositoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Deposito> formFiltrar;
	private List<Deposito> listaDepositos = new LinkedList<>();

	public DepositoForm() {
		Deposito deposito = new Deposito();
		Form<Deposito> form = new Form<Deposito>("form", new CompoundPropertyModel<Deposito>(deposito));

		NumberTextField<Integer> numeroConta = new NumberTextField<>("numeroConta");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				target.add(numeroConta, valor);
			}
		};

		button.setOutputMarkupId(true);
		form.add(numeroConta, valor);
		form.add(button);
		add(form);


	}

	}
