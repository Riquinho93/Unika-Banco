package com.mybank.view;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.mybank.model.Endereco;
import com.mybank.model.Usuario;

public class UsuarioPanel extends Panel {

	private static final long serialVersionUID = 8991195474675368668L;

	private Form<Usuario> formFunc;

	public UsuarioPanel(String id) {
		this(id, new Usuario());
	}

	public UsuarioPanel(String id, Usuario usuario) {
		super(id);
		formFunc = new Form<Usuario>("formFunc", new CompoundPropertyModel<Usuario>(usuario));

		TextField<String> nome = new TextField<>("nome");
		NumberTextField<Integer> identidade = new NumberTextField<>("identidade");
		NumberTextField<Integer> cpf = new NumberTextField<>("cpf");
		NumberTextField<Double> renda = new NumberTextField<>("renda");
		TextField<String> telefone = new TextField<>("telefone");
		TextField<String> email = new TextField<>("email");

		TextField<String> cep = new TextField<String>("endereco.cep");
		TextField<String> logradouro = new TextField<>("endereco.logradouro");
		NumberTextField<Integer> numero = new NumberTextField<>("endereco.numero");
		TextField<String> bairro = new TextField<>("endereco.bairro");
		TextField<String> cidade = new TextField<>("endereco.cidade");
		TextField<String> estado = new TextField<>("endereco.estado");

		/*
		 * nome.setRequired(true); identidade.setRequired(true); cpf.setRequired(true);
		 * telefone.setRequired(true); logradouro.setRequired(true);
		 * bairro.setRequired(true); cidade.setRequired(true); estado.setRequired(true);
		 */

		nome.setOutputMarkupId(true);
		identidade.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		renda.setOutputMarkupId(true);
		email.setOutputMarkupId(true);
		cep.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		bairro.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);
		estado.setOutputMarkupId(true);

		RadioGroup<Boolean> radioGroupAtivo = new RadioGroup<Boolean>("sexo");
		radioGroupAtivo.setRequired(true);
		radioGroupAtivo
				.add(new Radio<Boolean>("sim", new Model<Boolean>(true)).add(new AttributeModifier("id", "sim")));
		radioGroupAtivo
				.add(new Radio<Boolean>("nao", new Model<Boolean>(false)).add(new AttributeModifier("id", "nao")));
		formFunc.add(radioGroupAtivo);

		// Data de Nascimento
		DatePicker datePickerInicial = new DatePicker() {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean alignWithIcon() {
				return true;
			}

			@Override
			protected boolean enableMonthYearSelection() {
				return false;
			}
		};

		DateTextField data = new DateTextField("dataNascimento", "dd/MM/yyyy");
		datePickerInicial.setAutoHide(true);
		data.add(datePickerInicial);
		data.setOutputMarkupId(true);
		formFunc.add(data);

		/*
		 * ChoiceRenderer<Funcao> renderer = new ChoiceRenderer<Funcao>("descricao");
		 * IModel<List<Funcao>> model = new LoadableDetachableModel<List<Funcao>>() {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * @Override protected List<Funcao> load() { return Funcao.funcoes(); } };
		 * 
		 * DropDownChoice<Funcao> funcoes = new DropDownChoice<>("funcao", model,
		 * renderer);
		 */
		// funcionario.setEndereco(endereco);
		AjaxButton button = new AjaxButton("submit") {

			private static final long serialVersionUID = 994698440577863113L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				executarAoSalvar(target, usuario);
				target.add(nome);
				target.add(identidade);
				target.add(cpf);
				target.add(telefone);
				target.add(email);
				target.add(cep);
				target.add(logradouro);
				target.add(numero);
				target.add(bairro);
				target.add(cidade);
				target.add(estado);
				target.add(renda);

			}
		};
		button.setOutputMarkupId(true);

		formFunc.add(nome, identidade, cpf, renda, telefone, email, cep, logradouro, numero, bairro, cidade, estado);
		formFunc.add(button);
		add(formFunc);

	}

	// Enviando os dados para o HomePage
	public void executarAoSalvar(AjaxRequestTarget target, Usuario usuario) {

	}

}
