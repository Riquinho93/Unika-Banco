package com.mybank.view;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import com.mybank.model.Funcao;
import com.mybank.model.Funcionario;
import com.mybank.model.Situacao;

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
		NumberTextField<Integer> identidade = new NumberTextField<>("identidade");
		NumberTextField<Integer> cpf = new NumberTextField<>("cpf");
		PasswordTextField senha = new PasswordTextField("senha");
		PasswordTextField confirmarSenha = new PasswordTextField("confirmarSenha");

		nome.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		identidade.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		confirmarSenha.setOutputMarkupId(true);
		
		RadioGroup<Boolean> radioGroupAtivo = new RadioGroup<Boolean>("sexo");
		radioGroupAtivo.setRequired(true);
		radioGroupAtivo
				.add(new Radio<Boolean>("sim", new Model<Boolean>(true)).add(new AttributeModifier("id", "sim")));
		radioGroupAtivo
				.add(new Radio<Boolean>("nao", new Model<Boolean>(false)).add(new AttributeModifier("id", "nao")));
		form.add(radioGroupAtivo);
		
		//Funcao
		ChoiceRenderer<Funcao> renderer = new ChoiceRenderer<Funcao>("descricao");
		  IModel<List<Funcao>> model = new LoadableDetachableModel<List<Funcao>>() {
		  
		  private static final long serialVersionUID = 1L;
		  
		  @Override protected List<Funcao> load() { return Funcao.funcoes(); } };
		  
		  DropDownChoice<Funcao> funcoes = new DropDownChoice<>("funcao", model,
		  renderer);
		  
		  //Situacao
		  ChoiceRenderer<Situacao> renderer2 = new ChoiceRenderer<Situacao>("descricao");
		  IModel<List<Situacao>> model2 = new LoadableDetachableModel<List<Situacao>>() {
		  
		  private static final long serialVersionUID = 1L;
		  
		  @Override protected List<Situacao> load() { return Situacao.situacao(); } };
		  
		  DropDownChoice<Situacao> situacoes = new DropDownChoice<>("situacao", model2,
		  renderer2);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, cliente);
				target.add(nome, telefone, cpf, identidade, senha, confirmarSenha);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(ajaxButton, funcoes, situacoes);
		form.add(nome, telefone, cpf, identidade, senha, confirmarSenha);
		add(form);
	}

	public void executarAoSalvar(AjaxRequestTarget target, Funcionario cliente) {

	}

}
