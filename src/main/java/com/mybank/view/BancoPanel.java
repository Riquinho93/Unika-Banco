package com.mybank.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.mybank.model.Banco;
import com.mybank.service.AlertFeedback;

public class BancoPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Form<Banco> form;

	public BancoPanel(String id) {
		this(id, new Banco());
	}

	public BancoPanel(String id, Banco banco) {
		super(id);

		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);

		form = new Form<Banco>("form", new CompoundPropertyModel<Banco>(banco));

		TextField<String> nome = new TextField<>("nome");
		TextField<String> cep = new TextField<String>("endereco.cep");
		TextField<String> logradouro = new TextField<>("endereco.logradouro");
		NumberTextField<Integer> numero = new NumberTextField<>("endereco.numero");
		TextField<String> bairro = new TextField<>("endereco.bairro");
		TextField<String> cidade = new TextField<>("endereco.cidade");
		TextField<String> estado = new TextField<>("endereco.estado");

		nome.setOutputMarkupId(true);
		cep.setOutputMarkupId(true);
		logradouro.setOutputMarkupId(true);
		numero.setOutputMarkupId(true);
		bairro.setOutputMarkupId(true);
		cidade.setOutputMarkupId(true);
		estado.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("submit") {

			private static final long serialVersionUID = 994698440577863113L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				if (nome.getModelObject() == null) {
					feedbackPanel.error("Nome é obrigatorio");
				}
				if (logradouro.getModelObject() == null) {
					feedbackPanel.error("logradouro é obrigatorio!");
				}
				if (bairro.getModelObject() == null) {
					feedbackPanel.error("Bairro é obrigatorio");
				}
				if (cidade.getModelObject() == null) {
					feedbackPanel.error("Cidade é obrigatorio");
				}
				if (estado.getModelObject() == null) {
					feedbackPanel.error("Estado é obrigatorio");
				} else {

					executarAoSalvar(target, banco);
					target.add(nome);
					target.add(cep);
					target.add(logradouro);
					target.add(numero);
					target.add(bairro);
					target.add(cidade);
					target.add(estado);
				}
				target.add(feedbackPanel);
			}
		};
		add(feedbackPanel);
		button.setOutputMarkupId(true);
		form.add(nome, cep, logradouro, numero, bairro, cidade, estado);
		form.add(button);
		add(form);
		voltar();

	}

	// Enviando os dados para o HomePage
	public void executarAoSalvar(AjaxRequestTarget target, Banco banco) {

	}

	private void voltar() {
		AjaxLink<Login> ajaxLink = new AjaxLink<Login>("voltar") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(BancoForm.class);
			}
		};

		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		form.add(ajaxLink);
	}

}
