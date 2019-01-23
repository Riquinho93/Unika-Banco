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
import com.mybank.model.Usuario;
import com.mybank.service.UsuarioService;

public class UsuarioForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Usuario> formUsuario = new Form<Usuario>("formUsuario");
	private Form<Usuario> formFiltrar;
	private Usuario filtrar;
	private List<Usuario> listaUsuarios = new LinkedList<>();
	private ModalWindow modalWindow;
	private ModalWindow modalWindowDel;
	private WebMarkupContainer listContainer = null;
	private LoadableDetachableModel<List<Usuario>> atualiazarUsuarios;
	private PageableListView<Usuario> listViewUsuario;

	@SpringBean(name = "usuarioService")
	private UsuarioService usuarioService;

	public UsuarioForm() {

		listaUsuarios = usuarioService.listar();

		add(container());
		
		add(filtrar());

		modalWindow = new ModalWindow("modalWindow");
		// Tamanho do Modal
		modalWindow.setInitialHeight(350);
		modalWindow.setInitialWidth(530);
		modalWindow.setOutputMarkupId(true);
		add(modalWindow);

		// Modal Window do delete
		modalWindowDel = new ModalWindow("modalWindowDel");
		// Tamanho
		modalWindowDel.setInitialHeight(200);
		modalWindowDel.setInitialWidth(200);
		modalWindowDel.setOutputMarkupId(true);
		add(modalWindowDel);

		// Criando o botao para o Modal
		add(new AjaxLink<String>("viewLink") {

			private static final long serialVersionUID = -7766269695313736383L;

			@Override
			public void onClick(AjaxRequestTarget target) {

				UsuarioPanel usuarioPanel = new UsuarioPanel(modalWindow.getContentId()) {

					private static final long serialVersionUID = 277997013286385910L;

					public void executarAoSalvar(AjaxRequestTarget target, Usuario usuario) {
						usuarioService.SalvarOuAlterar(usuario);
						listaUsuarios.add(usuario);
						target.add(listContainer);
						modalWindow.close(target);
					};

				};
				usuarioPanel.setOutputMarkupId(true);
				add(usuarioPanel);
				modalWindow.setContent(usuarioPanel);
				modalWindow.show(target);
			};

		});

	}

	private WebMarkupContainer container() {

		listContainer = new WebMarkupContainer("container");
		listContainer.setOutputMarkupId(true);
		atualiazarUsuarios = new LoadableDetachableModel<List<Usuario>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Usuario> load() {
				return listaUsuarios;
			}
		};

		listViewUsuario = new PageableListView<Usuario>("listViewUsuario", atualiazarUsuarios, 5) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Usuario> item) {
				Usuario user = item.getModelObject();
				item.add(new Label("nome", user.getNome()));
				item.add(new Label("perfil", user.getPerfil()));
				item.add(new Label("login", user.getLogin()));
				item.add(new Label("senha", user.getSenha()));
				item.add(editando(user));
				item.add(remover(user.getId()));
			}
		};
		add(listViewUsuario);
		listViewUsuario.setOutputMarkupId(true);
		listContainer.add(listViewUsuario);

		add(new PagingNavigator("pag", listViewUsuario));
		return listContainer;
	}

	protected Component remover(Integer id) {
		
		AjaxLink<Usuario> ajaxLink = new AjaxLink<Usuario>("excluir") {

			private static final long serialVersionUID = 1L;
			
			Usuario answer = new Usuario();
			@Override
			public void onClick(AjaxRequestTarget target) {
				DeletUsuario deletUsuario = new DeletUsuario(modalWindowDel.getContentId(), answer) {
					
					private static final long serialVersionUID = 1L;

					public void executarAoExcluir(AjaxRequestTarget target, Usuario usuario) {
						if(usuario.isAnswer() == true) {
							usuarioService.excluir(id);
							target.add(listContainer);
						}
						modalWindowDel.close(target);
					};
					
				};
				deletUsuario.setOutputMarkupId(true);
				modalWindowDel.setContent(deletUsuario);
				modalWindowDel.show(target);
			}
		};
		ajaxLink.setOutputMarkupId(true);
		formUsuario.add(ajaxLink);
		return ajaxLink;
	}
	
	//Editando
	AjaxLink<Usuario> editando(Usuario usuario){
		AjaxLink<Usuario> editar = new AjaxLink<Usuario>("alterar") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				UsuarioPanel usuarioPanel = new UsuarioPanel(modalWindow.getContentId(), usuario) {

					private static final long serialVersionUID = 1L;

					public void executarAoSalvar(AjaxRequestTarget target, Usuario usuario) {
						usuarioService.SalvarOuAlterar(usuario);
						target.add(listContainer);
						modalWindow.close(target);;
					}
				};
				usuarioPanel.setOutputMarkupId(true);
				modalWindow.setContent(usuarioPanel);
				modalWindow.show(target);
			}
		};
		editar.setOutputMarkupId(true);
		formUsuario.add(editar);
		return editar;
	}
	
	//Filtrar
	public Form<Usuario> filtrar(){
		filtrar = new Usuario();
		formFiltrar = new Form<Usuario>("formFiltrar", new CompoundPropertyModel<Usuario>(filtrar));
		TextField<Usuario> nome = new TextField<>("nome");
		nome.setOutputMarkupId(true);
		formFiltrar.add(nome);
		AjaxSubmitLink ajaxLink = new AjaxSubmitLink("filtrar", formFiltrar) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Search search = new Search();
				if (filtrar.getNome() != null && !filtrar.getNome().equals("")) {
					search.addFilterLike("nome", "%" + filtrar.getNome() + "%");
				}
				listaUsuarios = usuarioService.search(search);
				target.add(listContainer);
				super.onSubmit(target, form);
			}
		};
		formFiltrar.setOutputMarkupId(true);
		formFiltrar.add(ajaxLink).setOutputMarkupId(true);
		return formFiltrar;
	}

}
