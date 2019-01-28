package com.mybank.view;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.mybank.model.Usuario;
import com.mybank.service.UsuarioService;

public class SolicitarAberturaConta extends WebPage {

	private static final long serialVersionUID = 1L;

	@SpringBean(name = "usuarioService")
	private UsuarioService usuarioService;

	public SolicitarAberturaConta() {

		add(voltar());

		Usuario usuario = new Usuario();
		Form<Usuario> form = new Form<Usuario>("form", new CompoundPropertyModel<Usuario>(usuario));
		TextField<String> nome = new TextField<>("nome");
		NumberTextField<Integer> identidade = new NumberTextField<>("identidade");
		NumberTextField<Integer> cpf = new NumberTextField<>("cpf");
		NumberTextField<Double> renda = new NumberTextField<>("renda");
		TextField<String> telefone = new TextField<>("telefone");
		TextField<String> email = new TextField<>("email");
		TextField<String> nomeBanco = new TextField<>("nomeBanco");
		TextField<String> logradouro = new TextField<>("endereco.logradouro");
		TextField<String> bairro = new TextField<>("endereco.bairro");
		TextField<String> cidade = new TextField<>("endereco.cidade");
		TextField<String> estado = new TextField<>("endereco.estado");
		NumberTextField<Integer> numero = new NumberTextField<>("endereco.numero");
		TextField<String> cep = new TextField<>("endereco.cep");

		nome.setOutputMarkupId(true);
		identidade.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);
		renda.setOutputMarkupId(true);
		telefone.setOutputMarkupId(true);
		email.setOutputMarkupId(true);
		nomeBanco.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		bairro.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);
		estado.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		cep.setOutputMarkupId(true);

		form.add(nome, identidade, cpf, renda, telefone, email, nomeBanco, logradouro, bairro,
				cidade, estado, numero, cep);

		RadioGroup<Boolean> radioGroupAtivo = new RadioGroup<Boolean>("sexo");
		radioGroupAtivo.setRequired(true);
		radioGroupAtivo
				.add(new Radio<Boolean>("sim", new Model<Boolean>(true)).add(new AttributeModifier("id", "sim")));
		radioGroupAtivo
				.add(new Radio<Boolean>("nao", new Model<Boolean>(false)).add(new AttributeModifier("id", "nao")));
		form.add(radioGroupAtivo);

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
		form.add(data);

		AjaxButton button = new AjaxButton("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Usuario user = (Usuario) form.getModelObject();
				usuarioService.SalvarOuAlterar(user);
				target.add(nome, identidade, cpf, renda, telefone, email, cep, logradouro,
						numero, bairro, cidade, estado);

				setResponsePage(Login.class);
			}

		};

		button.setOutputMarkupId(true);
		form.add(button);
		add(form);
	}

	private AjaxLink<Login> voltar() {
		AjaxLink<Login> ajaxLink = new AjaxLink<Login>("voltar") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(Login.class);
			}
		};

		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		return ajaxLink;
	}

}
