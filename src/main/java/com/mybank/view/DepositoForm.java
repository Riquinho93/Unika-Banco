package com.mybank.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.mybank.HomePage;
import com.mybank.model.Conta;
import com.mybank.model.Deposito;
import com.mybank.service.ContaService;

public class DepositoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private List<Deposito> listaDepositos = new LinkedList<>();
	private Deposito deposito;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	public DepositoForm() {
		deposito = new Deposito();
		Form<Deposito> form = new Form<Deposito>("form", new CompoundPropertyModel<Deposito>(deposito));

		TextField<Integer> numeroConta = new TextField<>("numeroConta");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				if (numeroConta.getModelObject() > 999) {
					if (valor.getModelObject() > 10) {
						int numConta = numeroConta.getConvertedInput();
						Double valorConta = valor.getConvertedInput();
						List<Conta> lista = new ArrayList<>();
						lista = contaService.depositar(numConta, valorConta);
						if (lista != null && !lista.isEmpty()) {
							target.appendJavaScript("sucessDeposito();");
						} else {
							target.appendJavaScript("contaInexistente();");
						}
					}else {
						target.appendJavaScript("valorInvalido();");
					}
				} else {
					target.appendJavaScript("contaInvalida();");
				}
				target.add(numeroConta, valor);
				deposito = new Deposito();
				form.clearInput();
				form.modelChanged();
				form.setDefaultModelObject(deposito);
				target.add(form);
			}

		};

		button.setOutputMarkupId(true);
		form.add(numeroConta, valor);
		form.add(button);
		add(form);

	}

}
