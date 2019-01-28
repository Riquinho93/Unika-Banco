package com.mybank.view;

import org.apache.wicket.markup.html.panel.Panel;

import com.mybank.model.Contato;

public class ContatoPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ContatoPanel(String id) {
		this(id, new Contato());
	}

	public ContatoPanel(String id, Contato contato) {
		super(id);

	}

}
