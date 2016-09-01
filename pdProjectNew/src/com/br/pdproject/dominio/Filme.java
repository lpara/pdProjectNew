/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.pdproject.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* @author lucas.carvalho | luan.alves
*/
@Entity
@Table(name="film")
public class Filme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="filme_seq")
	@SequenceGenerator(name="filme_seq", sequenceName="film_film_id_seq")
	@Column(name="film_id")
	private int id;
	
	@Column(name="title")
	private String titulo;
	
	@Column(name="description")
	private String descricao;
	
	@Column(name="release_year")
	private int anoLancamento;
	
	@ManyToOne
	@JoinColumn(name="language_id")
	private Idioma idioma;
	
	@Column(name="rental_duration")
	private int duracaoAluguel;
	
	@Column(name="rental_rate")
	private double taxaAluguel;
	
	@Column(name="length")
	private int duracao;
	
	@Column(name="replacement_cost")
	private double custoReposicao;
	
	@ManyToOne
	@JoinTable(name="film_category", 
	joinColumns=@JoinColumn(name="film_id"),
	inverseJoinColumns=@JoinColumn(name="category_id"))
	private Categoria categoria;
	
	@OneToMany(mappedBy="filmes")
	private List<Ator> atores;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public int getDuracaoAluguel() {
		return duracaoAluguel;
	}

	public void setDuracaoAluguel(int duracaoAluguel) {
		this.duracaoAluguel = duracaoAluguel;
	}

	public double getTaxaAluguel() {
		return taxaAluguel;
	}

	public void setTaxaAluguel(double taxaAluguel) {
		this.taxaAluguel = taxaAluguel;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public double getCustoReposicao() {
		return custoReposicao;
	}

	public void setCustoReposicao(double custoReposicao) {
		this.custoReposicao = custoReposicao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

}
