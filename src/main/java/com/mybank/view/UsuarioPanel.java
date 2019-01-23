package com.mybank.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.mybank.model.Perfil;
import com.mybank.model.Usuario;


public class UsuarioPanel extends Panel{

	private static final long serialVersionUID = -6534497016691513947L;
	
	private Form<Usuario> formUsuario;
	
	public UsuarioPanel(String id) {
		this(id, new Usuario());
	}

	public UsuarioPanel(String id, Usuario usuario) {
		super(id);
		
		formUsuario = new Form<>("formUsuario", new CompoundPropertyModel<>(usuario));
		
		TextField<String> nome = new TextField<>("nome");
		TextField<String> login = new TextField<>("login");
		PasswordTextField senha = new PasswordTextField("senha");
		PasswordTextField confirmarSenha = new PasswordTextField("confirmarSenha");
		
		nome.setRequired(true);
		login.setRequired(true);
		senha.setRequired(true);
		confirmarSenha.setRequired(true);
		
		nome.setOutputMarkupId(true);
		login.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		confirmarSenha.setOutputMarkupId(true);
		
		ChoiceRenderer<Perfil> renderer = new ChoiceRenderer<>("descricao");
		IModel<List<Perfil>> model = new LoadableDetachableModel<List<Perfil>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Perfil> load() {
				return Perfil.perfil();
			}
		};
		
		DropDownChoice<Perfil> perfis = new DropDownChoice<>("perfil", model, renderer);
		
		AjaxButton button = new AjaxButton("submit", formUsuario) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				
				executarAoSalvar(target, usuario);
				target.add(nome);
				target.add(login);
				target.add(senha);
				target.add(confirmarSenha);
			}
		};
		button.setOutputMarkupId(true);
		
		add(formUsuario);
		formUsuario.add(nome);
		formUsuario.add(login);
		formUsuario.add(senha);
		formUsuario.add(confirmarSenha);
		formUsuario.add(perfis);
		formUsuario.add(button);
	}
	
	public void executarAoSalvar(AjaxRequestTarget target, Usuario usuario) {}

}
