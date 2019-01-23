package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Banco;


@Repository
public class BancoDao extends GenericDao<Banco, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Banco> listar() {
		String hql = "select f from Banco f left join fetch f.endereco e";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Banco> userList = query.list();
		return userList;
	}
	
//	@Transactional(readOnly = true)
	public Banco alterar(Integer id) {
		String hql = "select f from Banco f left join fetch f.endereco e "
				+ "where f.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Banco user = (Banco) query.uniqueResult();
		return user;
	}
}
