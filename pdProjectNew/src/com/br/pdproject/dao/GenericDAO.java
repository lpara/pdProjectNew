/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author lucas.carvalho | luan.carlos
 *
 *
 */
public class GenericDAO {
	
    static private EntityManagerFactory emf;
    
    static{
        try{
            emf = Persistence.createEntityManagerFactory("persistence");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static EntityManagerFactory getEntityManager(){
        return emf;
    }
    
}

