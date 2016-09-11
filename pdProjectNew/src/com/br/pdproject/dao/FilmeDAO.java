/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import com.br.pdproject.dominio.Ator;
import com.br.pdproject.dominio.Filme;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
public class FilmeDAO extends GenericDAO {
    
    public Filme buscarFilme(int idFilme){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        Filme filme = new Filme();
        try{ 
            String consulta = "select filme from Filme where filme.id = :idFilme";
            Query q = em.createQuery(consulta);
            q.setParameter("idFilme", idFilme);
            filme = (Filme) q.getSingleResult();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        return filme;
    }
    
    public void inserirFilme(Filme filme){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        try{    
            em.getTransaction().begin();
            //Preenchimento dos campos não definidos pelo usuário do sistema.
            filme.setAnoLancamento(1990);
            filme.setDuracaoAluguel(2);
            filme.setTaxaAluguel(4.0);
            filme.setDuracao(120);
            filme.setCustoReposicao(10.00);
            Ator ator1 = new Ator();
            Ator ator2 = new Ator();
            List<Ator> atores = new ArrayList<Ator>();
            ator1.setId(165);
            ator2.setId(125);
            atores.add(ator1);
            atores.add(ator2);
            filme.setAtores(atores);
            
            em.persist(filme);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void removerFilme(int idFilme){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        Filme filme = buscarFilme(idFilme);
        try{    
            em.getTransaction().begin();
            em.remove(filme);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
}
