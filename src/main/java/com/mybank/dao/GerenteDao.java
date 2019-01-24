package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Gerente;

@Repository
public class GerenteDao extends GenericDao<Gerente, Serializable> {

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Gerente> listar() {
		String hql = "select f from Gerente f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Gerente> userList = query.list();
		return userList;
	}
	
//	@Transactional(readOnly = true)
	public Gerente alterar(Integer id) {
		String hql = "select f from Usuario f left join fetch f.endereco e "
				+ "where f.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Gerente user = (Gerente) query.uniqueResult();
		return user;
	}
}
