package com.mybank;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mybank.model.Endereco;
import com.mybank.model.Usuario;
import com.mybank.view.Login;
import com.mybank.view.TelaPrincipal;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage() {
		Endereco userName = (Endereco) getSession().getAttribute("userName");
		if (userName == null) {
			 setResponsePage(Login.class);
			 return;
		}
//		UsuarioForm usuarioForm = new UsuarioForm();
//		usuarioForm.setEnabled(false);
		
		add(new Link<Void>("sair"){
			
			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setResponsePage(TelaPrincipal.class);
			}
		});

    }
}
