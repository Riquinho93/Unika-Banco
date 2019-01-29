package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Contato;

@Repository
public class ContatoDao extends GenericDao<Contato, Serializable> {

	@SuppressWarnings("unchecked")
	// @Transactional(readOnly = true)
	public List<Contato> listar() {
		String hql = "select f from Contato f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Contato> userList = query.list();
		return userList;
	}
}
