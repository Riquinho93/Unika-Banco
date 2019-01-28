package com.mybank.view;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.HomePage;
import com.mybank.model.Conta;
import com.mybank.model.Usuario;
import com.mybank.service.ContaService;
import com.mybank.service.UsuarioService;

public class TelaPrincipal extends HomePage {

	private static final long serialVersionUID = 1L;

	@SpringBean(name = "contaService")
	private ContaService contaService;

	public TelaPrincipal() {
		this(new Conta());
	}

	public TelaPrincipal(Conta conta) {
		/* add(new Label("usuario", conta.getUsuario().getNome())); */
		add(new Label("numeroConta", conta.getNumeroConta()));
		/* add(new Label("banco", conta.getBanco().getNome())); */
		add(new Label("saldo", conta.getSaldo()));

	}

}
