package com.mybank.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "banco")
public class Banco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String nome;

	@OneToMany(mappedBy = "banco", targetEntity = Conta.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Conta> listaContas;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "banco", targetEntity = Deposito.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Deposito> listaDeposito;

	@OneToMany(mappedBy = "banco", targetEntity = Transferencia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transferencia> listaTransferencia;

	@Transient
	protected boolean answer;

	// Getters e Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<Conta> listaContas) {
		this.listaContas = listaContas;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public List<Deposito> getListaDeposito() {
		return listaDeposito;
	}

	public void setListaDeposito(List<Deposito> listaDeposito) {
		this.listaDeposito = listaDeposito;
	}

	public List<Transferencia> getListaTransferencia() {
		return listaTransferencia;
	}

	public void setListaTransferencia(List<Transferencia> listaTransferencia) {
		this.listaTransferencia = listaTransferencia;
	}

}
