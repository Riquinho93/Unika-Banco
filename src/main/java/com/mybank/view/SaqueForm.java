package com.mybank.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.HomePage;
import com.mybank.model.Conta;
import com.mybank.model.Saque;
import com.mybank.service.ContaService;

public class SaqueForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Saque saque = new Saque();

	@SpringBean(name = "contaService")
	private ContaService contaService;

	public SaqueForm() {
		this(new Conta());
	}

	public SaqueForm(Conta conta) {
		saque = new Saque();
		Form<Saque> form = new Form<Saque>("form", new CompoundPropertyModel<Saque>(saque));

		PasswordTextField senha = new PasswordTextField("senha");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		senha.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				if(valor.getModelObject() > 10) {
					if(senha.getModelObject().equals(conta.getSenha())) {
					double valorConta = valor.getConvertedInput();
					String senhaConta = senha.getConvertedInput();
					contaService.saque(conta, valorConta, senhaConta);
					target.appendJavaScript("sucessSaque();");
					target.add(senha, valor);
					saque = new Saque();
					}else {
						target.appendJavaScript("senhaIncorreta();");
					}
				}else {
					target.appendJavaScript("valorInvalido();");
				}
				form.clearInput();
				form.modelChanged();
				form.setDefaultModelObject(saque);
				target.add(form);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(senha, valor);
		form.add(ajaxButton);
		add(form);

	}

}
