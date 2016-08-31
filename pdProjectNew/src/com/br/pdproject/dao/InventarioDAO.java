/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.br.pdproject.dominio.Aluguel;
import com.br.pdproject.dominio.Ator;
import com.br.pdproject.dominio.Inventario;
import com.br.pdproject.dominio.Loja;
import javax.persistence.EntityManager;
/**
* 
* @author lucas.carvalho | luan.alves
*
* @param <T> Tipo do dominio associado ao DAO
*/
public class InventarioDAO extends GenericDAO{


	public boolean isInventarioEmEstoque(int iventario){
		Integer aluguel = 0;
		AluguelDAO aluDao = new AluguelDAO();
		
		aluguel = aluDao.aluguelExistente(iventario);
		if(aluguel > 0){
			return false;
		}
		return true;
	}
	
	
	
	public List<Loja> buscarLojasPorFilme(int idFilme){
		Session session = null;
		List<Loja> result = new ArrayList<Loja>();
		try{
		
                EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			     
	        String consulta = "select i from Inventario i "
	        		+ "where i.filme.id = :filme";
	        Query q = em.createQuery(consulta);
	        q.setParameter("filme", idFilme);
	        @SuppressWarnings("unchecked")
	        List<Inventario> inventarios = (List<Inventario>) q.getResultList();
	        
	        for(Inventario invent : inventarios){
	        	if(!result.contains(invent.getLoja())){
	        		result.add(invent.getLoja());
	        	}
	        }
	        return result;
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
	
	public List<Inventario> inventariosPorFilmeELoja(int idFilme, int idLoja){
		EntityManager em = GenericDAO.getEntityManager().createEntityManager();
		try{
			
			String consulta = "select i from Inventario i "
					+ "where i.filme.id = :filme "
					+ " and i.loja.id = :loja";
			
			Query q = em.createQuery(consulta);
			q.setParameter("filme", idFilme);
			q.setParameter("loja", idLoja);
			List<Inventario> inventarios = q.getResultList();
			
			return inventarios;
		}catch(Exception e){
			em.close();
			e.printStackTrace();
		}finally{
			if(em != null && em.isOpen()){
				em.close();

			}
		}
		return null;
	}
	
	public List<Inventario> inventariosPorFilme(int filme){
		List<Inventario> invent = new ArrayList<Inventario>();
		Session session = null;
		try{
			EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			String consulta = "select i from Inventario i "
					+ "where i.filme.id = :filme";
			
			Query q = em.createQuery(consulta);
			q.setParameter("filme", filme);
			invent = q.getResultList();
			
			return invent;
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

