/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="category")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categoria_seq")
	@SequenceGenerator(name="categoria_seq", sequenceName="category_category_id_seq")
	@Column(name="category_id")
	private int id;
	
	@Column(name="name")
	private String nome;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

