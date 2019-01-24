package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Cliente;

@Repository
public class ClienteDao extends GenericDao<Cliente, Serializable> {
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Cliente> listar() {
		String hql = "select f from Cliente f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Cliente> userList = query.list();
		return userList;
	}
	
//	@Transactional(readOnly = true)
	public Cliente alterar(Integer id) {
		String hql = "select f from Cliente f left join fetch f.endereco e "
				+ "where f.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Cliente user = (Cliente) query.uniqueResult();
		return user;
	}
}
