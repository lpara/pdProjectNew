/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="actor")
public class Ator {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ator_seq")
	@SequenceGenerator(name="ator_seq", sequenceName="actor_actor_id_seq")
	@Column(name="actor_id")
	private int id;
	
	@Column(name="first_name")
	private String primeiroNome;
	
	@Column(name="last_name")
	private String ultimoNome;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="film_actor", 
	joinColumns=@JoinColumn(name="actor_id"),
	inverseJoinColumns=@JoinColumn(name="film_id"))
	private List<Filme> filmes;
	
	public int getId() {
		return id;
	}

	public void setId(int idAtor) {
		this.id = idAtor;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public List<Filme> getFilme() {
		return filmes;
	}

	public void setFilme(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
}

