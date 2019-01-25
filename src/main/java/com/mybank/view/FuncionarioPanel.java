package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mybank.model.Funcionario;

public class FuncionarioPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public FuncionarioPanel(String id) {
		this(id, new Funcionario());
	}

	public FuncionarioPanel(String id, Funcionario cliente) {
		super(id);

		Form<Funcionario> form = new Form<Funcionario>("form", new CompoundPropertyModel<Funcionario>(cliente));

		TextField<String> nome = new TextField<>("nome");
		TextField<String> telefone = new TextField<>("telefone");
		NumberTextField<Integer> cpf = new NumberTextField<>("cpf");

		nome.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, cliente);
				target.add(nome, telefone, cpf);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(ajaxButton);
		form.add(nome, telefone, cpf);
		add(form);
	}

	public void executarAoSalvar(AjaxRequestTarget target, Funcionario cliente) {

	}

}
