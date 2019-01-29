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
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.mybank.model.Banco;
import com.mybank.model.Conta;
import com.mybank.model.Situacao;
import com.mybank.model.TipoConta;
import com.mybank.model.Usuario;
import com.mybank.service.BancoService;
import com.mybank.service.ContaService;
import com.mybank.service.UsuarioService;

public class ContaPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private List<Usuario> listaUsuarios = new ArrayList<>();
	private List<Banco> listaBancos = new ArrayList<>();
	private List<Conta> listaContas;
	@SpringBean(name = "usuarioService")
	private UsuarioService usuarioService;
	@SpringBean(name = "bancoService")
	private BancoService bancoService;

	private Usuario usuario;
	private Banco banco;
	@SpringBean(name = "contaService")
	private ContaService contaService;

	public ContaPanel(String id) {
		this(id, new Conta());
	}

	public ContaPanel(String id, Conta conta) {
		super(id);
		listaContas = new ArrayList<>();
		listaUsuarios = usuarioService.listar();
		listaBancos = bancoService.listar();

		Form<Conta> form = new Form<Conta>("form", new CompoundPropertyModel<Conta>(conta));

		NumberTextField<Integer> numeroConta = new NumberTextField<Integer>("numeroConta");
		PasswordTextField senha = new PasswordTextField("senha");
		PasswordTextField confirmarSenha = new PasswordTextField("confirmarSenha");
		// NumberTextField<Double> limite = new NumberTextField<>("limite");

		numeroConta.setOutputMarkupId(true);
		senha.setOutputMarkupId(true);
		confirmarSenha.setOutputMarkupId(true);
		// limite.setOutputMarkupId(true).setVisible(false);
		
		 //Situacao
		  ChoiceRenderer<Situacao> renderer2 = new ChoiceRenderer<Situacao>("descricao");
		  IModel<List<Situacao>> model2 = new LoadableDetachableModel<List<Situacao>>() {
		  
		  private static final long serialVersionUID = 1L;
		  
		  @Override protected List<Situacao> load() { return Situacao.situacao(); } };
		  
		  DropDownChoice<Situacao> situacoes = new DropDownChoice<>("situacao", model2,
		  renderer2);

		// Select usuarios
		usuario = new Usuario();
		DropDownChoice<Usuario> usuarios = new DropDownChoice<Usuario>("usuario",  new PropertyModel<Usuario>(conta, "usuario"),listaUsuarios,
				new ChoiceRenderer<Usuario>("nome"));

		
		 //Select Bancos 
		 banco = new Banco();
		 DropDownChoice<Banco> bancos = new DropDownChoice<Banco>("banco", new PropertyModel<Banco>(conta, "banco"),listaBancos,
			new ChoiceRenderer<Banco>("nome"));
	

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
				/*conta.setUsuario(usuario);
				conta.setBanco(banco);*/
				executarAoSalvar(target, conta);
				listaContas.add(conta);
				Usuario usuario = usuarioService.buscarPorId(conta.getUsuario().getId());
				usuario.setListacontas(listaContas);
				usuarioService.SalvarOuAlterar(usuario);
				
				Banco banco = bancoService.buscarPorId(conta.getBanco().getId());
				banco.setListaContas(listaContas);
				bancoService.SalvarOuAlterar(banco);
				listaContas = new ArrayList<>();
				form.clearInput();
				form.modelChanged();
				form.setDefaultModelObject(conta);
				target.add(form);
				target.add(numeroConta, senha, confirmarSenha);
			}
		};
		ajaxButton.setOutputMarkupId(true);
		form.add(ajaxButton, tipos, usuarios, bancos, situacoes);
		form.add(numeroConta, senha, confirmarSenha);
		add(form);
	}

	public void executarAoSalvar(AjaxRequestTarget target, Conta conta) {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
