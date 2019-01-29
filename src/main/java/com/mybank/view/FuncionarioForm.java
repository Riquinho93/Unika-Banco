package com.mybank.view;

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
import com.mybank.model.Funcionario;
import com.mybank.model.Usuario;
import com.mybank.service.FuncionarioService;

public class FuncionarioForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Funcionario> form = new Form<Funcionario>("form");
	private Funcionario filtrar;
	private Form<Funcionario> formFiltrar;
	private List<Funcionario> listaFuncionarios = new LinkedList<>();
	private PageableListView<Funcionario> listView;
	private LoadableDetachableModel<List<Funcionario>> atualizarLista;
	private WebMarkupContainer listContainer = null;
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;
	@SpringBean(name = "funcionarioService")
	private FuncionarioService funcionarioService;

	public FuncionarioForm() {

		add(container());
		add(filtrar());
		
		listaFuncionarios = funcionarioService.listar();
		
		modalWindow = new ModalWindow("modalWindow");
		// Tamanho do Modal
		modalWindow.setInitialHeight(400);
		modalWindow.setInitialWidth(700);
		modalWindow.setOutputMarkupId(true);
		add(modalWindow);

		// Modal Window do delete
		modalWindowDel = new ModalWindow("modalWindowDel");
		// Tamanho
		modalWindowDel.setInitialHeight(250);
		modalWindowDel.setInitialWidth(350);
		modalWindowDel.setOutputMarkupId(true);
		add(modalWindowDel);

		// Criando o botão para o modal
		add(new AjaxLink<String>("abrirModal") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				FuncionarioPanel funcionarioPanel = new FuncionarioPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Funcionario cliente) {
						funcionarioService.SalvarOuAlterar(cliente);
						listaFuncionarios.add(cliente);
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				funcionarioPanel.setOutputMarkupId(true);
				add(funcionarioPanel);
				modalWindow.setContent(funcionarioPanel);
				modalWindow.show(target);
			}
		});

	}

	private WebMarkupContainer container() {
		listContainer = new WebMarkupContainer("container");
		listContainer.setOutputMarkupId(true);
		atualizarLista = new LoadableDetachableModel<List<Funcionario>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Funcionario> load() {
				return listaFuncionarios;
			}
		};

		listView = new PageableListView<Funcionario>("listView", atualizarLista, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Funcionario> item) {
				Funcionario user = item.getModelObject();
				item.add(new Label("nome", user.getNome()));
				item.add(new Label("funcao", user.getFuncao()));
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

	public Form<Funcionario> filtrar() {
		filtrar = new Funcionario();
		formFiltrar = new Form<Funcionario>("formFiltrar", new CompoundPropertyModel<Funcionario>(filtrar));
		TextField<String> nome = new TextField<>("nome");
		nome.setOutputMarkupId(true);
		formFiltrar.add(nome);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", formFiltrar) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Funcionario.class);

				if (filtrar.getNome() != null) {
					search.addFilterLike("nome", "%" + filtrar.getNome() + "%");
				}

				listaFuncionarios = funcionarioService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		formFiltrar.setOutputMarkupId(true);
		formFiltrar.add(ajaxSubmitLink).setOutputMarkupId(true);
		return formFiltrar;

	}

	// Editando
	AjaxLink<Usuario> editando(Funcionario cliente) {
		AjaxLink<Usuario> editar = new AjaxLink<Usuario>("alterar") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Funcionario user = funcionarioService.alterar(cliente.getId());
				FuncionarioPanel clientePanel = new FuncionarioPanel(modalWindow.getContentId(), user) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Funcionario cliente) {
						funcionarioService.SalvarOuAlterar(cliente);
						listaFuncionarios = funcionarioService.listar();
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				clientePanel.setOutputMarkupId(true);
				modalWindow.setContent(clientePanel);
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
			Funcionario answer = new Funcionario();

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletFuncionario deletCliente = new DeletFuncionario(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(AjaxRequestTarget target, Funcionario cliente) {
						if (cliente.isAnswer() == true) {
							// enderecoService.excluir(index);
							funcionarioService.excluir(index);
							listaFuncionarios = funcionarioService.listar();
							target.add(listContainer);
						}
						modalWindowDel.close(target);
					};
				};
				deletCliente.setOutputMarkupId(true);
				modalWindowDel.setContent(deletCliente);
				modalWindowDel.show(target);
			}
		};
		button.setOutputMarkupId(true);
		form.add(button);
		return button;
	}

}
