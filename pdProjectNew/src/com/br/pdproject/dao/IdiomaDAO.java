/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import com.br.pdproject.dominio.Idioma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Lucas | Luan
 */
public class IdiomaDAO extends GenericDAO {
    
    public List<Idioma> listarIdiomas(){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        String consulta = "select idioma from Idioma idioma order by idioma.nome";
        
        Query q = em.createQuery(consulta);
        List<Idioma> idiomas = q.getResultList();
        
        return idiomas;
    }
    
    public Idioma buscarPorNome(String nome){
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        String consulta = "select idioma from Idioma idioma where idioma.nome = :nomeIdioma";
        Query q = em.createQuery(consulta);
        q.setParameter("nomeIdioma", nome);
        
        Idioma idioma = (Idioma) q.getSingleResult();
        
        return idioma;
    }
}
