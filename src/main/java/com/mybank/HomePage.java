package com.mybank;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.mybank.model.Endereco;
import com.mybank.model.Usuario;
import com.mybank.view.BancoForm;
import com.mybank.view.CriarConta;
import com.mybank.view.Login;
import com.mybank.view.TelaPrincipal;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		Endereco userName = (Endereco) getSession().getAttribute("userName");
		if (userName == null) {
			setResponsePage(Login.class);
			return;
		}
		// UsuarioForm usuarioForm = new UsuarioForm();
		// usuarioForm.setEnabled(false);

		add(criarConta());
		add(telaPrincipal());
		add(bancoForm());
		add(new Link<Void>("sair") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(TelaPrincipal.class);
			}
		});

	}

	// Metodo de chamada de Criar Conta
	AjaxLink<Usuario> criarConta() {
		AjaxLink<Usuario> ajaxLink = new AjaxLink<Usuario>("criarConta") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(CriarConta.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

	private AjaxLink<TelaPrincipal> telaPrincipal() {
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

	private AjaxLink<BancoForm> bancoForm() {
		AjaxLink<BancoForm> ajaxLink = new AjaxLink<BancoForm>("bancoForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(BancoForm.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}
}
