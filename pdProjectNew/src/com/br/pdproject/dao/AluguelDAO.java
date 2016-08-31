/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

/**
* Classe DAO de Alugueis
* 
* @author lucas.carvalho | luan.alves
*
*/
public class AluguelDAO extends GenericDAO{

	public Integer aluguelExistente(int inventario){
		Session session = null;
		try{
                        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			String consulta = "select count(a.id) from Aluguel a "
					+ "where a.inventario.id = :idInventario";
			
			Query q = em.createQuery(consulta);
			q.setParameter("idInventario", inventario);
			Long resultado = (Long)q.getSingleResult();
			Integer al = resultado.intValue();
			if(al != 0){
				al = itemEstaAlugado(inventario);
			}
			
			return al;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.close();
				session = null;
			}
		}
		
		return null;
	}
	
	public Integer itemEstaAlugado (int inventario){
		Session session = null;
		try{
			EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			String novaConsulta = "select count(a.id) from Aluguel a "
					+ "where a.inventario.id = :inventario "
					+ "and a.dataDevolucao is null";
			
			Query newQ = em.createQuery(novaConsulta);
			newQ.setParameter("inventario", inventario);
			Long result = (Long) newQ.getSingleResult();
			Integer al = result.intValue();
			
			return al;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
		}finally{
			if(session != null && session.isOpen()){
				session.close();
				session = null;
			}
		}
		return null;
	}
}

