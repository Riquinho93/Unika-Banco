package com.mybank.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "funcionario")
public class Funcionario extends Usuario {

	private static final long serialVersionUID = 1L;


}