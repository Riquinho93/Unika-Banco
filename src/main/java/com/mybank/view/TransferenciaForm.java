package com.mybank.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.mybank.HomePage;
import com.mybank.model.Banco;
import com.mybank.model.Conta;
import com.mybank.model.TipoConta;
import com.mybank.model.Transferencia;
import com.mybank.service.BancoService;

public class TransferenciaForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private List<Banco> listaBancos = new ArrayList<>();

	@SpringBean(name = "bancoService")
	private BancoService bancoService;

	public TransferenciaForm() {
		this(new Conta());
	}

	public TransferenciaForm(Conta conta) {

		listaBancos = bancoService.listar();

		Transferencia transferencia = new Transferencia();
		Form<Transferencia> form = new Form<Transferencia>("form",
				new CompoundPropertyModel<Transferencia>(transferencia));

		NumberTextField<Integer> numeroConta = new NumberTextField<>("numeroConta");
		NumberTextField<Integer> cpf = new NumberTextField<>("cpf");
		PasswordTextField senha = new PasswordTextField("senha");
		NumberTextField<Double> valor = new NumberTextField<>("valor");

		numeroConta.setOutputMarkupId(true);
		cpf.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		valor.setOutputMarkupId(true);

		// Select Bancos
		DropDownChoice<Banco> bancos = new DropDownChoice<Banco>("banco",
				new PropertyModel<Banco>(transferencia, "banco"), listaBancos, new ChoiceRenderer<Banco>("nome"));

		// Tipo da Conta
		ChoiceRenderer<TipoConta> renderer = new ChoiceRenderer<TipoConta>("descricao");
		IModel<List<TipoConta>> model = new LoadableDetachableModel<List<TipoConta>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<TipoConta> load() {
				return TipoConta.contas();
			}
		};

		DropDownChoice<TipoConta> tipos = new DropDownChoice<>("tipoConta", model, renderer);

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				
				target.add(numeroConta, cpf, senha, valor);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(numeroConta, cpf, senha, valor);
		form.add(bancos, tipos);
		form.add(ajaxButton);
		add(form);

	}

}
