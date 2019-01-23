package com.mybank.view;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Endereco;
import com.mybank.model.Usuario;
import com.mybank.service.AlertFeedback;
import com.mybank.service.EnderecoService;
import com.mybank.service.UsuarioService;

public class Login extends WebPage {

	private static final long serialVersionUID = -2850628051987758424L;

	private Form<Endereco> formularioLogin;
	private Endereco filtrarUsuario;
	@SpringBean(name = "enderecoService")
	private EnderecoService enderecoService;

	public Login() {
		
		AlertFeedback alertFeedback = new AlertFeedback("feedbackMessage");
		
		filtrarUsuario = new Endereco();
		final TextField<String> estado = new TextField<String>("estado");
		final PasswordTextField numero = new PasswordTextField("numero");
		estado.setRequired(true);
		numero.setRequired(true);
		estado.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		
		final Label errorLogin = new Label("errorLogin",
		 Model.of("Login Incorreto!!"));
		 errorLogin.setOutputMarkupId(true).setVisible(false);
		 

		 formularioLogin = new Form<Endereco>("formularioLogin",new  CompoundPropertyModel<>(filtrarUsuario)) {

			private static final long serialVersionUID = -5095534494215850537L;
			@Override
			protected void onSubmit() {
				super.onSubmit();
				Search search = new Search(Endereco.class);

				search.addFilterEqual("estado", estado.getModelObject());
				search.addFilterEqual("numero", numero.getModelObject());

				List<Endereco> lista = enderecoService.search(search);

				if (lista != null && !lista.isEmpty()) {

					alertFeedback.success("Login com sucesso!!");
					getSession().setAttribute("userName", lista.get(0));
					setResponsePage(TelaPrincipal.class);
				}else {
				
					alertFeedback.error("Login Incorreto");;
					errorLogin.setVisible(true);
				}

			}
			

		};
		add(alertFeedback, formularioLogin);
		formularioLogin.add(estado, numero).setOutputMarkupId(true);
	}
}
