package com.mybank.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Conta;
import com.mybank.service.AlertFeedback;
import com.mybank.service.ContaService;

public class Login extends WebPage {

	private static final long serialVersionUID = -2850628051987758424L;

	private Form<Conta> formularioLogin;
	private Conta filtrarUsuario;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	public Login() {

		AlertFeedback alertFeedback = new AlertFeedback("feedbackMessage");

		add(aberturaConta());

		filtrarUsuario = new Conta();
		final NumberTextField<Integer> numeroConta = new NumberTextField<Integer>("numeroConta");
		final PasswordTextField senha = new PasswordTextField("senha");
		numeroConta.setRequired(true);
		senha.setRequired(true);
		numeroConta.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);

		final Label errorLogin = new Label("errorLogin", Model.of("Login Incorreto!!"));
		errorLogin.setOutputMarkupId(true).setVisible(false);

		formularioLogin = new Form<Conta>("formularioLogin", new CompoundPropertyModel<>(filtrarUsuario)) {

			private static final long serialVersionUID = -5095534494215850537L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				Search search = new Search(Conta.class);

				search.addFilterEqual("numeroConta", numeroConta.getModelObject());
				search.addFilterEqual("senha", senha.getModelObject());

				List<Conta> lista = contaService.search(search);

				if (lista != null && !lista.isEmpty()) {
					Conta conta = lista.get(0);
					alertFeedback.success("Login com sucesso!!");
					getSession().setAttribute("userName", lista.get(0));
					setResponsePage(new TelaPrincipal(conta));
				} else {

					alertFeedback.error("Login Incorreto");
					;
					errorLogin.setVisible(true);
				}

			}

		};
		add(alertFeedback, formularioLogin);
		formularioLogin.add(numeroConta, senha).setOutputMarkupId(true);
	}

	private AjaxLink<SolicitarAberturaConta> aberturaConta() {
		AjaxLink<SolicitarAberturaConta> ajaxLink = new AjaxLink<SolicitarAberturaConta>("aberturaConta") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(SolicitarAberturaConta.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}
}
