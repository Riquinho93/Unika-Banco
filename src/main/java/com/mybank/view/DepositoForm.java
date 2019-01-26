package com.mybank.view;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.googlecode.genericdao.search.Search;
import com.mybank.HomePage;
import com.mybank.model.Deposito;

public class DepositoForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private Form<Deposito> formFiltrar;
	private List<Deposito> listaDepositos = new LinkedList<>();

	public DepositoForm() {
		Deposito deposito = new Deposito();
		Form<Deposito> form = new Form<Deposito>("form", new CompoundPropertyModel<Deposito>(deposito));

		NumberTextField<Integer> numeroConta = new NumberTextField<>("numeroConta");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		AjaxButton button = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				target.add(numeroConta, valor);
			}
		};

		button.setOutputMarkupId(true);
		form.add(numeroConta, valor);
		form.add(button);
		add(form);


	}

	}
