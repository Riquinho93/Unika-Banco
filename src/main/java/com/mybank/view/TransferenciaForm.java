package com.mybank.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
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
import org.eclipse.jetty.util.security.Password;

import com.googlecode.genericdao.search.Search;
import com.mybank.HomePage;
import com.mybank.model.Banco;
import com.mybank.model.Conta;
import com.mybank.model.TipoConta;
import com.mybank.model.Transferencia;
import com.mybank.service.BancoService;
import com.mybank.service.ContaService;

public class TransferenciaForm extends HomePage {

	private static final long serialVersionUID = 1L;

	private List<Banco> listaBancos = new ArrayList<>();
	private Transferencia transferencia;

	@SpringBean(name = "bancoService")
	private BancoService bancoService;

	@SpringBean(name = "contaService")
	private ContaService contaService;

	public TransferenciaForm() {
		this(new Conta());
	}

	public TransferenciaForm(Conta contaParametro) {
		add(new Label("nome", contaParametro.getUsuario().getNome()));
		add(new Label("tipoConta", contaParametro.getTipoConta()));
		add(new Label("numeroConta", contaParametro.getNumeroConta()));

		listaBancos = bancoService.listar();

		transferencia = new Transferencia();
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

		AjaxButton ajaxButton = new AjaxButton("salvar") {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				Search search = new Search(Conta.class);

				search.addFilterEqual("numeroConta", numeroConta.getModelObject());
				List<Conta> listacontas = contaService.search(search);

				if (listacontas != null && !listacontas.isEmpty()) {
					Conta contaDestino = listacontas.get(0);

					if (contaDestino.getUsuario().getCpf() == Integer.parseInt(cpf.getValue())) {
						if (contaParametro.getSenha().equals(senha.getModelObject())) {
							System.out.println("Senha1: " + contaDestino.getSenha());
							System.out.println("Senha2: " + contaParametro.getSenha());
							System.out.println("Deuuuu Erroooooo");
							Double num = valor.getConvertedInput();
							contaService.transferir(contaParametro, contaDestino, num);
						} else {
							System.out.println("Senha Incorreta");
						}

					} else {
						System.out.println("Cpf não bate com a conta destino!!! ");
					}
				} else {
					System.out.println("Nao existe essa conta");
				}

				target.add(numeroConta, cpf, senha, valor);
				transferencia = new Transferencia();
				form.clearInput();
				form.modelChanged();
				form.setDefaultModelObject(transferencia);
				target.add(form);
			}
		};

		ajaxButton.setOutputMarkupId(true);
		form.add(numeroConta, cpf, senha, valor);
		form.add(ajaxButton);
		add(form);

	}

}
