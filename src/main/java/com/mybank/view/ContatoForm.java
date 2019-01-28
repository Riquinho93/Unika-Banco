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

import com.googlecode.genericdao.search.Search;
import com.mybank.HomePage;
import com.mybank.model.Banco;
import com.mybank.model.Contato;

public class ContatoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Contato> form = new Form<Contato>("form");
	private Contato filtrar;
	private Form<Contato> formFiltrar;
	private List<Contato> listaContatos = new LinkedList<>();
	private PageableListView<Contato> listView;
	private LoadableDetachableModel<List<Contato>> atualizarLista;
	private WebMarkupContainer listContainer = null;
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;

	public ContatoForm() {

		add(container());
		add(filtrar());

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
				ContatoPanel contatoPanel = new ContatoPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Contato contato) {
						listaContatos.add(contato);
						target.add(listContainer);
						modalWindow.close(target);
					};
				};
				contatoPanel.setOutputMarkupId(true);
				add(contatoPanel);
				modalWindow.setContent(contatoPanel);
				modalWindow.show(target);
			}
		});

	}

	private WebMarkupContainer container() {
		listContainer = new WebMarkupContainer("container");
		listContainer.setOutputMarkupId(true);
		atualizarLista = new LoadableDetachableModel<List<Contato>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Contato> load() {
				return listaContatos;
			}
		};

		listView = new PageableListView<Contato>("listView", atualizarLista, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Contato> item) {
				Contato user = item.getModelObject();
				item.add(new Label("nome", user.getUsuario().getNome()));
				item.add(new Label("numeroConta", user.getNumeroConta()));
				item.add(remover(user.getId()));
			}
		};
		add(listView);
		listView.setOutputMarkupId(true);
		listContainer.add(listView);

		add(new PagingNavigator("pag", listView));
		return listContainer;
	}

	public Form<Contato> filtrar() {
		filtrar = new Contato();
		formFiltrar = new Form<Contato>("formFiltrar", new CompoundPropertyModel<Contato>(filtrar));
		TextField<String> nome = new TextField<String>("usuario.nome");
		nome.setOutputMarkupId(true);
		formFiltrar.add(nome);
		AjaxSubmitLink ajaxSubmitLink = new AjaxSubmitLink("filtrar", formFiltrar) {

			private static final long serialVersionUID = 8104552052869900594L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search(Contato.class);

				if (filtrar.getUsuario().getNome() != null) {
					search.addFilterLike("usuario.nome", "%" + filtrar.getUsuario().getNome() + "%");
				}

//				listaContatos = contatoService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}

		};
		formFiltrar.setOutputMarkupId(true);
		formFiltrar.add(ajaxSubmitLink).setOutputMarkupId(true);
		return formFiltrar;

	}

	// Removendo
	public Component remover(final Integer index) {

		AjaxLink<Banco> button = new AjaxLink<Banco>("excluir") {
			Contato answer = new Contato();

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletContato deletConta = new DeletContato(modalWindowDel.getContentId(), answer) {

					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(AjaxRequestTarget target, Contato contato) {
						if (contato.isAnswer() == true) {
							// enderecoService.excluir(index);
//								contatoService.excluir(index);
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
