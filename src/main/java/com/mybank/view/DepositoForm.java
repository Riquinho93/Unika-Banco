package com.mybank.view;

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
import com.mybank.model.Deposito;
import com.mybank.service.ContaService;

public class DepositoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Deposito> formFiltrar;
	private List<Deposito> listaDepositos = new LinkedList<>();
	private ModalWindow modalWindowSucesso;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	public DepositoForm() {
		Deposito deposito = new Deposito();
		Form<Deposito> form = new Form<Deposito>("form", new CompoundPropertyModel<Deposito>(deposito));
		
		modalWindowSucesso = new ModalWindow("modalWindowSucesso");
		//Tamanho
		modalWindowSucesso.setInitialHeight(200);
		modalWindowSucesso.setInitialWidth(600);
		modalWindowSucesso.setOutputMarkupId(true);
		add(modalWindowSucesso);
		
		TextField<Integer> numeroConta = new TextField<>("numeroConta");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				int numConta = numeroConta.getConvertedInput();
				Double valorConta = valor.getConvertedInput();
				contaService.depositar(numConta, valorConta);
				TransacaoSucesso transacaoSucesso = new TransacaoSucesso(modalWindowSucesso.getContentId()) {
				};
				target.add(numeroConta, valor);
				transacaoSucesso.setOutputMarkupId(true);
				add(transacaoSucesso);
				modalWindowSucesso.setContent(transacaoSucesso);
				modalWindowSucesso.show(target);
			}
			
		};
		
		button.setOutputMarkupId(true);
		form.add(numeroConta, valor);
		form.add(button);
		add(form);


	}

	}
