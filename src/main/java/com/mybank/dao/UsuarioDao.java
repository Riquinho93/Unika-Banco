package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Usuario;

@Repository
public class UsuarioDao extends GenericDao<Usuario, Serializable>{
	
	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		String hql = "select f from Usuario f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Usuario> userList = query.list();
		return userList;
	}
//	@Transactional(readOnly = true)
	public Usuario alterar(Integer id) {
		String hql = "select f from Usuario f left join fetch f.endereco e "
				+ "where f.id = :id";
		
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Usuario user = (Usuario) query.uniqueResult();
		return user;
	}
}
