/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import com.br.pdproject.dominio.Ator;
import com.br.pdproject.dominio.Filme;
import com.br.pdproject.dominio.Idioma;
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
    
    public List<Filme> listarFilmes(){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        List<Filme> filmes = new ArrayList<Filme>();
        try{
            String consulta = "select distinct filme from Filme filme where emUso = true order by filme.titulo";
            Query q = em.createQuery(consulta);
            filmes = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return filmes;
    }
    
    public List<Filme> Filtrar(String filtro){

       EntityManager em = GenericDAO.getEntityManager().createEntityManager();
       
       String consulta = "from Filme where emUso = true and (upper(titulo) like '%" + filtro.toUpperCase() + "%' "+ 
                     "or upper(descricao) like '%" + filtro.toUpperCase() + "%' ) " +
               " order by titulo ";
       List<Filme> filmes =  em.createQuery(consulta).getResultList();
       return filmes;
       
    }
    
    public void inserirFilme(Filme filme){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        try{    
            em.getTransaction().begin();
            //Preenchimento dos campos não definidos pelo usuário do sistema.
            filme.setDuracaoAluguel(2);
            filme.setTaxaAluguel(4.0);
            filme.setDuracao(120);
            filme.setCustoReposicao(10.00);
            Idioma idioma = new Idioma();
            idioma.setId(1);
            filme.setIdioma(idioma);
            Ator ator1 = new Ator();
            Ator ator2 = new Ator();
            List<Ator> atores = new ArrayList<Ator>();
            ator1.setId(165);
            ator2.setId(125);
            atores.add(ator1);
            atores.add(ator2);
            filme.setAtores(atores);
            filme.setEmUso(true);
            
            em.persist(filme);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void removerFilme(Filme filme){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        try{    
            String remocao = "update Filme filme set filme.emUso = false where filme.id = "+filme.getId();
            em.getTransaction().begin();
            em.createQuery(remocao).executeUpdate();
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public void atualizarFilme(Filme filme){

        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        
        Query q = em.createQuery("UPDATE Filme filme SET filme.titulo = '" + filme.getTitulo() +  "' , filme.descricao = '" + filme.getDescricao() +  
                "' , filme.anoLancamento = " + filme.getAnoLancamento() +  ", filme.categoria.id = " + filme.getCategoria().getId() + " where id = ? ");
        
        q.setParameter(1, filme.getId());
        
        q.executeUpdate();

        em.getTransaction().commit();
    
    }
    
}
