package com.mybank.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Usuario;
import com.mybank.service.AlertFeedback;
import com.mybank.service.UsuarioService;

public class Login extends WebPage {

	private static final long serialVersionUID = -2850628051987758424L;

	private Form<Usuario> formularioLogin;
	private Usuario filtrarUsuario;
	@SpringBean(name = "usuarioService")
	private UsuarioService usuarioService;

	public Login() {
		
		AlertFeedback alertFeedback = new AlertFeedback("feedbackMessage");
		
		filtrarUsuario = new Usuario();
		final TextField<String> login = new TextField<String>("login");
		final PasswordTextField senha = new PasswordTextField("senha");
		login.setRequired(true);
		senha.setRequired(true);
		login.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		
		final Label errorLogin = new Label("errorLogin",
		 Model.of("Login Incorreto!!"));
		 errorLogin.setOutputMarkupId(true).setVisible(false);
		 

		 formularioLogin = new Form<Usuario>("formularioLogin",new  CompoundPropertyModel<>(filtrarUsuario)) {

			private static final long serialVersionUID = -5095534494215850537L;
			@Override
			protected void onSubmit() {
				super.onSubmit();
				Search search = new Search(Usuario.class);

				search.addFilterEqual("login", login.getModelObject());
				search.addFilterEqual("senha", senha.getModelObject());

				List<Usuario> lista = usuarioService.search(search);

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
		formularioLogin.add(login, senha).setOutputMarkupId(true);
	}
}
