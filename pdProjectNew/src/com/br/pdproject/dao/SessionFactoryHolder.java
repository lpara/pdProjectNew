/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.br.pdproject.dominio.Aluguel;
import com.br.pdproject.dominio.Ator;
import com.br.pdproject.dominio.Categoria;
import com.br.pdproject.dominio.Cidade;
import com.br.pdproject.dominio.Cliente;
import com.br.pdproject.dominio.Endereco;
import com.br.pdproject.dominio.Equipe;
import com.br.pdproject.dominio.Filme;
import com.br.pdproject.dominio.Idioma;
import com.br.pdproject.dominio.Inventario;
import com.br.pdproject.dominio.Loja;
import com.br.pdproject.dominio.Pagamento;
import com.br.pdproject.dominio.Pais;

public class SessionFactoryHolder {
	
	private static SessionFactory sessionFactory;
	
	static {
		inicializarSessionFactory();
	}

	public static void inicializarSessionFactory(){
		
		ServiceRegistry serviceRegistry = construirServiceRegistry();
		
		Metadata metadata = construirMetaData(serviceRegistry);
		
		sessionFactory = metadata.buildSessionFactory();
		 
	}

	/**
	 *  Para construir o {@link ServiceRegistry}, podemos utilizar:
	 *  
	 *  Para {@link BootstrapServiceRegistry} o builder {@link BootstrapServiceRegistryBuilder}.
	 *  
	 *  e para {@link StandardServiceRegistry} builder o {@link StandardServiceRegistryBuilder}
	 *  
	 * @return
	 */
	private static ServiceRegistry construirServiceRegistry(){
		 
		ServiceRegistry standarServiceRegistry = 
				new StandardServiceRegistryBuilder()
					.configure()
					.build();
		 
		return standarServiceRegistry;
	}
	
	private static Metadata construirMetaData(ServiceRegistry sr){
		MetadataSources metadataSources = new MetadataSources(sr);
		
		metadataSources.addAnnotatedClass( Aluguel.class )
			.addAnnotatedClass(Ator.class)
			.addAnnotatedClass(Categoria.class)
			.addAnnotatedClass(Cidade.class)
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(Endereco.class)
			.addAnnotatedClass(Equipe.class)
			.addAnnotatedClass(Filme.class)
			.addAnnotatedClass(Idioma.class)
			.addAnnotatedClass(Inventario.class)
			.addAnnotatedClass(Loja.class)
			.addAnnotatedClass(Pagamento.class)
			.addAnnotatedClass(Pais.class);
		
		// define outras configurações 
		MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		
		Metadata metadata = metadataBuilder.build();
		
		return metadata;
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
}
