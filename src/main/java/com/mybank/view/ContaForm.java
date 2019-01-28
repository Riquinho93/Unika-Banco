package com.mybank.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.genericdao.search.Search;
import com.mybank.HomePage;
import com.mybank.model.Banco;
import com.mybank.model.Conta;
import com.mybank.model.Usuario;
import com.mybank.service.BancoService;
import com.mybank.service.ContaService;
import com.mybank.service.UsuarioService;

public class ContaForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Conta> form = new Form<Conta>("form");
	private Conta filtrar;
	private Form<Conta> formFiltrar;
	private List<Conta> listaContas = new LinkedList<>();
	private PageableListView<Conta> listView;
	private LoadableDetachableModel<List<Conta>> atualizarLista;
	private WebMarkupContainer listContainer = null;
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	public ContaForm() {

		add(container());
		add(filtrar());

		listaContas = contaService.listar();

		modalWindow = new ModalWindow("modalWindow");
		// Tamanho do Modal
		modalWindow.setInitialHeight(350);
		modalWindow.setInitialWidth(500);
		modalWindow.setOutputMarkupId(true);
		add(modalWindow);

		// Modal Window do delete
		modalWindowDel = new ModalWindow("modalWindowDel");
		// Tamanho
		modalWindowDel.setInitialHeight(200);
		modalWindowDel.setInitialWidth(200);
		modalWindowDel.setOutputMarkupId(true);
		add(modalWindowDel);

		// Criando o botão para o modal
		add(new AjaxLink<String>("abrirModal") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ContaPanel contaPanel = new ContaPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Conta conta) {
						contaService.SalvarOuAlterar(conta);
						listaContas.add(conta);
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				contaPanel.setOutputMarkupId(true);
				add(contaPanel);
				modalWindow.setContent(contaPanel);
				modalWindow.show(target);
			}
		});

	}

	private WebMarkupContainer container() {
		listContainer = new WebMarkupContainer("container");
		listContainer.setOutputMarkupId(true);
		atualizarLista = new LoadableDetachableModel<List<Conta>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Conta> load() {
				return listaContas;
			}
		};

		listView = new PageableListView<Conta>("listView", atualizarLista, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Conta> item) {
				Conta user = item.getModelObject();
				item.add(new Label("numeroConta", user.getNumeroConta()));
				item.add(new Label("saldo", user.getSaldo()));
				item.add(remover(user.getId()));
				item.add(editando(user));
			}
		};
		add(listView);
		listView.setOutputMarkupId(true);
		listContainer.add(listView);

		add(new PagingNavigator("pag", listView));
		return listContainer;
	}

	public Form<Conta> filtrar() {
		filtrar = new Conta();
		formFiltrar = new Form<Conta>("formFiltrar", new CompoundPropertyModel<Conta>(filtrar));
		NumberTextField<Integer> numeroConta = new NumberTextField<Integer>("numeroConta");
		numeroConta.setOutputMarkupId(true);
		formFiltrar.add(numeroConta);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", formFiltrar) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Conta.class);

				if (filtrar.getNumeroConta() != 0) {
					search.addFilterLike("numeroConta", "%" + filtrar.getNumeroConta() + "%");
				}

				listaContas = contaService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		formFiltrar.setOutputMarkupId(true);
		formFiltrar.add(ajaxSubmitLink).setOutputMarkupId(true);
		return formFiltrar;

	}

	// Editando
	AjaxLink<Usuario> editando(Conta conta) {
		AjaxLink<Usuario> editar = new AjaxLink<Usuario>("alterar") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Conta user = contaService.alterar(conta.getId());
				ContaPanel contaPanel = new ContaPanel(modalWindow.getContentId(), user) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Conta conta) {
						contaService.SalvarOuAlterar(conta);
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				contaPanel.setOutputMarkupId(true);
				modalWindow.setContent(contaPanel);
				modalWindow.show(target);
			}
		};
		editar.setOutputMarkupId(true);
		form.add(editar);
		return editar;
	}

	// Removendo
	public Component remover(final Integer index) {

		AjaxLink<Banco> button = new AjaxLink<Banco>("excluir") {
			Conta answer = new Conta();

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletConta deletConta = new DeletConta(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(AjaxRequestTarget target, Conta conta) {
						if (conta.isAnswer() == true) {
							// enderecoService.excluir(index);
							contaService.excluir(index);
							target.add(listContainer);
						}
						modalWindowDel.close(target);
					};
				};
				deletConta.setOutputMarkupId(true);
				modalWindowDel.setContent(deletConta);
				modalWindowDel.show(target);
			}
		};
		button.setOutputMarkupId(true);
		form.add(button);
		return button;
	}

}
