/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.br.pdproject.dominio.Ator;
import javax.persistence.EntityManager;

/**
* Classe DAO de Atores
* 
* @author lucas.carvalho | luan.alves
*
*/
public class AtorDAO extends GenericDAO {
	


	public List<Ator> buscarAtoresEFilmes(){
		Session session = null;
		try{
                    EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			     
                    String consulta = "select ator from Ator ator order by ator.primeiroNome";
                    Query q = em.createQuery(consulta, Ator.class);
                    @SuppressWarnings("unchecked")
                    List<Ator> result = (List<Ator>) q.getResultList();
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

