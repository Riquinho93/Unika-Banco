package com.mybank;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.mybank.model.Conta;
import com.mybank.model.Contato;
import com.mybank.model.Funcionario;
import com.mybank.view.BancoForm;
import com.mybank.view.FuncionarioForm;
import com.mybank.view.ContaForm;
import com.mybank.view.ContatoForm;
import com.mybank.view.UsuarioForm;
import com.mybank.view.DepositoForm;
import com.mybank.view.Login;
import com.mybank.view.SaqueForm;
import com.mybank.view.TelaPrincipal;
import com.mybank.view.TransferenciaForm;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		Conta userName = (Conta) getSession().getAttribute("userName");
		if (userName == null) {
			setResponsePage(Login.class);
			return;
		}
		
		add(usuarioForm());
		add(telaPrincipal());
		add(bancoForm());
		add(contaForm());
		add(clienteForm());
		add(depositoForm());
		add(saqueForm(userName));
		add(transferenciaForm(userName));
		add(contatoForm(userName));
		add(new Link<Void>("sair") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(TelaPrincipal.class);
			}
		});

	}

	// Metodo de chamada de Criar Conta
	AjaxLink<Funcionario> usuarioForm() {
		AjaxLink<Funcionario> ajaxLink = new AjaxLink<Funcionario>("usuarioForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(UsuarioForm.class);
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

	private AjaxLink<ContaForm> contaForm() {
		AjaxLink<ContaForm> ajaxLink = new AjaxLink<ContaForm>("contaForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ContaForm.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

	private AjaxLink<FuncionarioForm> clienteForm() {
		AjaxLink<FuncionarioForm> ajaxLink = new AjaxLink<FuncionarioForm>("clienteForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(FuncionarioForm.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

	private AjaxLink<DepositoForm> depositoForm() {
		AjaxLink<DepositoForm> ajaxLink = new AjaxLink<DepositoForm>("depositoForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(DepositoForm.class);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

	private AjaxLink<SaqueForm> saqueForm(Conta conta) {
		AjaxLink<SaqueForm> ajaxLink = new AjaxLink<SaqueForm>("saqueForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new SaqueForm(conta));
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

	private AjaxLink<TransferenciaForm> transferenciaForm(Conta conta) {
		AjaxLink<TransferenciaForm> ajaxLink = new AjaxLink<TransferenciaForm>("transferenciaForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new TransferenciaForm(conta));
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

	private AjaxLink<Contato> contatoForm(Conta conta) {
		AjaxLink<Contato> ajaxLink = new AjaxLink<Contato>("contatoForm") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new ContatoForm(conta));
			}
		};
		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}
}
