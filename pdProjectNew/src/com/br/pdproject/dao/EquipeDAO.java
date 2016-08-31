/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import javax.persistence.Query;

import org.hibernate.Session;

import com.br.pdproject.dominio.Equipe;
import javax.persistence.EntityManager;

/**
 * Classe DAO da Equipe
 * 
 * @author lucas.carvalho | luan.carlos
 *
 */
public class EquipeDAO extends GenericDAO {

	public Equipe buscarEmpregadoMike(){
		Session session = null;
		try{
	        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
	        Equipe result = new Equipe();
	        
	        String consulta = "select e from Equipe e "
	                + "inner join e.endereco "
	                + "inner join e.endereco.cidade "
	                + "inner join e.endereco.cidade.pais "
	                + "where e.primeiroNome like :nome and e.ultimoNome like :ultimoNome";
	        
	        Query q = em.createQuery(consulta);
	        q.setParameter("nome", "Mike"); 
	        q.setParameter("ultimoNome", "Hillyer");
	        result = (Equipe) q.getSingleResult();
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
}

