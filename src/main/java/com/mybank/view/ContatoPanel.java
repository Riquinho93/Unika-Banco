package com.mybank.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
import com.mybank.model.Usuario;
import com.mybank.service.ContaService;
import com.mybank.service.ContatoService;
import com.mybank.service.UsuarioService;

public class ContatoPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Form<Contato> form;
	private Contato contato;

	private Conta conta;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	@SpringBean(name = "contatoService")
	private ContatoService contatoService;

	@SpringBean(name = "usuarioService")
	private UsuarioService usuarioService;

	public ContatoPanel(String id) {
		super(id);
		contato = new Contato();

		form = new Form<Contato>("form", new CompoundPropertyModel<Contato>(contato));

		final NumberTextField<Integer> numeroConta = new NumberTextField<Integer>("conta.numeroConta");
		final NumberTextField<Integer> cpf = new NumberTextField<>("usuario.cpf");

		numeroConta.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Search search = new Search(Conta.class);
				Search search2 = new Search(Usuario.class);

				search.addFilterEqual("numeroConta", numeroConta.getModelObject());
				List<Conta> listacontas = contaService.search(search);
				Conta conta = listacontas.get(0);

				search2.addFilterEqual("cpf", cpf.getModelObject());
				List<Usuario> listausuarios = usuarioService.search(search2);
				Usuario usuario = listausuarios.get(0);

				if (conta.getUsuario().getId() == usuario.getId()) {
					listacontas.add(conta);
					contato.setConta(conta);
					contato.setUsuario(usuario);
					executarAoSalvar(target, contato);
				} else {
					System.out.println("Erro no ContatoPanel");
				}

				target.add(numeroConta, cpf);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(numeroConta, cpf);
		form.add(ajaxButton);
		add(form);
		voltar();

	}

	public void executarAoSalvar(AjaxRequestTarget target, Contato contato) {
	}
	
	private void voltar() {
		AjaxLink<Login> ajaxLink = new AjaxLink<Login>("voltar") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(ContatoForm.class);
			}
		};

		ajaxLink.setOutputMarkupId(true);
		add(ajaxLink);
		form.add(ajaxLink);
	}
}
