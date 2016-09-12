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
		try{
			EntityManager em = GenericDAO.getEntityManager().createEntityManager();
			String consulta = "select c from Categoria c order by c.nome";
			
			Query q = em.createQuery(consulta);
			List<Categoria> result = q.getResultList();
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
                }
		return null;
	}
        
        public Categoria buscarPorNome(String nome){
            EntityManager em = GenericDAO.getEntityManager().createEntityManager();
            String consulta = "select c from Categoria c where c.nome = :nomeCategoria";
            Query q = em.createQuery(consulta);
            q.setParameter("nomeCategoria", nome);
            
            Categoria result = (Categoria)q.getSingleResult();
            
            return result;
        }
        
        public List<Categoria> Filtrar(String filtro){

            EntityManager em = GenericDAO.getEntityManager().createEntityManager();

            String consulta = "from Categoria where emUso = true and (upper(nome) like '%" + filtro.toUpperCase() + "%' ) "+ 
                    " order by titulo ";
            List<Categoria> categorias =  em.createQuery(consulta).getResultList();
            return categorias;
       
        }
        
        public void inserirCategoria(Categoria categoria){
            EntityManager em = GenericDAO.getEntityManager().createEntityManager();
            try{
                em.getTransaction().begin();
                em.persist(categoria);
                em.getTransaction().commit();
            }catch(Exception e){
                em.getTransaction().rollback();
                e.printStackTrace();
                
            }
        }
        
        public void removerCategoria(Categoria categoria){
            EntityManager em = GenericDAO.getEntityManager().createEntityManager();
            try{    
                String remocao = "update Categoria categoria set categoria.emUso = false where categoria.id = "+categoria.getId();
                em.getTransaction().begin();
                em.createQuery(remocao).executeUpdate();
                em.getTransaction().commit();
            }catch(Exception e){
                em.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        
        public void atualizarCategoria(Categoria categoria){

            EntityManager em = GenericDAO.getEntityManager().createEntityManager();
            em.getTransaction().begin();

            Query q = em.createQuery("UPDATE Categoria categoria SET categoria.nome = '" + categoria.getNome() +" where id = ? ");

            q.setParameter(1, categoria.getId());

            q.executeUpdate();

            em.getTransaction().commit();
    
        }
}
