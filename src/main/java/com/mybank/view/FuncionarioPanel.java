package com.mybank.view;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.mybank.model.Endereco;
import com.mybank.model.Funcao;
import com.mybank.model.Funcionario;


public class FuncionarioPanel extends Panel {

	private static final long serialVersionUID = 8991195474675368668L;

	private Form<Funcionario> formFunc;
	private Form<Endereco> formEnd;
//	private Funcionario funcionario;
//	private Endereco endereco;

	public FuncionarioPanel(String id) {
		this(id, new Funcionario(), new Endereco());
	}

	public FuncionarioPanel(String id, Funcionario funcionario, Endereco endereco) {
		super(id);
		formFunc = new Form<Funcionario>("formFunc", new CompoundPropertyModel<Funcionario>(funcionario));
//		formEnd = new Form<Endereco>("formEnd", new CompoundPropertyModel<Endereco>(funcionario.getEndereco()));

		TextField<String> nome = new TextField<>("nome");
		TextField<String> telefone = new TextField<>("telefone");
		TextField<String> email = new TextField<>("email");

		TextField<String> cep = new TextField<String>("endereco.cep");
		TextField<String> logradouro = new TextField<>("endereco.logradouro");
		NumberTextField<Integer> numero = new NumberTextField<>("endereco.numero");
		TextField<String> bairro = new TextField<>("endereco.bairro");
		TextField<String> cidade = new TextField<>("endereco.cidade");
		TextField<String> estado = new TextField<>("endereco.estado");
		
		nome.setRequired(true);
		telefone.setRequired(true);
		logradouro.setRequired(true);
		bairro.setRequired(true);
		cidade.setRequired(true);
		estado.setRequired(true);
		

		nome.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		email.setOutputMarkupId(true);
		cep.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		bairro.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);
		estado.setOutputMarkupId(true);

		ChoiceRenderer<Funcao> renderer = new ChoiceRenderer<Funcao>("descricao");
		IModel<List<Funcao>> model = new LoadableDetachableModel<List<Funcao>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Funcao> load() {
				return Funcao.funcoes();
			}
		};

		DropDownChoice<Funcao> funcoes = new DropDownChoice<>("funcao", model, renderer);

		// funcionario.setEndereco(endereco);
		AjaxButton button = new AjaxButton("submit") {

			private static final long serialVersionUID = 994698440577863113L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, funcionario);
				target.add(nome);
				target.add(telefone);
				target.add(email);
				target.add(cep);
				target.add(logradouro);
				target.add(numero);
				target.add(bairro);
				target.add(cidade);
				target.add(estado);

			}
		};
		button.setOutputMarkupId(true);
	
//		add(formEnd);
		formFunc.add(nome);
		formFunc.add(telefone);
		formFunc.add(email);
		formFunc.add(funcoes);
		
		formFunc.add(cep);
		formFunc.add(logradouro);
		formFunc.add(numero);
		formFunc.add(bairro);
		formFunc.add(cidade);
		formFunc.add(estado);
//		formFunc.add(formEnd);
		formFunc.add(button);
		add(formFunc);
		

	}

	// Enviando os dados para o HomePage
	public void executarAoSalvar(AjaxRequestTarget target, Funcionario funcionario) {

	}

}
