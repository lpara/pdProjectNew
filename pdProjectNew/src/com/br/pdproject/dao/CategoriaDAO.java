/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.br.pdproject.dominio.Categoria;
import javax.persistence.EntityManager;

/**
* Classe DAO das Categorias
* 
* @author lucas.carvalho | luan.alves
*
*/
public class CategoriaDAO extends GenericDAO {

	public List<Categoria> buscarCategorias(){
		Session session = null;
		try{
			EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			String consulta = "select c from Categoria c order by c.nome";
			
			Query q = em.createQuery(consulta);
			List<Categoria> result = q.getResultList();
			
			return result;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
		}finally {
			if(session != null && session.isOpen()){
				session.close();
				session = null;
			}
		}
		return null;
	}
}
