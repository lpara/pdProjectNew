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

/**
* 
* @author lucas.carvalho | luan.alves
*
* @param <T> Tipo do dominio associado ao DAO
*/
public class AtorDAO extends GenericDAO<Ator> {
	
	
    public AtorDAO() {
		super(Ator.class);
	}

	public List<Ator> buscarAtoresEFilmes(){
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			     
	        String consulta = "select ator from Ator ator order by ator.primeiroNome";
	        Query q = session.createQuery(consulta, Ator.class);
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

