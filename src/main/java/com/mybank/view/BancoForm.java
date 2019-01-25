package com.mybank.view;

import java.util.ArrayList;
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
import com.mybank.model.Usuario;
import com.mybank.service.BancoService;

public class BancoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Banco> form = new Form<>("form");
	private Banco filtrar;
	private Form<Banco> formFiltrar;
	private List<Banco> listaBancos = new ArrayList<>();
	private PageableListView<Banco> listView;
	private LoadableDetachableModel<List<Banco>> atualizarLista;
	private WebMarkupContainer listContainer = null;
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;
	@SpringBean(name = "bancoService")
	private BancoService bancoService;

	public BancoForm() {

		add(container());
		add(filtrar());

		modalWindow = new ModalWindow("modalWindow");
		// Tamanho do Modal
		modalWindow.setInitialHeight(500);
		modalWindow.setInitialWidth(700);
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
				BancoPanel bancoPanel = new BancoPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Banco banco) {
						bancoService.SalvarOuAlterar(banco);
						listaBancos.add(banco);
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				bancoPanel.setOutputMarkupId(true);
				add(bancoPanel);
				modalWindow.setContent(bancoPanel);
				modalWindow.show(target);
			}
		});
	}

	private WebMarkupContainer container() {
		listContainer = new WebMarkupContainer("container");
		listContainer.setOutputMarkupId(true);
		atualizarLista = new LoadableDetachableModel<List<Banco>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Banco> load() {
				return listaBancos;
			}
		};

		listView = new PageableListView<Banco>("listView", atualizarLista, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Banco> item) {
				Banco user = item.getModelObject();
				item.add(new Label("nome", user.getNome()));
				item.add(new Label("endereco", user.getEndereco().getCidade()));
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

	public Form<Banco> filtrar() {
		filtrar = new Banco();
		formFiltrar = new Form<Banco>("formFiltrar", new CompoundPropertyModel<Banco>(filtrar));
		TextField<String> nome = new TextField<String>("nome");
		nome.setOutputMarkupId(true);
		formFiltrar.add(nome);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", formFiltrar) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Banco.class);

				if (filtrar.getNome() != null && !filtrar.getNome().equals("")) {
					search.addFilterLike("nome", "%" + filtrar.getNome() + "%");
				}

				listaBancos = bancoService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		formFiltrar.setOutputMarkupId(true);
		formFiltrar.add(ajaxSubmitLink).setOutputMarkupId(true);
		return formFiltrar;

	}

	// Editando
	AjaxLink<Usuario> editando(Banco banco) {
		AjaxLink<Usuario> editar = new AjaxLink<Usuario>("alterar") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Banco user = bancoService.alterar(banco.getId());
				BancoPanel bancoPanel = new BancoPanel(modalWindow.getContentId(), user) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Banco banco) {
						bancoService.SalvarOuAlterar(banco);
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				bancoPanel.setOutputMarkupId(true);
				modalWindow.setContent(bancoPanel);
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
			Banco answer = new Banco();

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletBanco deletBanco = new DeletBanco(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(AjaxRequestTarget target, Banco banco) {
						if (banco.isAnswer() == true) {
							// enderecoService.excluir(index);
							bancoService.excluir(index);
							target.add(listContainer);
						}
						modalWindowDel.close(target);
					};
				};
				deletBanco.setOutputMarkupId(true);
				modalWindowDel.setContent(deletBanco);
				modalWindowDel.show(target);
			}
		};
		button.setOutputMarkupId(true);
		form.add(button);
		return button;
	}

}
