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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="inventory")
public class Inventario {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="inventario_seq")
	@SequenceGenerator(name="inventario_seq", sequenceName="inventory_inventory_id_seq")
	@Column(name="inventory_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="film_id")
	private Filme filme;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Loja loja;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	
	
}
