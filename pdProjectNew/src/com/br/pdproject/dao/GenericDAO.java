/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * 
 * @author lucas.carvalho | luan.carlos
 *
 * @param <T> Tipo do dominio associado ao DAO
 */
public class GenericDAO<T> implements DAO<T,Long> {
	
	private SessionFactory sessionFactory = SessionFactoryHolder
												.getSessionFactory();
	
	private Class<T> dominio;
	
	public GenericDAO(Class<T> dominio){	
		this.dominio = dominio;
	}
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Class<T> getDominio(){
		return dominio;
	}
	
	
	public T salvar(T entidade) {
		getCurrentSession().saveOrUpdate(entidade);
		return entidade;
	}

	
	public void deletar(T entidade) {
		getCurrentSession().delete(entidade);
	}

	
	public List<T> buscar() { 
		Transaction tx = getCurrentSession().beginTransaction();  
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();			
		CriteriaQuery<T> criteria = builder.createQuery(dominio);			
		criteria.from(dominio);
		tx.commit();
		return getCurrentSession().createQuery(criteria).getResultList();	
	}

	
	public long contar() {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();			
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);			
		criteria.select(builder.count(criteria.from(dominio)));
		return getCurrentSession().createQuery(criteria).getSingleResult();
	}

	
	public List<T> buscar(int offset, int max) { 
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();			
		CriteriaQuery<T> criteria = builder.createQuery(dominio);			
		criteria.from(dominio);
		return getCurrentSession().createQuery(criteria)
				.setFirstResult(offset).setMaxResults(max).getResultList();
	}
	
}

