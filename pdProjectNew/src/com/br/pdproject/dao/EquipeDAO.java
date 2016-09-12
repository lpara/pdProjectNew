/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import com.br.pdproject.dominio.Endereco;
import javax.persistence.Query;

import com.br.pdproject.dominio.Equipe;
import com.br.pdproject.dominio.Loja;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Classe DAO da Equipe
 * 
 * @author lucas.carvalho | luan.carlos
 *
 */
public class EquipeDAO extends GenericDAO {

    public void Salvar(String nome, String sobrenome, String email, String login, String senha){
        
        Equipe c = new Equipe();
        c.setNome(nome);
        c.setSobrenome(sobrenome);
        c.setEmail(email);
        c.setLogin(login);
        c.setSenha(senha);
        Loja loja = new Loja();
        loja.setId(1);
        c.setLoja(loja);
        Endereco end = new Endereco();
        end.setId(1);
        c.setEndereco(end);
        c.setAtivo(true);
        
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

    }
    
    public List<Equipe> Listar(){

       EntityManager em = GenericDAO.getEntityManager().createEntityManager();
       List<Equipe> funcionarios =  em.createQuery("from Equipe where ativo = true order by nome").getResultList();
       return funcionarios;
       
    }
    
    public List<Equipe> Filtrar(String filtro){

       EntityManager em = GenericDAO.getEntityManager().createEntityManager();
       
       String consulta = "from Equipe where ativo = true " + 
               "and (upper(nome) like '%" + filtro.toUpperCase() + "%' " +
                     "or upper(sobrenome) like '%" + filtro.toUpperCase() + "%' " +
                     "or upper(email) like '%" + filtro.toUpperCase() + "%') " +
               " order by nome ";
       List<Equipe> funcionarios =  em.createQuery(consulta).getResultList();
       return funcionarios;
       
    }
    
    public void Remover(Equipe funcionario){

        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Equipe SET ativo = false where id = " + funcionario.getId()).executeUpdate();
        em.getTransaction().commit();

       
    }
    
    public void Atualizar(Equipe funcionario){

        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        
        Query q = em.createQuery("UPDATE Equipe SET nome = '" + funcionario.getNome() +  "' , sobrenome = '" + funcionario.getSobrenome() +
                                "' , email = '" + funcionario.getEmail() +  "' , login = '" + funcionario.getLogin() +  "' , senha = '" + funcionario.getSenha() +  "'  where id = ? ");
        
        q.setParameter(1, funcionario.getId());
        
        q.executeUpdate();

        em.getTransaction().commit();
    
    }
}

