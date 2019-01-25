package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Conta;

@Repository
public class ContaDao extends GenericDao<Conta, Serializable> {

	@SuppressWarnings("unchecked")
	// @Transactional(readOnly = true)
	public List<Conta> listar() {
		String hql = "select f from Conta f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Conta> userList = query.list();
		return userList;
	}

	// @Transactional(readOnly = true)
	public Conta alterar(Integer id) {
		String hql = "select f from Conta f left join fetch f.endereco e " + "where f.id = :id";

		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Conta user = (Conta) query.uniqueResult();
		return user;
	}
}
