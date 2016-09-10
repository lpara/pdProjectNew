/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.br.pdproject.dominio.Cliente;
import com.br.pdproject.dominio.Endereco;
import com.br.pdproject.dominio.Loja;
import java.sql.Timestamp;
import javax.persistence.EntityManager;

/**
* Classe DAO de Atores
* 
* @author lucas.carvalho | luan.alves
*
*/
public class ClienteDAO extends GenericDAO {
	


    public void Salvar(String nome, String sobrenome, String email){
        
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setSobrenome(sobrenome);
        c.setEmail(email);
        c.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        Loja loja = new Loja();
        loja.setId(1);
        c.setLoja(loja);
        Endereco end = new Endereco();
        end.setId(1);
        c.setEndereco(end);
        
        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

    }
    
    public List<Cliente> Listar(){

       EntityManager em = GenericDAO.getEntityManager().createEntityManager();
       List<Cliente> clientes =  em.createQuery("from Cliente order by nome").getResultList();
       return clientes;
       
    }
    
    public void Remover(Cliente cliente){

        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Cliente where id = " + cliente.getId()).executeUpdate();
        em.getTransaction().commit();

       
    }
    
    public void Atualizar(Cliente cliente){

        EntityManager em = GenericDAO.getEntityManager().createEntityManager();
        em.getTransaction().begin();
        
        Query q = em.createQuery("UPDATE Cliente SET nome = '" + cliente.getNome() +  "' , sobrenome = '" + cliente.getSobrenome() +  "' , email = '" + cliente.getEmail() +  "'  where id = ? ");
        
        q.setParameter(1, cliente.getId());
        
        q.executeUpdate();

        em.getTransaction().commit();
    
    }
    
}

