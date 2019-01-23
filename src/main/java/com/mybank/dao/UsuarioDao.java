package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Usuario;

@Repository
public class UsuarioDao extends GenericDao<Usuario, Serializable> {

	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		String hql = "select f from Usuario f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Usuario> userList = query.list();
		return userList;
	}
}
