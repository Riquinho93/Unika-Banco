package com.mybank.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Conta;
import com.mybank.model.Contato;
import com.mybank.service.ContaService;
import com.mybank.service.ContatoService;

public class ContatoPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Form<Contato> form;
	private Contato contato;

	private Conta conta;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	@SpringBean(name = "contatoService")
	private ContatoService contatoService;

	public ContatoPanel(String id) {
		super(id);
		contato = new Contato();

		conta = new Conta();

		form = new Form<Contato>("form", new CompoundPropertyModel<Contato>(contato));

		final NumberTextField<Integer> numeroConta = new NumberTextField<Integer>("numeroConta");
		final NumberTextField<Integer> cpf = new NumberTextField<>("cpf");

		numeroConta.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Search search = new Search(Conta.class);
				
				search.addFilterEqual("numeroConta", + numeroConta.getModelObject());
				List<Conta> listacontas = contaService.search(search);
				Conta conta = listacontas.get(0);
				
				System.out.println("conta: " + conta.getUsuario().getCpf());
				System.out.println("conta: " + cpf.getModelObject());
				
				/*
				 * ESTA TENDO ERRO DE DUPLICIDADE DO CPF
				 * */
				
				
	//			if (conta.getUsuario().getCpf() == cpf.getModelObject()) {
					listacontas.add(conta);
					contato.setListacontas(listacontas);
					contatoService.SalvarOuAlterar(contato);
					executarAoSalvar(target);
//				} else {
//					System.out.println("Erro no ContatoPanel");
//				}

				target.add(numeroConta, cpf);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(numeroConta, cpf);
		form.add(ajaxButton);
		add(form);

	}

	public void executarAoSalvar(AjaxRequestTarget target) {
	}

}
