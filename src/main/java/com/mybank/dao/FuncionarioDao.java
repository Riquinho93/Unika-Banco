package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Funcionario;


@Repository
public class FuncionarioDao extends GenericDao<Funcionario, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Funcionario> listar() {
		String hql = "select f from Funcionario f left join fetch f.endereco e";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Funcionario> userList = query.list();
		return userList;
	}
	
//	@Transactional(readOnly = true)
	public Funcionario alterar(Integer id) {
		String hql = "select f from Funcionario f left join fetch f.endereco e "
				+ "where f.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Funcionario user = (Funcionario) query.uniqueResult();
		return user;
	}
}
