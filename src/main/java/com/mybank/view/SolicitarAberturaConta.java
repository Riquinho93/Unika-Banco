package com.mybank.view;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.mybank.model.Usuario;

public class SolicitarAberturaConta extends WebPage {

	private static final long serialVersionUID = 1L;
	public Form<Usuario> form;
	private Usuario usuario;

	public SolicitarAberturaConta() {
		usuario = new Usuario();
		form = new Form<Usuario>("form", new CompoundPropertyModel<Usuario>(usuario));
		add(form);
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

		form.add(nome, identidade, cpf, renda, telefone, email, nomeBanco, logradouro, bairro, cidade, estado, numero,
				cep);

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
	}

}
